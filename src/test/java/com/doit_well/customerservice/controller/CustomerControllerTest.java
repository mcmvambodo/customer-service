package com.doit_well.customerservice.controller;

import com.doit_well.customerservice.db.CustomerTestDatabase;
import com.doit_well.customerservice.dtos.CustomerDto;
import com.doit_well.customerservice.entity.CreateCustomerRequest;
import com.doit_well.customerservice.entity.Customer;
import com.doit_well.customerservice.entity.UpdateCustomerRequest;
import com.doit_well.customerservice.helper.Common;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerControllerTest {

    private static final String SECRET = "";
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    void givenCreateCustomerRequest_whenCreateCustomerWithoutUser_thenReturnCustomerDto() throws Exception {
        //given
        CreateCustomerRequest customerRequest = CustomerTestDatabase.createCustomerRequestWithNoAccount;

        Map<String,String> headersMap = new HashMap<>();
        headersMap.put("Authorization", SECRET);
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersMap);

        //when
        this.mockMvc.perform(MockMvcRequestBuilders.post("/"+Common.BASE_PATH+"/customer")
                .headers(headers)
                        .characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(customerRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                        .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.firstname",is(customerRequest.firstname())));

    }

    @Test
    void givenCreateCustomerRequest_whenCreateCustomerWithUser_thenReturnCustomerDto() throws Exception {
        //given
        CreateCustomerRequest customerRequest = CustomerTestDatabase.createCustomerRequestWithAccount;

        Map headersMap = new HashMap();
        headersMap.put("Authorization", SECRET);
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersMap);
        // when
        mockMvc.perform(post("/"+Common.BASE_PATH+"/customer")
                .headers(headers)
                .content(mapper.writeValueAsString(customerRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.hasAccount", is(true)));
    }

    @Test
    void givenUpdateCustomerRequest_whenUpdateCustomer_thenReturnCustomerDtoWithStatusOK() throws Exception {
        //given
        UpdateCustomerRequest customerRequest = CustomerTestDatabase.updateRequest;

        Map headersMap = new HashMap();
        headersMap.put("Authorization",SECRET);
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersMap);

        //when
        mockMvc.perform(put("/"+Common.BASE_PATH + "/customer")
                .headers(headers)
                .content(mapper.writeValueAsString(customerRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname",is(customerRequest.lastname())));
    }
}
