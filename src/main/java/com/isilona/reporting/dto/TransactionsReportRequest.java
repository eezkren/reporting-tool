package com.isilona.reporting.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TransactionsReportRequest implements PspRequest {

    @NotNull
    private LocalDate fromDate;
    @NotNull
    private LocalDate toDate;
    private Integer merchantId;
    private Integer acquirerId;

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getAcquirerId() {
        return acquirerId;
    }

    public void setAcquirerId(Integer acquirerId) {
        this.acquirerId = acquirerId;
    }
}
