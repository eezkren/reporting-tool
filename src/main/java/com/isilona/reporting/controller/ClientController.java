package com.isilona.reporting.controller;

import com.isilona.reporting.dto.ClientRequest;
import com.isilona.reporting.service.ClientService;
import com.isilona.reporting.util.ApiMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@Controller
@RequestMapping(value = ApiMappings.CLIENT)
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String clientForm(Model model) {
        model.addAttribute("client_request", new ClientRequest());
        return "client";
    }

    @PostMapping
    public DeferredResult<String> clientRequestSubmit(@Valid ClientRequest clientRequest, Model model) {
        DeferredResult<String> result = new DeferredResult<>();
        clientService.getClient(clientRequest, result, model);
        return result;
    }
}
