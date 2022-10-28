package com.example.Eindopdracht.controller;

import com.example.Eindopdracht.dto.AppointmentDto;
import com.example.Eindopdracht.dto.AppointmentInputDto;
import com.example.Eindopdracht.dto.CarDto;
import com.example.Eindopdracht.dto.CarInputDto;
import com.example.Eindopdracht.model.Appointment;
import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.security.JwtService;
import com.example.Eindopdracht.service.AppointmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AppointmentController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)

class AppointmentControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private AppointmentService service;

    @MockBean
    JwtService jwtService;

    Car car1;
    Car car2;

    CarInputDto carInputDto1;
    CarInputDto carInputDto2;

    CarDto carDto1;
    CarDto carDto2;

    Appointment appointment1;
    Appointment appointment2;

    AppointmentDto appointmentDto1;
    AppointmentDto appointmentDto2;
    AppointmentDto appointmentDto3;

    AppointmentInputDto appointmentInputDto1;
    AppointmentInputDto appointmentInputDto2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        car1 = new Car(1L,"Ford escord","04-23G-G");
        car2 = new Car(2L,"BMW","04-23G-G");

        carDto1 = new CarDto("Ford escord","04-23G-G");
        carDto2 = new CarDto("BMW","04-23G-G");

        carInputDto1 = new CarInputDto("Ford escord","04-23G-G");
        carInputDto2 = new CarInputDto("BMW","04-23G-G");

        appointment1 = new Appointment(1L, LocalDate.of(2020,02,02),"","finished","reparatie", car1);
        appointment2 = new Appointment(1L,LocalDate.of(2022,05,06),"","not_finished","keuring", car2);

        appointmentDto1 = new AppointmentDto(1L, LocalDate.of(2022,02,02),"","finished","reparatie",car1);
        appointmentDto3 = new AppointmentDto(1L, LocalDate.of(2022,02,02),"","finished","reparatie",car2);
        appointmentDto2 = new AppointmentDto(1L, LocalDate.of(2022,05,06),"","not_finished","keuring",car1);

        appointmentInputDto1 = new AppointmentInputDto(1L, LocalDate.of(2022,02,02),"","finished","reparatie", car1);
        appointmentInputDto2 = new AppointmentInputDto(1L, LocalDate.of(2022,05,06),"","not_finished","keuring",car2);
    }


    @Test
    @WithMockUser
    void getAllAppointments() throws Exception {

    given(service.getAllAppointments()).willReturn(List.of(appointmentDto1,appointmentDto2));

    mockMvc.perform(get("/appointments"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].type_appointment").value("reparatie"))
            .andExpect(jsonPath("$[0].finish_date").value("2022-02-02"))
            .andExpect(jsonPath("$[0].notes").value(""))
            .andExpect(jsonPath("$[0].status").value("finished"))
            .andExpect(jsonPath("$[0].carDto.brand").value("Ford escord"))
            .andExpect(jsonPath("$[0].carDto.licenseplate").value("04-23G-G"))
            .andExpect(jsonPath("$[1].type_appointment").value("keuring"))
            .andExpect(jsonPath("$[1].finish_date").value("2022-05-06"))
            .andExpect(jsonPath("$[1].notes").value(""))
            .andExpect(jsonPath("$[1].status").value("not_finished"));
    }

    @Test
    @WithMockUser
    void getAppointment() throws Exception {
        Long id = 1L;
        given(service.getAppointment(id)).willReturn(appointmentDto1);
        mockMvc.perform(get("/appointments/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("type_appointment").value("reparatie"))
                .andExpect(jsonPath("finish_date").value("2022-02-02"))
                .andExpect(jsonPath("notes").value(""))
                .andExpect(jsonPath("status").value("finished"))
                .andExpect(jsonPath("carDto.brand").value("Ford escord"))
                .andExpect(jsonPath("carDto.licenseplate").value("04-23G-G"));
    }

    @Test
    @WithMockUser
    void createAppointment() throws Exception {
        when(service.createAppointment(appointmentInputDto1)).thenReturn(appointmentDto1);
        mockMvc.perform(post("/appointments")

                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(asJsonString(appointmentInputDto1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("notes").value(""))
                .andExpect(jsonPath("status").value("finished"))
                .andExpect(jsonPath("type_appointment").value("reparatie"));
    }

    @Test
    @WithMockUser
    void updateAppointment() throws Exception {
        given(service.updateAppointment(1L, appointmentInputDto2)).willReturn(appointmentDto3);
        mockMvc.perform(MockMvcRequestBuilders.put("/appointments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(appointmentInputDto2)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("notes").value(""))
                .andExpect(jsonPath("status").value("finished"))
                .andExpect(jsonPath("type_appointment").value("reparatie"));
    }

    @Test
    @WithMockUser
    void deleteAppointment() throws Exception {
        mockMvc.perform(delete("/appointments/1"))
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