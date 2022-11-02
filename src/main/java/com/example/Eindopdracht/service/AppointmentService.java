package com.example.Eindopdracht.service;

import com.example.Eindopdracht.dto.*;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.model.Appointment;
import com.example.Eindopdracht.repository.AppointmentRepository;
import com.example.Eindopdracht.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepos;
    private final CarRepository carRepos;

    public AppointmentService(AppointmentRepository repos, CarRepository carRepos) {
        this.appointmentRepos = repos;
        this.carRepos = carRepos;
    }

    public AppointmentDto createAppointment(AppointmentInputDto dto) {
        Appointment appointment = transferToAppointment(dto);
        appointmentRepos.save(appointment);
        return transferToDto(appointment);
    }

    public AppointmentDto updateAppointment(AppointmentInputDto dto,Long id) {
        if(appointmentRepos.findById(id).isPresent()) {
            Appointment appointment = appointmentRepos.findById(id).get();

            Appointment updatedAppointment = transferToAppointment(dto);
            updatedAppointment.setId(appointment.getId());

            appointmentRepos.save(updatedAppointment);

            return transferToDto(updatedAppointment);
        } else {
            throw new RecordNotFoundException("no appointment found");
        }

    }

    public void deleteAppointment(Long id) {
        appointmentRepos.deleteById(id);
    }

    public List<AppointmentDto> getAllAppointments() {

        List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        List<Appointment> appointmentList = (List<Appointment>) appointmentRepos.findAll();
        for (Appointment appointment : appointmentList) {
            AppointmentDto dto = transferToDto(appointment);
            appointmentDtoList.add(dto);
        }
        return appointmentDtoList;
    }

    public AppointmentDto getAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepos.findById(id);
        if (appointment.isPresent()) {
            Appointment a = appointment.get();
            return transferToDto(a);
        } else {
            throw new RecordNotFoundException("Appointment not found");
        }
    }

    public AppointmentDto transferToDto(Appointment appointment) {
        AppointmentDto dto = new AppointmentDto();
        dto.setType_appointment(appointment.getType_appointment());
        dto.setFinish_date(appointment.getFinish_date());
        dto.setNotes(appointment.getNotes());
        dto.setStatus(appointment.getStatus());
        dto.setCar(appointment.getCar());
        return dto;
    }

    public Appointment transferToAppointment(AppointmentInputDto dto) {
        if (carRepos.findById(dto.getCar().getId()).isPresent()) {
            Appointment appointment = new Appointment();
            appointment.setType_appointment(dto.getType_appointment());
            appointment.setFinish_date(dto.getFinish_date());
            appointment.setNotes(dto.getNotes());
            appointment.setStatus(dto.getStatus());

            appointment.setCar(dto.car);
            appointment.setCar(dto.getCar());

            return appointment;
        } else {
            throw new RecordNotFoundException("car not found");
        }
    }
}
