package com.isilona.reporting.dao.psp.response;

import com.isilona.reporting.dao.psp.TransactionsReport;

import java.util.List;

public class TransactionsReportResponse {

    private String status;
    private List<TransactionsReport> response;

    private String code;

    private String message;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
