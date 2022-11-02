package com.example.Eindopdracht.dto;

import com.example.Eindopdracht.model.Customer;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class CarInputDto {
    public Long id;

    @NotEmpty
    public String brand;

    @NotEmpty
    @Column(unique = true)
    public String licenseplate;

    public Customer customer;

    public CarInputDto(Long id, String brand, String licenseplate) {
        this.id = id;
        this.licenseplate = licenseplate;
        this.brand = brand;
    }

    public CarInputDto(Long id, String brand, String licenseplate, Customer customer) {
        this.id = id;
        this.licenseplate = licenseplate;
        this.brand = brand;
        this.customer = customer;
    }

    public CarInputDto() {

    }

    public void setId(Long id) {
        this.id = id;
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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public Customer getCustomer() {
        return customer;
    }
}
