package com.isilona.reporting.service;

import com.isilona.reporting.dao.psp.TransactionsReportResponse;
import com.isilona.reporting.dto.TransactionsReportRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionsReportService {

    private final RestTemplate restTemplate;
    private final AuthenticationService authenticationService;

    @Value("${external-api.psp.url.transactions.report}")
    private String resourceUrl;

    public TransactionsReportService(RestTemplateBuilder restTemplateBuilder, AuthenticationService authenticationService) {
        this.restTemplate = restTemplateBuilder.build();
        this.authenticationService = authenticationService;
    }

    public TransactionsReportResponse getTransactionsReport(TransactionsReportRequest jsonRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, authenticationService.getToken());

        HttpEntity<TransactionsReportRequest> request = new HttpEntity(jsonRequest, headers);
        TransactionsReportResponse result = this.restTemplate.postForObject(resourceUrl, request, TransactionsReportResponse.class);

        System.out.println(result);
        return result;
    }
}
