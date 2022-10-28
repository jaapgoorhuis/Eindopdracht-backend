package com.example.Eindopdracht.dto;

public class CarPartDto {
    public String name;

    public float price;

    public String serial_number;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getName() {
        return name;
    }
}
