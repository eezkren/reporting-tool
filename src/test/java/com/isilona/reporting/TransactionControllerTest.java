package com.isilona.reporting;

import com.isilona.reporting.dao.psp.*;
import com.isilona.reporting.dao.psp.response.TransactionListResponse;
import com.isilona.reporting.dao.psp.response.TransactionResponse;
import com.isilona.reporting.dto.TransactionListRequest;
import com.isilona.reporting.dto.TransactionRequest;
import com.isilona.reporting.service.TransactionListService;
import com.isilona.reporting.service.TransactionService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class TransactionControllerTest {


    @MockBean
    TransactionListService transactionListService;

    @MockBean
    TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void transactionListGet_ShouldAddTransactionListRequestToModelAndRenderTransactionListSearchView() throws Exception {
        mockMvc.perform(get("/transaction/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("transactionList"))
                .andExpect(model().attribute("transaction_list_request", is(instanceOf(TransactionListRequest.class))))
                .andExpect(model().attribute("transaction_list_request",
                        allOf(
                                hasProperty("fromDate", nullValue()),
                                hasProperty("toDate", nullValue()),
                                hasProperty("status", nullValue()),
                                hasProperty("merchantId", nullValue()),
                                hasProperty("acquirerId", nullValue())
                        )
                ));
    }

    @Test
    public void transactionListPost_ShouldAddTransactionListResponseToModelAndRenderTransactionListResultView() throws Exception {

        TransactionListResponse transactionListResponse = new TransactionListResponse();
        transactionListResponse.setPerPage(50);
        transactionListResponse.setCurrentPage(1);
        transactionListResponse.setNextPageUrl("https://sandbox-reporting.rpdpymnt.com/api/v3/transaction/list?page=2");
        transactionListResponse.setPrevPageUrl(null);
        transactionListResponse.setFrom(1);
        transactionListResponse.setTo(50);

        TransactionData transactionData = new TransactionData();

        CustomerInfo customerInfo = new CustomerInfo();
        transactionData.setCustomerInfo(customerInfo);

        transactionData.setUpdatedAt(LocalDateTime.parse("2018-10-12 15:12:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        transactionData.setCreatedAt(LocalDateTime.parse("2018-10-12 15:12:24", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Fx fx = new Fx();
        transactionData.setFx(fx);

        Acquirer acquirer = new Acquirer();
        transactionData.setAcquirer(acquirer);

        Transaction transaction = new Transaction();
        transactionData.setTransaction(transaction);

        transactionData.setRefundable(false);

        Merchant merchant = new Merchant();
        transactionData.setMerchant(merchant);

        Ipn ipn = new Ipn();
        transactionData.setIpn(ipn);

        transactionListResponse.setData(Stream.of(transactionData).collect(Collectors.toList()));


        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((DeferredResult) args[1]).setResult("transactionListResult");
            ((Model) args[2]).addAttribute("transaction_list_result", transactionListResponse);
            return null; // void method in a block-style lambda, so return null
        }).when(transactionListService).getData(any(), any(), any());

        MvcResult mvcResult = mockMvc.perform(post("/transaction/list").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("fromDate", "2018-01-29")
                .param("toDate", "2020-10-01"))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(view().name("transactionListResult"))
                .andExpect(model().attribute("transaction_list_result", allOf(
                        is(instanceOf(TransactionListResponse.class)),
                        hasProperty("perPage", is(50))
                )));
    }

    @Test
    public void transactionGet_ShouldAddTransactionRequestToModelAndRenderTransactionSearchView() throws Exception {
        mockMvc.perform(get("/transaction"))
                .andExpect(status().isOk())
                .andExpect(view().name("transaction"))
                .andExpect(model().attribute("transaction_request", is(instanceOf(TransactionRequest.class))))
                .andExpect(model().attribute("transaction_request",
                        allOf(
                                hasProperty("transactionId", nullValue())
                        )
                ));
    }

    @Test
    public void transactionPost_ShouldAddTransactionResponseToModelAndRenderTransactionResultView() throws Exception {

        TransactionResponse transactionResponse = new TransactionResponse();

        CustomerInfo customerInfo = new CustomerInfo();
        transactionResponse.setCustomerInfo(customerInfo);

        Fx fx = new Fx();
        transactionResponse.setFx(fx);

        Merchant merchant = new Merchant();
        transactionResponse.setMerchant(merchant);

        Transaction transaction = new Transaction();
        transactionResponse.setTransaction(transaction);


        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((DeferredResult) args[1]).setResult("transactionResult");
            ((Model) args[2]).addAttribute("transaction_result", transactionResponse);
            return null; // void method in a block-style lambda, so return null
        }).when(transactionService).getData(any(), any(), any());

        MvcResult mvcResult = mockMvc.perform(post("/transaction").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("transactionId", "1011028-1539357144-1293"))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(view().name("transactionResult"))
                .andExpect(model().attribute("transaction_result", allOf(
                        is(instanceOf(TransactionResponse.class)),
                        hasProperty("customerInfo", is(notNullValue()))
                )));
    }


}
