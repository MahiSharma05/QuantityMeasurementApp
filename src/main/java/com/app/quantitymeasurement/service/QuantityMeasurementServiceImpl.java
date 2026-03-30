package com.app.quantitymeasurement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.core.*;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityModel;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    // ================= ENUM =================
    private enum ArithmeticOperation {
        ADD {
            double compute(double a, double b) { return a + b; }
        },
        SUBTRACT {
            double compute(double a, double b) { return a - b; }
        },
        DIVIDE {
            double compute(double a, double b) {
                if (Math.abs(b) < 1e-5)
                    throw new ArithmeticException("Division by zero");
                return a / b;
            }
        };

        abstract double compute(double a, double b);
    }

    // ================= HELPERS =================

    private QuantityModel<IMeasurable> getQuantityModel(QuantityDTO dto) {
        if (dto == null)
            throw new QuantityMeasurementException("QuantityDTO cannot be null");

        IMeasurable unit = resolveUnit(dto.getMeasurementType(), dto.getUnit());
        return new QuantityModel<>(dto.getValue(), unit);
    }

    private IMeasurable resolveUnit(String type, String unit) {
        return switch (type) {
            case "LengthUnit" -> LengthUnit.FEET.getUnitInstance(unit);
            case "WeightUnit" -> WeightUnit.KILOGRAM.getUnitInstance(unit);
            case "VolumeUnit" -> VolumeUnit.LITRE.getUnitInstance(unit);
            case "TemperatureUnit" -> TemperatureUnit.CELSIUS.getUnitInstance(unit);
            default -> throw new QuantityMeasurementException("Unknown type: " + type);
        };
    }

    private void validate(QuantityModel<IMeasurable> m1, QuantityModel<IMeasurable> m2) {
        if (m1 == null || m2 == null)
            throw new QuantityMeasurementException("Operands cannot be null");

        if (!m1.getUnit().getClass().equals(m2.getUnit().getClass()))
            throw new QuantityMeasurementException("Different measurement types");
    }

    private double baseValue(QuantityModel<IMeasurable> m) {
        return m.getUnit().convertToBaseUnit(m.getValue());
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    private QuantityDTO toDTO(double value, IMeasurable unit) {
        return new QuantityDTO(value, unit.getUnitName(), unit.getMeasurementType());
    }

    // ================= COMPARE =================
    @Override
    public boolean compare(QuantityDTO d1, QuantityDTO d2) {
        var m1 = getQuantityModel(d1);
        var m2 = getQuantityModel(d2);

        try {
            boolean equal = false;

            if (m1.getUnit().getClass().equals(m2.getUnit().getClass())) {
                equal = Math.abs(baseValue(m1) - baseValue(m2)) <= 1e-5;
            }

            repository.save(new QuantityMeasurementEntity(m1, m2, "COMPARE", String.valueOf(equal)));
            return equal;

        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity(m1, m2, "COMPARE", e.getMessage(), true));
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ================= CONVERT =================
    @Override
    public QuantityDTO convert(QuantityDTO d1, QuantityDTO d2) {
        var m1 = getQuantityModel(d1);
        var target = getQuantityModel(d2).getUnit();

        try {
            double result = round(target.convertFromBaseUnit(baseValue(m1)));

            repository.save(new QuantityMeasurementEntity(m1, null, "CONVERT", String.valueOf(result)));

            return toDTO(result, target);

        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity(m1, null, "CONVERT", e.getMessage(), true));
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ================= ADD =================
    @Override
    public QuantityDTO add(QuantityDTO d1, QuantityDTO d2) {
        var m1 = getQuantityModel(d1);
        var m2 = getQuantityModel(d2);

        try {
            validate(m1, m2);

            double result = round(
                    m1.getUnit().convertFromBaseUnit(
                            baseValue(m1) + baseValue(m2)
                    )
            );

            repository.save(new QuantityMeasurementEntity(m1, m2, "ADD", String.valueOf(result)));

            return toDTO(result, m1.getUnit());

        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity(m1, m2, "ADD", e.getMessage(), true));
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ================= SUBTRACT =================
    @Override
    public QuantityDTO subtract(QuantityDTO d1, QuantityDTO d2) {
        var m1 = getQuantityModel(d1);
        var m2 = getQuantityModel(d2);

        try {
            validate(m1, m2);

            double result = round(
                    m1.getUnit().convertFromBaseUnit(
                            baseValue(m1) - baseValue(m2)
                    )
            );

            repository.save(new QuantityMeasurementEntity(m1, m2, "SUBTRACT", String.valueOf(result)));

            return toDTO(result, m1.getUnit());

        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity(m1, m2, "SUBTRACT", e.getMessage(), true));
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ================= DIVIDE =================
    @Override
    public double divide(QuantityDTO d1, QuantityDTO d2) {
        var m1 = getQuantityModel(d1);
        var m2 = getQuantityModel(d2);

        try {
            validate(m1, m2);

            double result = baseValue(m1) / baseValue(m2);

            repository.save(new QuantityMeasurementEntity(m1, m2, "DIVIDE", String.valueOf(result)));

            return result;

        } catch (Exception e) {
            repository.save(new QuantityMeasurementEntity(m1, m2, "DIVIDE", e.getMessage(), true));
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

	@Override
	public QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, QuantityDTO targetUnitDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}