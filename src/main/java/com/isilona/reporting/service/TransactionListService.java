package com.isilona.reporting.service;

import com.isilona.reporting.dao.psp.response.TransactionListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class TransactionListService extends AbstractService {

    @Value("${external-api.psp.url.transaction.list}")
    private String resourceUrl;

    public TransactionListService(RestTemplateBuilder restTemplateBuilder, AuthenticationService authenticationService) {
        super(restTemplateBuilder, authenticationService);
    }

    @Override
    String getAttributeName() {
        return "transaction_list_result";
    }

    @Override
    String getTemplateName() {
        return "transactionListResult";
    }

    @Override
    Class getResponseClass() {
        return TransactionListResponse.class;
    }

    @Override
    String getResourceUrl() {
        return resourceUrl;
    }
}
