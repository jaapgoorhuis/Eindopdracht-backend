package com.example.Eindopdracht.repository;

import com.example.Eindopdracht.model.CarPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPartRepository extends JpaRepository<CarPart, Long> {
}
