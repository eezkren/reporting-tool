package com.isilona.reporting.dao.psp;

import java.util.List;

public class TransactionsReportResponse {

    private String status;
    private List<TransactionsReport> response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TransactionsReport> getResponse() {
        return response;
    }

    public void setResponse(List<TransactionsReport> response) {
        this.response = response;
    }
}
