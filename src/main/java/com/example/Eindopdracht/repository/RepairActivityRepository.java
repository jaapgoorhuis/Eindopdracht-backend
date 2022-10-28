package com.example.Eindopdracht.repository;

import com.example.Eindopdracht.model.CarPart;
import com.example.Eindopdracht.model.RepairActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RepairActivityRepository extends JpaRepository<RepairActivity, Long> {

}
