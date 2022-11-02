package com.example.Eindopdracht.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String licenseplate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cars")
    @LazyCollection(LazyCollectionOption.TRUE)
    @JsonIgnore
    private Set<RepairActivity> repairActivities;

    @OneToOne(mappedBy = "car")
    @JsonBackReference
    private Appointment appointment;


    public Car(Long id, String brand, String licenseplate) {
        this.id = id;
        this.licenseplate = licenseplate;
        this.brand = brand;
    }

    public Car(Long id, String brand, String licenseplate, Customer customer) {
        this.customer = customer;
        this.id = id;
        this.licenseplate = licenseplate;
        this.brand = brand;
    }

    public Car(String brand, String licenseplate) {
        this.licenseplate = licenseplate;
        this.brand = brand;
    }

    public Car() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<RepairActivity> getRepairActivities() {
        return repairActivities;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
