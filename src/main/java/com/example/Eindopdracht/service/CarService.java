package com.example.Eindopdracht.service;

import com.example.Eindopdracht.dto.CarDto;
import com.example.Eindopdracht.dto.CarInputDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.model.Car;
import com.example.Eindopdracht.repository.CarRepository;
import com.example.Eindopdracht.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarService {

    private final CarRepository carRepos;
    private final CustomerRepository customerRepos;

    public CarService(CarRepository repos, CustomerRepository customRepos) {
        this.carRepos = repos;
        this.customerRepos = customRepos;
    }

    public CarDto createCar(CarInputDto dto) {
        Car car = transferToCar(dto);
        carRepos.save(car);
        return transferToDto(car);
    }

    public CarDto updateCar(Long id, CarInputDto dto) {
        if(carRepos.findById(id).isPresent()) {
            Car car = carRepos.findById(id).get();

            Car updatedCar = transferToCar(dto);
            updatedCar.setId(car.getId());

            carRepos.save(updatedCar);

            return transferToDto(updatedCar);
        } else {
            throw new RecordNotFoundException("no car found");
        }
    }

    public void deleteCar(Long id) {
        if(carRepos.findById(id).isPresent()) {
            carRepos.deleteById(id);

        } else {
            throw new RecordNotFoundException("no car found");
        }
    }

    public Collection<CarDto> getAllCars() {

        Collection<CarDto> carDtoList = new HashSet<>();
        Collection<Car> carList = carRepos.findAll();
        for (Car car : carList) {
            CarDto dto = transferToDto(car);
            carDtoList.add(dto);
        }
        return carDtoList;
    }

    public CarDto getCar(Long id) {
        Optional<Car> car = carRepos.findById(id);
        if (car.isPresent()) {
            Car c = car.get();
            return transferToDto(c);
        } else {
            throw new RecordNotFoundException("car not found");
        }
    }

    public CarDto transferToDto(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setLicenseplate(car.getLicenseplate());
        dto.setRepairActivity(car.getRepairActivities());
        return dto;
    }

    public Car transferToCar(CarInputDto dto) {
        var car = new Car();
        car.setId(dto.getId());
        car.setBrand(dto.brand);
        car.setLicenseplate(dto.licenseplate);

        if(customerRepos.existsById(dto.customer.getId())) {
            car.setCustomer(dto.customer);
            return car;

        } else {
            throw new RecordNotFoundException("No customer found");
        }
    }
}
