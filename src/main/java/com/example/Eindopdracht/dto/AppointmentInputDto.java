package com.example.Eindopdracht.dto;

import com.example.Eindopdracht.model.Car;

import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
public class AppointmentInputDto {

    public Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate finish_date;

    public String notes;

    public String status;

    public String type_appointment;

    @Column(unique = true)
    public Car car;

    public AppointmentInputDto(Long id,LocalDate finish_date, String notes, String status, String type_appointment, Car car) {
        this.id = id;
        this.finish_date = finish_date;
        this.notes = notes;
        this.status = status;
        this.type_appointment = type_appointment;
        this.car = car;
    }

    public AppointmentInputDto() {

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setFinish_date(LocalDate finish_date) {
        this.finish_date = finish_date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

}
