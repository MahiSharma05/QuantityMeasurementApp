package com.app.quantitymeasurement.dto;

public class QuantityInput {

    private QuantityDTO thisQuantity;
    private QuantityDTO thatQuantity;

    public QuantityDTO getThisQuantity() {
        return thisQuantity;
    }

    public void setThisQuantity(QuantityDTO thisQuantity) {
        this.thisQuantity = thisQuantity;
    }

    public QuantityDTO getThatQuantity() {
        return thatQuantity;
    }

    public void setThatQuantity(QuantityDTO thatQuantity) {
        this.thatQuantity = thatQuantity;
    }
}