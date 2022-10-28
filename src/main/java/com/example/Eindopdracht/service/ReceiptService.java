package com.example.Eindopdracht.service;

import com.example.Eindopdracht.dto.ReceiptDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceiptService {
    private final CarRepository carRepos;

    public ReceiptService(CarRepository repos) {
        this.carRepos = repos;
    }

    public ReceiptDto getReceipt(Long id) {
        Optional<Car> car = carRepos.findById(id);
        if (car.isPresent()) {
            Car c = car.get();
            return transferToReceiptDto(c);
        } else {
            throw new RecordNotFoundException("car not found");
        }
    }

    public ReceiptDto transferToReceiptDto(Car car) {
        ReceiptDto dto = new ReceiptDto();
        dto.setRepairActivity(car.getRepairActivities());
        dto.setAppointment(car.getAppointment());
        return dto;
    }
}
