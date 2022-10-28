package com.example.Eindopdracht.controller;

import com.example.Eindopdracht.dto.AppointmentDto;
import com.example.Eindopdracht.dto.AppointmentInputDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> createAppointment(@Valid @RequestBody AppointmentInputDto dto) {
        try {
            service.createAppointment(dto);
            return ResponseEntity.status(HttpStatus.OK).body(service.createAppointment(dto));
        } catch(RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car id not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentInputDto dto) {
        try {
            AppointmentDto appointment = service.updateAppointment(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(appointment);
        } catch(RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment id not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable Long id) {
        try {
            service.deleteAppointment(id);
            return ResponseEntity.status(HttpStatus.OK).body("Appointment deleted");
        }catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No appointment found");
        }
    }


    @GetMapping
    public ResponseEntity<Collection<AppointmentDto>> getAppointments() {
        Collection<AppointmentDto> dtos;
        dtos = service.getAllAppointments();
        if (!dtos.isEmpty()) {
            return ResponseEntity.ok().body(dtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAppointment(@PathVariable Long id) {
        try {
            AppointmentDto appointment = service.getAppointment(id);
            return ResponseEntity.ok().body(appointment);
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No appointment found");
        }
    }
}
