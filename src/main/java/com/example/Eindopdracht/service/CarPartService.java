package com.example.Eindopdracht.service;
import com.example.Eindopdracht.dto.CarPartDto;
import com.example.Eindopdracht.dto.CarPartInputDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.model.CarPart;
import com.example.Eindopdracht.repository.CarPartRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class CarPartService {

    private final CarPartRepository carPartRepos;

    public CarPartService(CarPartRepository repos) {
        this.carPartRepos = repos;
    }

    public CarPartDto createCarPart(CarPartInputDto dto) {
        CarPart carPart = transferToCarPart(dto);
        carPartRepos.save(carPart);
        return transferToDto(carPart);
    }

    public CarPartDto updateCarPart(Long id, CarPartInputDto dto) {
        if(carPartRepos.findById(id).isPresent()) {
            CarPart carPart = carPartRepos.findById(id).get();

            CarPart updatedCarPart = transferToCarPart(dto);
            updatedCarPart.setId(carPart.getId());

            carPartRepos.save(updatedCarPart);

            return transferToDto(updatedCarPart);
        } else {
            throw new RecordNotFoundException("no carpart found");
        }
    }

    public void deleteCarPart(Long id) {
        if(carPartRepos.findById(id).isPresent()) {
            carPartRepos.deleteById(id);

        } else {
            throw new RecordNotFoundException("no carpart found");
        }
    }

    public Collection<CarPartDto> getAllCarParts() {

        Collection<CarPartDto> carPartDtoList = new HashSet<>();
        Collection<CarPart> carPartList = carPartRepos.findAll();
        for (CarPart carPart : carPartList) {
            CarPartDto dto = transferToDto(carPart);
            carPartDtoList.add(dto);
        }
        return carPartDtoList;
    }

    public CarPartDto getCarPart(Long id) {
        Optional<CarPart> carPart = carPartRepos.findById(id);
        if (carPart.isPresent()) {
            CarPart c = carPart.get();
            return transferToDto(c);
        } else {
            throw new RecordNotFoundException("carpart not found");
        }
    }

    public CarPartDto transferToDto(CarPart carPart) {
        CarPartDto dto = new CarPartDto();
        dto.setName(carPart.getName());
        dto.setPrice(carPart.getPrice());
        dto.setSerial_number(carPart.getSerial_number());
        return dto;
    }

    public CarPart transferToCarPart(CarPartInputDto dto) {
        var carPart = new CarPart();
        carPart.setName(dto.getName());
        carPart.setPrice(dto.getPrice());
        carPart.setSerial_number(dto.getSerial_number());

        return carPart;
    }
}
