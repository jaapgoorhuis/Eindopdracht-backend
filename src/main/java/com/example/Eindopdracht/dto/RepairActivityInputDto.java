package com.example.Eindopdracht.dto;

import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.model.Customer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RepairActivityInputDto {

    @NotEmpty
    public String description;

    @NotNull
    public float hour;

    @NotNull
    public float hour_price;

    @NotEmpty
    public String status;

    @NotEmpty
    public String title;

    public String notes;

    public Car cars;

    public static Long[] carParts;

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
