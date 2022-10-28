package com.example.Eindopdracht.dto;

import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.model.Customer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.Date;

public class AppointmentInputDto {

    public Long id;

    @NotNull

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate finish_date;

    public String notes;

    @NotEmpty
    public String status;

    @NotEmpty
    public String type_appointment;

    public Car car;

    public Customer customer;

    public AppointmentInputDto(Long id,LocalDate finish_date, String notes, String status, String type_appointment, Car car) {
        this.id = id;
        this.finish_date = finish_date;
        this.notes = notes;
        this.status = status;
        this.type_appointment = type_appointment;
        this.car = car;
    }

    public AppointmentInputDto(LocalDate finish_date, String notes, String status, String type_appointment) {

        this.finish_date = finish_date;
        this.notes = notes;
        this.status = status;
        this.type_appointment = type_appointment;

    }

    public AppointmentInputDto() {

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public String getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }

    public String getType_appointment() {
        return type_appointment;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
