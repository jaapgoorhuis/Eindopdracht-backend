package com.example.Eindopdracht.controller;

import com.example.Eindopdracht.dto.CarDto;
import com.example.Eindopdracht.dto.CarInputDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @PostMapping("cars")
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarInputDto dto) {
        try {
            CarDto car = service.createCar(dto);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch(RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer id not found");
        }
    }

    @PutMapping("cars/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable Long id, @Valid @RequestBody CarInputDto dto) {
        try {
            CarDto car = service.updateCar(id, dto);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch(RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer id not found");
        }
    }

    @DeleteMapping("cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable Long id) {
        try {
            service.deleteCar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Car deleted");
        }catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car found");
        }
    }

    @GetMapping("cars")
    public ResponseEntity<Collection<CarDto>> getCars() {
        Collection<CarDto> dtos;
        dtos = service.getAllCars();
        if (!dtos.isEmpty()) {
            return ResponseEntity.ok().body(dtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("cars/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable Long id) {
        try {
            CarDto car = service.getCar(id);
            return ResponseEntity.ok().body(car);
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car found");
        }
    }
}
