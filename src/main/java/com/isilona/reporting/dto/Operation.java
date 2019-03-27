package com.isilona.reporting.dto;

public enum Operation {

    DIRECT("DIRECT"),
    REFUND("REFUND"),
    _3D("3D"),
    _3DAUTH("3DAUTH"),
    STORED("STORED");

    private final String displayName;

    Operation(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
