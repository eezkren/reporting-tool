package com.isilona.reporting.dto;

public enum Status {

    APPROVED("APPROVED"),
    WAITING("WAITING"),
    DECLINED("DECLINED"),
    ERROR("ERROR");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
