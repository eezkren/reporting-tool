package com.isilona.reporting.dto;

public enum ErrorCode {

    DO_NOT_HONOR("Do not honor"),
    INVALID_TRANSACTION("Invalid Transaction"),
    INVALID_CARD("Invalid Card"),
    NOT_SUFFICIENT_FUNDS("Not sufficient funds"),
    INCORRECT_PIN("Incorrect PIN"),
    INVALID_COUNTRY_ASSOCIATION("Invalid country association"),
    CURRENCY_NOT_ALLOWED("Currency not allowed"),
    _3D_SECURE_TRANSPORT_ERROR("3-D Secure Transport Error"),
    TRANSACTION_NOT_PERMITTED_TO_CARDHOLDER("Transaction not permitted to cardholder");

    private final String displayName;

    ErrorCode(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
