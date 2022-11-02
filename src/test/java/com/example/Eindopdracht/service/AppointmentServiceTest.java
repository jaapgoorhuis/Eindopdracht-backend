package com.example.Eindopdracht.service;

import com.example.Eindopdracht.dto.AppointmentDto;
import com.example.Eindopdracht.dto.AppointmentInputDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.model.Appointment;
import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.repository.AppointmentRepository;
import com.example.Eindopdracht.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AppointmentServiceTest {

    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    AppointmentService appointmentService;

    @Captor
    ArgumentCaptor<Appointment> argumentCaptor;

    Appointment appointment1;
    Appointment appointment2;

    Car car1;
    Car car2;

    @BeforeEach
    void setUp() {
        car1 = new Car(1L,"Ford escord","04-23G-G");
        car2 = new Car(1L,"BMW","04-23G-G");

        appointment1 = new Appointment(1L, LocalDate.of(2020,02,02),"","finished","reparatie", car1);
        appointment2 = new Appointment(1L,LocalDate.of(2022,05,06),"","not_finished","keuring", car2);
    }

    @Test
    void createAppointment() {
        AppointmentInputDto dto = new AppointmentInputDto(1L,LocalDate.of(2020,02,02),"","finished","reparatie", car1);

        given(carRepository.findById(1L)).willReturn(Optional.of(car1));
        when(appointmentRepository.save(appointment1)).thenReturn(appointment1);

        appointmentService.createAppointment(dto);
        verify(appointmentRepository, times(1)).save(argumentCaptor.capture());
        Appointment appointment = argumentCaptor.getValue();

        assertEquals(appointment1.getType_appointment(), appointment.getType_appointment());
        assertEquals(appointment1.getNotes(), appointment.getNotes());
        assertEquals(appointment1.getStatus(), appointment.getStatus());
        assertEquals(appointment1.getFinish_date(), appointment.getFinish_date());
    }

    @Test
    void updateAppointment() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment1));
        given(carRepository.findById(1L)).willReturn(Optional.of(car1));
        AppointmentInputDto dto = new AppointmentInputDto(1L,LocalDate.of(2020,02,02),"","finished","reparatie", car1);

        when(appointmentRepository.save(appointmentService.transferToAppointment(dto))).thenReturn(appointment1);

        appointmentService.updateAppointment(dto,1L);

        verify(appointmentRepository, times(1)).save(argumentCaptor.capture());

        Appointment captured = argumentCaptor.getValue();

        assertEquals(dto.getType_appointment(), captured.getType_appointment());
        assertEquals(dto.getNotes(), captured.getNotes());
        assertEquals(dto.getStatus(), captured.getStatus());
        assertEquals(dto.getFinish_date(), captured.getFinish_date());
    }

    @Test
    void deleteAppointment() {
        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository).deleteById(1L);
    }

    @Test
    void getAllAppointments() {

        when(appointmentRepository.findAll()).thenReturn(List.of(appointment1, appointment2));

        List<Appointment> appointments = (List<Appointment>) appointmentRepository.findAll();
        List<AppointmentDto> dtos = appointmentService.getAllAppointments();

        assertEquals(appointments.get(0).getType_appointment(), dtos.get(0).getType_appointment());
        assertEquals(appointments.get(0).getFinish_date(), dtos.get(0).getFinish_date());
        assertEquals(appointments.get(0).getNotes(), dtos.get(0).getNotes());
        assertEquals(appointments.get(0).getStatus(), dtos.get(0).getStatus());
        assertEquals(appointments.get(0).getCar(), dtos.get(0).getCar());
    }

    @Test
    void getAppointment() {
       Long id = 1L;
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment2));

        Appointment appointment = appointmentRepository.findById(id).get();
        AppointmentDto dto = appointmentService.getAppointment(id);

        assertEquals(appointment.getCar(), dto.getCar());
        assertEquals(appointment.getType_appointment(), dto.getType_appointment());
        assertEquals(appointment.getStatus(), dto.getStatus());
        assertEquals(appointment.getNotes(), dto.getNotes());
        assertEquals(appointment.getFinish_date(), dto.getFinish_date());
    }

    @Test
    void updateAppointmentThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> appointmentService.updateAppointment(new AppointmentInputDto(1L, LocalDate.of(2020,02,02),"test","test","tes",car1), 1L));
    }

    @Test
    void getAppointmentThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> appointmentService.getAppointment(null));
    }

    @Test
    void updateAppointmentsThrowsExceptionForCarTest() {
        when(appointmentRepository.findById(any())).thenReturn(Optional.of(appointment1));
        assertThrows(RecordNotFoundException.class, () -> appointmentService.updateAppointment(new AppointmentInputDto(1L, LocalDate.of(2020,02,02),"test","test","tes",car1), 1L));
    }
}