package com.isilona.reporting;

import com.isilona.reporting.dao.psp.TransactionsReport;
import com.isilona.reporting.dao.psp.TransactionsReportResponse;
import com.isilona.reporting.dto.TransactionsReportRequest;
import com.isilona.reporting.service.TransactionsReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.ui.Model;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionsControllerTest {


    @MockBean
    TransactionsReportService transactionsReportService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void transactionsReportPost_ShouldAddTransactionsReportResponseToModelAndRenderTransactionsReportResultView() throws Exception {

        TransactionsReport reportEUR = new TransactionsReport();
        reportEUR.setCount(1);
        reportEUR.setCurrency("EUR");
        reportEUR.setTotal(100L);

        TransactionsReportResponse response = new TransactionsReportResponse();
        response.setStatus("APPROVED");
        response.setResponse(Stream.of(reportEUR).collect(Collectors.toList()));

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((DeferredResult) args[1]).setResult("transactionsReportResult");
            ((Model) args[2]).addAttribute("transactions_report_result", response);
            return null; // void method in a block-style lambda, so return null
        }).when(transactionsReportService).getData(any(), any(), any());

        MvcResult mvcResult = mockMvc.perform(post("/transactions/report").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("fromDate", "2018-01-29")
                .param("toDate", "2020-10-01"))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(view().name("transactionsReportResult"))
                .andExpect(model().attribute("transactions_report_result", allOf(
                        is(instanceOf(TransactionsReportResponse.class)),
                        hasProperty("status", is("APPROVED")),
                        hasProperty("response", hasSize(1))
                )));
    }

    @Test
    public void transactionsReportGet_ShouldAddTransactionsReportRequestToModelAndRenderTransactionsReportSearchView() throws Exception {
        mockMvc.perform(get("/transactions/report"))
                .andExpect(status().isOk())
                .andExpect(view().name("transactionsReport"))
                .andExpect(model().attribute("transactions_report_request", is(instanceOf(TransactionsReportRequest.class))))
                .andExpect(model().attribute("transactions_report_request",
                        allOf(
                                hasProperty("fromDate", nullValue()),
                                hasProperty("toDate", nullValue()),
                                hasProperty("merchant", nullValue()),
                                hasProperty("acquirer", nullValue())
                        )
                ));
    }
}
