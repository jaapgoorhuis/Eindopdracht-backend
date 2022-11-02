package com.example.Eindopdracht.dto;

public class CarPartDto {
    public String name;

    public float price;

    public String serialnumber;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getName() {
        return name;
    }
}
