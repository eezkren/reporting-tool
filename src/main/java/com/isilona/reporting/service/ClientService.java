package com.isilona.reporting.service;

import com.isilona.reporting.dao.psp.response.ClientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractService {

    @Value("${external-api.psp.url.client}")
    private String resourceUrl;

    public ClientService(RestTemplateBuilder restTemplateBuilder, AuthenticationService authenticationService) {
        super(restTemplateBuilder, authenticationService);
    }

    @Override
    String getAttributeName() {
        return "client_result";
    }

    @Override
    String getTemplateName() {
        return "clientResult";
    }

    @Override
    Class getResponseClass() {
        return ClientResponse.class;
    }

    @Override
    String getResourceUrl() {
        return resourceUrl;
    }
}
