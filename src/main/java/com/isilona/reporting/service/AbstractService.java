package com.isilona.reporting.service;

import com.isilona.reporting.dto.PspRequest;
import com.isilona.reporting.dto.TransactionsReportRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

public abstract class AbstractService {

    private final RestTemplate restTemplate;
    private final AuthenticationService authenticationService;

    public AbstractService(RestTemplateBuilder restTemplateBuilder, AuthenticationService authenticationService) {
        this.restTemplate = restTemplateBuilder.build();
        this.authenticationService = authenticationService;
    }

    public void getData(PspRequest jsonRequest, DeferredResult<String> deferredResult, Model model) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, authenticationService.getToken());

        HttpEntity<TransactionsReportRequest> request = new HttpEntity(jsonRequest, headers);

        CompletableFuture.supplyAsync(() -> this.restTemplate.postForObject(getResourceUrl(), request, getResponseClass()))
                .whenCompleteAsync((result, throwable) -> {
                    deferredResult.setResult(getTemplateName());
                    model.addAttribute(getAttributeName(), result);
                });
    }

    abstract String getAttributeName();

    abstract String getTemplateName();

    abstract Class getResponseClass();

    abstract String getResourceUrl();
}
