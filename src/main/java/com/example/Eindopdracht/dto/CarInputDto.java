package com.example.Eindopdracht.dto;

import com.example.Eindopdracht.model.Customer;

import javax.validation.constraints.NotEmpty;

public class CarInputDto {
    public Long id;

    @NotEmpty
    public String brand;

    @NotEmpty
    public String licenseplate;

    public Customer customer;

    public CarInputDto(String brand, String licenseplate) {
        this.licenseplate = licenseplate;
        this.brand = brand;
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

}
