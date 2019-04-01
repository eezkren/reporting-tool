package com.isilona.reporting.dao.psp.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isilona.reporting.dao.psp.TransactionData;

import java.util.List;

public class TransactionListResponse {

    @JsonProperty("per_page")
    private Integer perPage;
    @JsonProperty("current_page")
    private Integer currentPage;
    @JsonProperty("next_page_url")
    private String nextPageUrl;
    @JsonProperty("prev_page_url")
    private String prevPageUrl;
    private Integer from;
    private Integer to;
    private List<TransactionData> data;

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public List<TransactionData> getData() {
        return data;
    }

    public void setData(List<TransactionData> data) {
        this.data = data;
    }
}
