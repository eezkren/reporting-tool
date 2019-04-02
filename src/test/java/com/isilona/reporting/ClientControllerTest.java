package com.isilona.reporting;

import com.isilona.reporting.dao.psp.CustomerInfo;
import com.isilona.reporting.dao.psp.response.ClientResponse;
import com.isilona.reporting.dto.ClientRequest;
import com.isilona.reporting.service.ClientService;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {


    @MockBean
    ClientService clientService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void clientGet_ShouldAddClientRequestToModelAndRenderClientSearchView() throws Exception {
        mockMvc.perform(get("/client"))
                .andExpect(status().isOk())
                .andExpect(view().name("client"))
                .andExpect(model().attribute("client_request", is(instanceOf(ClientRequest.class))))
                .andExpect(model().attribute("client_request",
                        allOf(
                                hasProperty("transactionId", nullValue())
                        )
                ));
    }

    @Test
    public void clientPost_ShouldAddClientResponseToModelAndRenderClientResultView() throws Exception {

        ClientResponse clientResponse = new ClientResponse();

        CustomerInfo customerInfo = new CustomerInfo();
        clientResponse.setCustomerInfo(customerInfo);

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            ((DeferredResult) args[1]).setResult("clientResult");
            ((Model) args[2]).addAttribute("client_result", clientResponse);
            return null; // void method in a block-style lambda, so return null
        }).when(clientService).getData(any(), any(), any());

        MvcResult mvcResult = mockMvc.perform(post("/client").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("transactionId", "1011028-1539357144-1293"))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(view().name("clientResult"))
                .andExpect(model().attribute("client_result", allOf(
                        is(instanceOf(ClientResponse.class)),
                        hasProperty("customerInfo", is(notNullValue()))
                )));
    }


}
