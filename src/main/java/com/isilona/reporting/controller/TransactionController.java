package com.isilona.reporting.controller;

import com.isilona.reporting.dto.TransactionRequest;
import com.isilona.reporting.dto.TransactionsReportRequest;
import com.isilona.reporting.service.TransactionService;
import com.isilona.reporting.service.TransactionsReportService;
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
@RequestMapping(value = ApiMappings.TRANSACTION)
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public String transactionsReportForm(Model model) {
        model.addAttribute("transaction_request", new TransactionRequest());
        return "transaction";
    }

    @PostMapping
    public DeferredResult<String> transactionRequestSubmit(@Valid TransactionRequest transactionRequest, Model model) {
        DeferredResult<String> result = new DeferredResult<>();
        transactionService.getData(transactionRequest, result, model);
        return result;
    }
}
