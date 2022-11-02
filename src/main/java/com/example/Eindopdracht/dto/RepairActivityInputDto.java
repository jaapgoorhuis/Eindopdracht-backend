package com.example.Eindopdracht.dto;

import com.example.Eindopdracht.model.Car;

public class RepairActivityInputDto {

    public String description;

    public float hour;

    public float hour_price;

    public String status;

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

    public static Long[] getCarParts() {
        return carParts;
    }

    public  void setCarParts(Long[] carParts) {
        this.carParts = carParts;
    }
}
