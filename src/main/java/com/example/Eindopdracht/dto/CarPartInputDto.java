package com.example.Eindopdracht.dto;

import javax.persistence.Column;

public class CarPartInputDto {

    public String name;

    public float price;

    @Column(unique = true)
    public String serialnumber;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

}
