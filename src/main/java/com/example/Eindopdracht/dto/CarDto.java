package com.example.Eindopdracht.dto;

import com.example.Eindopdracht.model.Customer;
import com.example.Eindopdracht.model.RepairActivity;

import java.util.Set;

public class CarDto {
    public Long id;

    public String brand;

    public String licenseplate;

    public Customer customer;

    public Set<RepairActivity> repairActivity;

    public CarDto(Long id, String brand, String licenseplate) {
        this.id = id;
        this.licenseplate = licenseplate;
        this.brand = brand;
    }

    public CarDto() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRepairActivity(Set<RepairActivity> repairActivity) {
        this.repairActivity = repairActivity;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getLicenseplate() {
        return licenseplate;
    }
}
