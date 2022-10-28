package com.example.Eindopdracht.model;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="car_parts")
public class CarPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String serial_number;

    private float price;

    @ManyToMany(mappedBy = "carParts")
    private Collection<RepairActivity> repairActivities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
