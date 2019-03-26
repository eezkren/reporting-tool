package com.isilona.reporting.service;

import com.isilona.reporting.dao.psp.ClientResponse;
import com.isilona.reporting.dao.psp.TransactionsReportResponse;
import com.isilona.reporting.dto.ClientRequest;
import com.isilona.reporting.dto.TransactionsReportRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@Service
public class ClientService {

    private final RestTemplate restTemplate;
    private final AuthenticationService authenticationService;

    @Value("${external-api.psp.url.client}")
    private String resourceUrl;

    public ClientService(RestTemplateBuilder restTemplateBuilder, AuthenticationService authenticationService) {
        this.restTemplate = restTemplateBuilder.build();
        this.authenticationService = authenticationService;
    }

    public void getClient(ClientRequest jsonRequest, DeferredResult<String> deferredResult, Model model) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, authenticationService.getToken());

        HttpEntity<TransactionsReportRequest> request = new HttpEntity(jsonRequest, headers);

        CompletableFuture.supplyAsync(() -> this.restTemplate.postForObject(resourceUrl, request, ClientResponse.class))
                .whenCompleteAsync((result, throwable) -> {
                    deferredResult.setResult("clientResult");
                    model.addAttribute("client_result", result);
                });
    }
}
