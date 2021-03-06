package com.haulmont.testtask.model;

public enum PaymentScheduleType {
    DIFFERENTIATED("Differentiated"),
    ANNUAL("Annual");

    private final String displayValue;

    PaymentScheduleType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
