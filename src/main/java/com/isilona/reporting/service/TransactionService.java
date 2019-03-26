package com.isilona.reporting.service;

import com.isilona.reporting.dao.psp.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends AbstractService {

    @Value("${external-api.psp.url.transaction}")
    private String resourceUrl;

    public TransactionService(RestTemplateBuilder restTemplateBuilder, AuthenticationService authenticationService) {
        super(restTemplateBuilder, authenticationService);
    }

    @Override
    String getAttributeName() {
        return "transaction_result";
    }

    @Override
    String getTemplateName() {
        return "transactionResult";
    }

    @Override
    Class getResponseClass() {
        return TransactionResponse.class;
    }

    @Override
    String getResourceUrl() {
        return resourceUrl;
    }
}
