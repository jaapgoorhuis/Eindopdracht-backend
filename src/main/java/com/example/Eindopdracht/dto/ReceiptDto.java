package com.example.Eindopdracht.dto;

import com.example.Eindopdracht.model.Appointment;
import com.example.Eindopdracht.model.Customer;
import com.example.Eindopdracht.model.RepairActivity;

import java.util.Set;

public class ReceiptDto {
    public Long id;

    public Customer customer;

    public Appointment appointment;

    public Set<RepairActivity> repairActivity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRepairActivity(Set<RepairActivity> repairActivity) {
        this.repairActivity = repairActivity;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Customer getCustomer() {
        return customer;
    }

}
