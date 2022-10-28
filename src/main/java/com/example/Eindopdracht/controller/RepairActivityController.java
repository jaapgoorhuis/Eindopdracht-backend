package com.example.Eindopdracht.controller;

import com.example.Eindopdracht.dto.RepairActivityDto;
import com.example.Eindopdracht.dto.RepairActivityInputDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.service.RepairActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RepairActivityController {

    private final RepairActivityService service;

    public RepairActivityController(RepairActivityService service) {
        this.service = service;
    }

    @PostMapping("/repairactivities")
    public ResponseEntity<Object> createRepairActivity(@Valid @RequestBody RepairActivityInputDto dto) {
        try {
            RepairActivityDto repairActivityDto = service.createRepairActivity(dto);

            return new ResponseEntity<>(repairActivityDto, HttpStatus.CREATED);
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oeps something went wrong");
        }
    }

    @PutMapping("/repairactivities/{id}")
    public ResponseEntity<Object> updateRepairActivity(@PathVariable Long id, @Valid @RequestBody RepairActivityInputDto dto) {
        try {
            RepairActivityDto repairActivityDto = service.updateRepairActivity(id, dto);
            return ResponseEntity.ok().body(repairActivityDto);
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No repair activity found");
        }
    }

    @GetMapping("repairactivities")
    public ResponseEntity<List<RepairActivityDto>> getRepairActivities() {
        List<RepairActivityDto> dtos;
        dtos = service.getAllRepairActivities();

        if(!dtos.isEmpty()) {
            return ResponseEntity.ok().body(dtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("repairactivities/{id}")
    public ResponseEntity<Object> getOneRepairActivity(@PathVariable Long id) {
        try {
            RepairActivityDto repairActivity = service.getOneRepairActivity(id);
            return ResponseEntity.ok().body(repairActivity);
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No repair activity found");
        }
    }

    @DeleteMapping("repairactivities/{id}")
    public ResponseEntity<Object> deleteRepairActivity(@PathVariable Long id) {
        try {
            service.deleteRepairActivity(id);
            return ResponseEntity.status(HttpStatus.OK).body("Repair activity deleted");
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No repair activity found");
        }
    }
}
