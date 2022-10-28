package com.example.Eindopdracht.dto;
import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.model.CarPart;

import java.util.Collection;
public class RepairActivityDto {

    public String description;

    public float hour;

    public float hour_price;

    public String status;

    public String title;

    public String notes;

    public Car cars;

    public Collection<CarPart> carParts;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHour(float hour) {
        this.hour = hour;
    }

    public void setHour_price(float hour_price) {
        this.hour_price = hour_price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCarParts(Collection<CarPart> carParts) {
        this.carParts = carParts;
    }

    public void setCars(Car cars) {
        this.cars = cars;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }
}
