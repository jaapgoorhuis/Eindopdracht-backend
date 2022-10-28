package com.example.Eindopdracht.repository;

import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.model.Customer;
import com.example.Eindopdracht.model.RepairActivity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
