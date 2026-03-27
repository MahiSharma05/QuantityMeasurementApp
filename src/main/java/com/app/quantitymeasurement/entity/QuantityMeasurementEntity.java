package com.app.quantitymeasurement.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;
import com.app.quantitymeasurement.core.IMeasurable;
import com.app.quantitymeasurement.dto.QuantityModel;

@Entity
@Table(name = "quantity_measurements")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;

    private double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    private String operation;

    private double resultValue;
    private String resultUnit;
    private String resultMeasurementType;

    private String resultString;

    private boolean isError;
    private String errorMessage;


    public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity,
                                     QuantityModel<IMeasurable> thatQuantity,
                                     String operation) {

        this.thisValue = thisQuantity.getValue();
        this.thisUnit = thisQuantity.getUnit().getUnitName();
        this.thisMeasurementType = thisQuantity.getUnit().getMeasurementType();
        this.operation = operation;

        if (thatQuantity != null) {
            this.thatValue = thatQuantity.getValue();
            this.thatUnit = thatQuantity.getUnit().getUnitName();
            this.thatMeasurementType = thatQuantity.getUnit().getMeasurementType();
        }
    }

    public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity,
                                     QuantityModel<IMeasurable> thatQuantity,
                                     String operation,
                                     String result) {

        this(thisQuantity, thatQuantity, operation);
        this.resultString = result;
    }

    public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity,
                                     QuantityModel<IMeasurable> thatQuantity,
                                     String operation,
                                     QuantityModel<IMeasurable> result) {

        this(thisQuantity, thatQuantity, operation);
        this.resultValue = result.getValue();
        this.resultUnit = result.getUnit().getUnitName();
        this.resultMeasurementType = result.getUnit().getMeasurementType();
    }

    public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity,
                                     QuantityModel<IMeasurable> thatQuantity,
                                     String operation,
                                     String errorMessage,
                                     boolean isError) {

        this(thisQuantity, thatQuantity, operation);
        this.errorMessage = errorMessage;
        this.isError = isError;
    }
}