package com.isilona.reporting.service;

import com.isilona.reporting.dao.psp.TransactionsReportResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class TransactionsReportService extends AbstractService {

    @Value("${external-api.psp.url.transactions.report}")
    private String resourceUrl;

    public TransactionsReportService(RestTemplateBuilder restTemplateBuilder, AuthenticationService authenticationService) {
        super(restTemplateBuilder, authenticationService);
    }

    @Override
    String getAttributeName() {
        return "transactions_report_result";
    }

    @Override
    String getTemplateName() {
        return "transactionsReportResult";
    }

    @Override
    Class getResponseClass() {
        return TransactionsReportResponse.class;
    }

    @Override
    String getResourceUrl() {
        return resourceUrl;
    }
}
