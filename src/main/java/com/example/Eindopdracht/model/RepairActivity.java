package com.example.Eindopdracht.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="repair_activity")
public class RepairActivity {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private String title;

    private String status;

    private float hour;

    private float hour_price;

    private String notes;

    @ManyToOne
    private Car cars;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<CarPart> carParts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<CarPart> getCarParts() {
        return carParts;
    }

    public void setCarParts(Collection<CarPart> carParts) {
        this.carParts = carParts;
    }

    public float getHour() {
        return hour;
    }

    public void setHour(float hour) {
        this.hour = hour;
    }

    public float getHour_price() {
        return hour_price;
    }

    public void setHour_price(float hour_price) {
        this.hour_price = hour_price;
    }

    public Car getCars() {
        return cars;
    }

    public void setCars(Car cars) {
        this.cars = cars;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
