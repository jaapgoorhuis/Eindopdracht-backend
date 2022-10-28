package com.example.Eindopdracht.repository;

import com.example.Eindopdracht.model.Appointment;
import com.example.Eindopdracht.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {



}
