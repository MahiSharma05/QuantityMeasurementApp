package com.quantity.quantityservice.service;

import com.quantity.quantityservice.core.ConversionStrategy;
import com.quantity.quantityservice.core.IMeasurable;
import com.quantity.quantityservice.dto.*;
import com.quantity.quantityservice.entity.QuantityMeasurementEntity;
import com.quantity.quantityservice.entity.OperationHistory;
import com.quantity.quantityservice.exception.*;
import com.quantity.quantityservice.measurement.UnitRegistry;
import com.quantity.quantityservice.repository.QuantityMeasurementRepository;
import com.quantity.quantityservice.repository.OperationHistoryRepository;
import com.quantity.quantityservice.strategy.LinearConversionStrategy;
import com.quantity.quantityservice.strategy.TemperatureConversionStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final QuantityMeasurementRepository repo;
    private final OperationHistoryRepository historyRepo;

    public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repo,
                                          OperationHistoryRepository historyRepo) {
        this.repo = repo;
        this.historyRepo = historyRepo;
    }

    // HISTORY METHOD
    private void saveHistory(String username,
                             String operationType,
                             String inputData,
                             String result) {
        historyRepo.save(OperationHistory.builder()
                .username(username)
                .operationType(operationType)
                .inputData(inputData)
                .result(result)
                .build());
    }

    // HISTORY METHODS
    @Override
    public List<OperationHistoryResponse> getUserHistory(String username) {
        return historyRepo.findByUsernameOrderByTimestampDesc(username)
                .stream()
                .map(OperationHistoryResponse::from)
                .toList();
    }

    // CONVERT UNITS

    @Override
    public ConvertResponse convertUnits(ConvertRequest request, String user) {

        IMeasurable from = UnitRegistry.resolve(request.getFromUnit());
        IMeasurable to   = UnitRegistry.resolve(request.getToUnit());

        if (!from.getMeasurementType().equals(to.getMeasurementType())) {
            throw new IncompatibleUnitsException(
                    from.getMeasurementType(), to.getMeasurementType());
        }

        ConversionStrategy strategy = selectStrategy(from);
        double result = strategy.convert(request.getValue(), from, to);

        // SAVE HISTORY
        saveHistory(user, "CONVERT_UNITS",
                "value=" + request.getValue()
                        + " from=" + request.getFromUnit()
                        + " to=" + request.getToUnit(),
                String.valueOf(result));

        return ConvertResponse.of(result,
                from.getUnitName(),
                to.getUnitName(),
                request.getValue());
    }

    private ConversionStrategy selectStrategy(IMeasurable unit) {
        if (unit.isTemperature()) {
            return TemperatureConversionStrategy.INSTANCE;
        }
        return LinearConversionStrategy.INSTANCE;
    }

    // EXISTING METHODS WITH HISTORY ADDED

    @Override
    public boolean compare(QuantityDTO aDto, QuantityDTO bDto, String user) {
        var a = resolve(aDto);
        var b = resolve(bDto);
        assertCompatible(a, b);

        boolean result;

        if (a.getUnit().isTemperature()) {
            ConversionStrategy strategy = TemperatureConversionStrategy.INSTANCE;
            IMeasurable celsius = UnitRegistry.resolve("CELSIUS");
            double aInCelsius = strategy.convert(a.getValue(), a.getUnit(), celsius);
            double bInCelsius = strategy.convert(b.getValue(), b.getUnit(), celsius);
            result = Math.abs(aInCelsius - bInCelsius) < 1e-9;
        } else {
            result = Math.abs(toBase(a) - toBase(b)) < 1e-9;
        }

        // SAVE HISTORY
        saveHistory(user, "COMPARE",
                "thisUnit=" + a.getUnit().getUnitName() + " value=" + a.getValue()
                        + " | thatUnit=" + b.getUnit().getUnitName() + " value=" + b.getValue(),
                String.valueOf(result));

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO aDto, QuantityDTO bDto, String user) {
        var a = resolve(aDto);
        var b = resolve(bDto);
        assertCompatible(a, b);

        double resultVal = selectStrategy(a.getUnit()).convert(a.getValue(), a.getUnit(), b.getUnit());
        var result = dto(resultVal, b.getUnit());

        // SAVE HISTORY
        saveHistory(user, "CONVERT",
                "value=" + a.getValue() + " from=" + a.getUnit().getUnitName()
                        + " to=" + b.getUnit().getUnitName(),
                "value=" + resultVal + " unit=" + b.getUnit().getUnitName());

        return result;
    }

    @Override
    public QuantityDTO add(QuantityDTO aDto, QuantityDTO bDto, String user) {
        var a = resolve(aDto);
        var b = resolve(bDto);
        assertCompatible(a, b);

        double resultVal;
        if (a.getUnit().isTemperature()) {
            ConversionStrategy strategy = TemperatureConversionStrategy.INSTANCE;
            IMeasurable celsius = UnitRegistry.resolve("CELSIUS");
            double aBase = strategy.convert(a.getValue(), a.getUnit(), celsius);
            double bBase = strategy.convert(b.getValue(), b.getUnit(), celsius);
            resultVal = strategy.convert(aBase + bBase, celsius, a.getUnit());
        } else {
            resultVal = fromBase(toBase(a) + toBase(b), a.getUnit());
        }

        var result = dto(resultVal, a.getUnit());

        // SAVE HISTORY
        saveHistory(user, "ADD",
                "thisUnit=" + a.getUnit().getUnitName() + " value=" + a.getValue()
                        + " | thatUnit=" + b.getUnit().getUnitName() + " value=" + b.getValue(),
                "value=" + resultVal + " unit=" + a.getUnit().getUnitName());

        return result;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO aDto, QuantityDTO bDto, String user) {
        var a = resolve(aDto);
        var b = resolve(bDto);
        assertCompatible(a, b);

        double resultVal;
        if (a.getUnit().isTemperature()) {
            ConversionStrategy strategy = TemperatureConversionStrategy.INSTANCE;
            IMeasurable celsius = UnitRegistry.resolve("CELSIUS");
            double aBase = strategy.convert(a.getValue(), a.getUnit(), celsius);
            double bBase = strategy.convert(b.getValue(), b.getUnit(), celsius);
            resultVal = strategy.convert(aBase - bBase, celsius, a.getUnit());
        } else {
            resultVal = fromBase(toBase(a) - toBase(b), a.getUnit());
        }

        var result = dto(resultVal, a.getUnit());

        // SAVE HISTORY
        saveHistory(user, "SUBTRACT",
                "thisUnit=" + a.getUnit().getUnitName() + " value=" + a.getValue()
                        + " | thatUnit=" + b.getUnit().getUnitName() + " value=" + b.getValue(),
                "value=" + resultVal + " unit=" + a.getUnit().getUnitName());

        return result;
    }

    @Override
    public double divide(QuantityDTO aDto, QuantityDTO bDto, String user) {
        var a = resolve(aDto);
        var b = resolve(bDto);
        assertCompatible(a, b);

        double divisor = toBase(b);
        if (Math.abs(divisor) < 1e-12) {
            throw new DivisionByZeroException();
        }

        double result = toBase(a) / divisor;

        // SAVE HISTORY
        saveHistory(user, "DIVIDE",
                "thisUnit=" + a.getUnit().getUnitName() + " value=" + a.getValue()
                        + " | thatUnit=" + b.getUnit().getUnitName() + " value=" + b.getValue(),
                String.valueOf(result));

        return result;
    }

    // HELPERS

    private QuantityModel<IMeasurable> resolve(QuantityDTO dto) {
        return new QuantityModel<>(dto.getValue(), UnitRegistry.resolve(dto.getUnit()));
    }

    private double toBase(QuantityModel<IMeasurable> q) {
        return q.getValue() * q.getUnit().getConversionFactor();
    }

    private double fromBase(double base, IMeasurable target) {
        return base / target.getConversionFactor();
    }

    private QuantityDTO dto(double value, IMeasurable unit) {
        return new QuantityDTO(value, unit.getUnitName(), unit.getMeasurementType());
    }

    private void assertCompatible(QuantityModel<IMeasurable> a, QuantityModel<IMeasurable> b) {
        if (!a.getUnit().getMeasurementType().equals(b.getUnit().getMeasurementType())) {
            throw new IncompatibleUnitsException(
                    a.getUnit().getMeasurementType(), b.getUnit().getMeasurementType());
        }
    }
}