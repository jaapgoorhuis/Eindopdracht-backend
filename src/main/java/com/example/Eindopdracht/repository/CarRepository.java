package com.example.Eindopdracht.repository;

import com.example.Eindopdracht.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByLicenseplate(String licenseplate);
    Car findCarByLicenseplate(String licenseplate);
}
