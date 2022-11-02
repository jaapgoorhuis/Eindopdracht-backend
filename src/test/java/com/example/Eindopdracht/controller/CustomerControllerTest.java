package com.example.Eindopdracht.controller;

import com.example.Eindopdracht.dto.CustomerDto;
import com.example.Eindopdracht.dto.CustomerInputDto;
import com.example.Eindopdracht.model.Customer;
import com.example.Eindopdracht.security.JwtService;
import com.example.Eindopdracht.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
class CustomerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    private CustomerService service;

    @MockBean
    JwtService jwtService;

    Customer customer1;

    CustomerDto customerDto1;
    CustomerDto customerDto2;

    CustomerInputDto customerInputDto1;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        customer1 = new Customer(1L,"test@test.nl", "test", "gebruiker", "testweg 12", "teststad", "1234AB");

        customerDto1 = new CustomerDto(1L,"test@test.nl", "test", "gebruiker", "testweg 12", "teststad", "1234AB");
        customerDto2 = new CustomerDto(1L,"test@test.nl", "Nieuwe naam", "nieuwe achternaam", "testweg 12", "teststad", "1234AB");

        customerInputDto1 = new CustomerInputDto(1L,"test@test.nl", "test", "gebruiker", "testweg 12", "teststad", "1234AB");
    }

    @Test
    @WithMockUser
    void saveCustomer() throws Exception {
        given(service.createCustomer(any(CustomerInputDto.class))).willReturn(customerDto1);

        mockMvc.perform(post("/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(customerInputDto1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("email").value("test@test.nl"))
                .andExpect(jsonPath("firstname").value("test"))
                .andExpect(jsonPath("lastname").value("gebruiker"))
                .andExpect(jsonPath("streetname").value("testweg 12"))
                .andExpect(jsonPath("town").value("teststad"))
                .andExpect(jsonPath("zipcode").value("1234AB"));
    }

    @Test
    void updateCustomer() throws Exception {
        given(service.updateCustomer(1L,customerInputDto1)).willReturn(customerDto2);

        mockMvc.perform(put("/customers/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(customerInputDto1)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getCustomers() throws Exception {
        given(service.getAllCustomers()).willReturn(List.of(customerDto1));

        mockMvc.perform(get("/customers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].email").value("test@test.nl"))
            .andExpect(jsonPath("$[0].firstname").value("test"))
            .andExpect(jsonPath("$[0].lastname").value("gebruiker"))
            .andExpect(jsonPath("$[0].streetname").value("testweg 12"))
            .andExpect(jsonPath("$[0].town").value("teststad"))
            .andExpect(jsonPath("$[0].zipcode").value("1234AB"));
    }

    @Test
    @WithMockUser
    void getOneCustomer() throws Exception {
        Long id = 1L;
        given(service.getOneCustomer(id)).willReturn(customerDto1);
        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("email").value("test@test.nl"))
                .andExpect(jsonPath("firstname").value("test"))
                .andExpect(jsonPath("lastname").value("gebruiker"))
                .andExpect(jsonPath("streetname").value("testweg 12"))
                .andExpect(jsonPath("town").value("teststad"))
                .andExpect(jsonPath("zipcode").value("1234AB"));
    }

    @Test
    @WithMockUser
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}