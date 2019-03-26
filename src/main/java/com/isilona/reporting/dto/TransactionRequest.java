package com.isilona.reporting.dto;

import javax.validation.constraints.NotNull;

public class TransactionRequest implements PspRequest {

    @NotNull
    private String transactionId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
