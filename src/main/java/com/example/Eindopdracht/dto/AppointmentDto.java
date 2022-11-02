package com.example.Eindopdracht.dto;

import com.example.Eindopdracht.model.Car;

import java.time.LocalDate;

public class AppointmentDto {

    public Long id;

    public LocalDate finish_date;

    public String notes;

    public String status;

    public String type_appointment;

    public Car car;


    public AppointmentDto(Long id, LocalDate finish_date, String notes, String status, String type_appointment, Car car) {
        this.id = id;
        this.finish_date = finish_date;
        this.notes = notes;
        this.status = status;
        this.type_appointment = type_appointment;
        this.car = car;
    }

    public AppointmentDto(LocalDate finish_date, String notes, String status, String type_appointment, Car car) {

        this.finish_date = finish_date;
        this.notes = notes;
        this.status = status;
        this.type_appointment = type_appointment;
        this.car = car;
    }


    public AppointmentDto(){}

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

    public String getStatus() {
        return status;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public String getNotes() {
        return notes;
    }
    public void setType_appointment(String type_appointment) {
        this.type_appointment = type_appointment;
    }

    public String getType_appointment() {
        return type_appointment;
    }

    public Long getId() {
        return id;
    }
}
