package com.isilona.reporting.controller;

import com.isilona.reporting.dao.psp.TransactionsReportResponse;
import com.isilona.reporting.dto.TransactionsReportRequest;
import com.isilona.reporting.service.TransactionsReportService;
import com.isilona.reporting.util.ApiMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = ApiMappings.TRANSACTIONS)
public class TransactionsController {

    private final TransactionsReportService transactionsReportService;

    @Autowired
    public TransactionsController(TransactionsReportService transactionsReportService) {
        this.transactionsReportService = transactionsReportService;
    }

    @GetMapping("/report")
    public String transactionsReportForm(Model model) {
        model.addAttribute("transactions_report_request", new TransactionsReportRequest());
        return "transactionsReport";
    }

    @PostMapping("/report")
    public String greetingSubmit(@Valid TransactionsReportRequest transactionsReportRequest, Model model) {
        TransactionsReportResponse result = transactionsReportService.getTransactionsReport(transactionsReportRequest);
        model.addAttribute("transactions_report_result", result);
        return "transactionsReportResult";
    }
}
