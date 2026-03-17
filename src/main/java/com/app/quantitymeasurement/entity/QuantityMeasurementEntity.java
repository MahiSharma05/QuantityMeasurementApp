package com.app.quantitymeasurement.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

import com.app.quantitymeasurement.core.IMeasurable;
import com.app.quantitymeasurement.dto.QuantityModel;

@Entity
@Table(name = "quantity_measurements")
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

    // REQUIRED by JPA
    public QuantityMeasurementEntity() {
    }

    // ===== EXISTING CONSTRUCTORS (UNCHANGED) =====

    public QuantityMeasurementEntity(QuantityModel<IMeasurable> thisQuantity,
                                     QuantityModel<IMeasurable> thatQuantity,
                                     String operation) {

        this.thisValue = thisQuantity.value;
        this.thisUnit = thisQuantity.getUnit().getUnitName();
        this.thisMeasurementType = thisQuantity.getUnit().getMeasurementType();
        this.operation = operation;

        if (thatQuantity != null) {
            this.thatValue = thatQuantity.value;
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
        this.resultValue = result.value;
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

    // ===== GETTERS & SETTERS (IMPORTANT FOR JPA) =====

    public Long getId() {
        return id;
    }

    public double getThisValue() {
        return thisValue;
    }

    public void setThisValue(double thisValue) {
        this.thisValue = thisValue;
    }

    public String getThisUnit() {
        return thisUnit;
    }

    public void setThisUnit(String thisUnit) {
        this.thisUnit = thisUnit;
    }

    public String getThisMeasurementType() {
        return thisMeasurementType;
    }

    public void setThisMeasurementType(String thisMeasurementType) {
        this.thisMeasurementType = thisMeasurementType;
    }

    public double getThatValue() {
        return thatValue;
    }

    public void setThatValue(double thatValue) {
        this.thatValue = thatValue;
    }

    public String getThatUnit() {
        return thatUnit;
    }

    public void setThatUnit(String thatUnit) {
        this.thatUnit = thatUnit;
    }

    public String getThatMeasurementType() {
        return thatMeasurementType;
    }

    public void setThatMeasurementType(String thatMeasurementType) {
        this.thatMeasurementType = thatMeasurementType;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResultValue() {
        return resultValue;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    public String getResultMeasurementType() {
        return resultMeasurementType;
    }

    public void setResultMeasurementType(String resultMeasurementType) {
        this.resultMeasurementType = resultMeasurementType;
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // ===== equals & toString =====

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof QuantityMeasurementEntity)) return false;

        QuantityMeasurementEntity other = (QuantityMeasurementEntity) obj;

        return Double.compare(thisValue, other.thisValue) == 0
                && Double.compare(thatValue, other.thatValue) == 0
                && Objects.equals(thisUnit, other.thisUnit)
                && Objects.equals(thatUnit, other.thatUnit)
                && Objects.equals(operation, other.operation);
    }

    @Override
    public String toString() {
        if (isError)
            return "[ERROR] " + operation + " | " + errorMessage;

        if (resultString != null) {
            return operation + " | " + thisValue + " " + thisUnit
                    + " vs " + thatValue + " " + thatUnit
                    + " -> " + resultString;
        }

        return operation + " | " + thisValue + " " + thisUnit
                + " & " + thatValue + " " + thatUnit
                + " = " + resultValue + " " + resultUnit;
    }
}