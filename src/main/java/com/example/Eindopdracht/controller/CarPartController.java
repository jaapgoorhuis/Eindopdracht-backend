package com.example.Eindopdracht.controller;

import com.example.Eindopdracht.dto.CarPartDto;
import com.example.Eindopdracht.dto.CarPartInputDto;
import com.example.Eindopdracht.exceptions.DuplicatedEntryException;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.service.CarPartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class CarPartController {

    private final CarPartService service;

    public CarPartController(CarPartService service) {
        this.service = service;
    }

    @PostMapping("carparts")
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarPartInputDto dto) {
        try {
            CarPartDto carPart = service.createCarPart(dto);
            return new ResponseEntity<>(carPart, HttpStatus.CREATED);
        } catch(RecordNotFoundException | DuplicatedEntryException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
    }

    @PutMapping("carparts/{id}")
    public ResponseEntity<Object> updateCarPart(@PathVariable Long id, @Validated @RequestBody CarPartInputDto dto) {
        try {
            CarPartDto carPart = service.updateCarPart(id, dto);
            return new ResponseEntity<>(carPart, HttpStatus.CREATED);
        } catch(RecordNotFoundException | DuplicatedEntryException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
    }

    @DeleteMapping("carparts/{id}")
    public ResponseEntity<Object> deleteCarPart(@PathVariable Long id) {
        try {
            service.deleteCarPart(id);
            return ResponseEntity.status(HttpStatus.OK).body("Carpart deleted");
        }catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No carpart found");
        }
    }

    @GetMapping("carparts")
    public ResponseEntity<Collection<CarPartDto>> getAllCarParts() {
        Collection<CarPartDto> dtos;
        dtos = service.getAllCarParts();
        if (!dtos.isEmpty()) {
            return ResponseEntity.ok().body(dtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("carparts/{id}")
    public ResponseEntity<Object> getOneCarPart(@PathVariable Long id) {
        try {
            CarPartDto carPart = service.getCarPart(id);
            return ResponseEntity.ok().body(carPart);
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No carpart found");
        }
    }
}
