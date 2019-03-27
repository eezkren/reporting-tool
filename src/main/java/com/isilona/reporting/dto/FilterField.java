package com.isilona.reporting.dto;

public enum FilterField {

    TRANSACTION_UUID("Transaction UUID"),
    CUSTOMER_EMAIL("Customer Email"),
    REFERENCE_NO("Reference No"),
    CUSTOM_DATA("Custom Data"),
    CARD_PAN("Card PAN");

    private final String displayName;

    FilterField(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
