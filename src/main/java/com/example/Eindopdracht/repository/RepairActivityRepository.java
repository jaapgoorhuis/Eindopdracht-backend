package com.example.Eindopdracht.repository;

import com.example.Eindopdracht.model.RepairActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairActivityRepository extends JpaRepository<RepairActivity, Long> {

}
