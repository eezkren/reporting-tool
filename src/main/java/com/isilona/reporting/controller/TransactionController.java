package com.isilona.reporting.controller;

import com.isilona.reporting.dto.TransactionListRequest;
import com.isilona.reporting.dto.TransactionRequest;
import com.isilona.reporting.service.TransactionListService;
import com.isilona.reporting.service.TransactionService;
import com.isilona.reporting.util.ApiMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;

@Controller
@RequestMapping(value = ApiMappings.TRANSACTION)
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionListService transactionListService;


    @Autowired
    public TransactionController(TransactionService transactionService, TransactionListService transactionListService) {
        this.transactionService = transactionService;
        this.transactionListService = transactionListService;
    }

    @GetMapping
    public String transactionForm(Model model) {
        model.addAttribute("transaction_request", new TransactionRequest());
        return "transaction";
    }

    @PostMapping
    public DeferredResult<String> transactionRequestSubmit(@Valid TransactionRequest transactionRequest, Model model) {
        DeferredResult<String> result = new DeferredResult<>();
        transactionService.getData(transactionRequest, result, model);
        return result;
    }

    @GetMapping("/list")
    public String transactionListForm(Model model) {
        model.addAttribute("transaction_list_request", new TransactionListRequest());
        return "transactionList";
    }

    @PostMapping("/list")
    public DeferredResult<String> transactionListRequestSubmit(@Valid TransactionListRequest transactionListRequest, Model model) {
        DeferredResult<String> result = new DeferredResult<>();
        transactionListService.getData(transactionListRequest, result, model);
        return result;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
