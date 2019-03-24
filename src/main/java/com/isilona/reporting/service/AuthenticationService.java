package com.isilona.reporting.service;

import com.isilona.reporting.dao.psp.LoginResponse;
import com.isilona.reporting.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AuthenticationService {

    private final RestTemplate restTemplate;
    private final LoginRequest loginRequest;

    @Value("${external-api.psp.url.merchant.user.login}")
    private String resourceUrl;
    @Value("${external-api.psp.token-expiry-time}")
    private long tokenExpiryTime;

    private LocalDateTime tokenCreated;
    private String token;

    public AuthenticationService(RestTemplateBuilder restTemplateBuilder, LoginRequest loginRequest) {
        this.restTemplate = restTemplateBuilder.build();
        this.loginRequest = loginRequest;
    }

    public String getToken() {
        if (isTokenExpired()) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<LoginRequest> request = new HttpEntity(loginRequest, headers);
            LoginResponse result = this.restTemplate.postForObject(resourceUrl, request, LoginResponse.class);

            this.tokenCreated = LocalDateTime.now();
            this.token = result.getToken();
        }
        return this.token;
    }

    private boolean isTokenExpired() {
        if (tokenCreated == null || token == null) {
            return true;
        }
        return ChronoUnit.MILLIS.between(tokenCreated, LocalDateTime.now()) > tokenExpiryTime;
    }
}
