package com.isilona.reporting.dto;

public enum PaymentMethod {

    CREDITCARD("CREDITCARD"),
    CUP("CUP"),
    IDEAL("IDEAL"),
    GIROPAY("GIROPAY"),
    MISTERCASH("MISTERCASH"),
    STORED("STORED"),
    PAYTOCARD("PAYTOCARD"),
    CEPBANK("CEPBANK"),
    CITADEL("CITADEL");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
