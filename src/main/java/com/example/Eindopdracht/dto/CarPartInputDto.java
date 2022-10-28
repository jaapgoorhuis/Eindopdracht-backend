package com.example.Eindopdracht.dto;

import javax.validation.constraints.NotNull;

public class CarPartInputDto {

    @NotNull
    public String name;

    @NotNull
    public float price;

    @NotNull
    public String serial_number;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getSerial_number() {
        return serial_number;
    }

}
