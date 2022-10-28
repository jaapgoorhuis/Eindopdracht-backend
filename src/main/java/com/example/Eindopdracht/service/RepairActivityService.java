package com.example.Eindopdracht.service;
import com.example.Eindopdracht.dto.RepairActivityDto;
import com.example.Eindopdracht.dto.RepairActivityInputDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.model.CarPart;
import com.example.Eindopdracht.model.RepairActivity;
import com.example.Eindopdracht.repository.CarPartRepository;
import com.example.Eindopdracht.repository.CarRepository;
import com.example.Eindopdracht.repository.RepairActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RepairActivityService {

    private final RepairActivityRepository repairActivityRepos;
    private final CarPartRepository carPartRepos;
    private final CarRepository carRepos;

    public RepairActivityService(RepairActivityRepository repairActivityRepos, CarPartRepository carPartRepos, CarRepository carRepos) {
        this.repairActivityRepos = repairActivityRepos;
        this.carPartRepos = carPartRepos;
        this.carRepos = carRepos;
    }

    public RepairActivityDto createRepairActivity(RepairActivityInputDto dto) {
        RepairActivity repairActivity = transferToRepairActivity(dto);
        repairActivityRepos.save(repairActivity);

        return transferToDto(repairActivity);
    }

    public RepairActivityDto updateRepairActivity(Long id, RepairActivityInputDto dto) {
        if (repairActivityRepos.findById(id).isPresent()) {
            RepairActivity repairActivity = repairActivityRepos.findById(id).get();
            RepairActivity updatedRepairActivity = transferToRepairActivity(dto);
            updatedRepairActivity.setId(repairActivity.getId());
            repairActivityRepos.save(updatedRepairActivity);

            return transferToDto(updatedRepairActivity);
        } else {
            throw new RecordNotFoundException("No customer found");
        }
    }

    public List<RepairActivityDto> getAllRepairActivities() {
        List<RepairActivity> repairActivityList = repairActivityRepos.findAll();

        List<RepairActivityDto> repairActivityDtoList = new ArrayList<>();
        for (RepairActivity repairActivity : repairActivityList) {
            RepairActivityDto dto = transferToDto(repairActivity);
            repairActivityDtoList.add(dto);
        }
        return repairActivityDtoList;
    }

    public RepairActivityDto getOneRepairActivity(Long id) {
        Optional<RepairActivity> repairActivity = repairActivityRepos.findById(id);

        if (repairActivity.isPresent()) {
            RepairActivity r = repairActivity.get();
            return transferToDto(r);
        } else {
            throw new RecordNotFoundException("Repair activity not found");
        }
    }

    public void deleteRepairActivity(Long id) {
        if(repairActivityRepos.findById(id).isPresent()) {
            repairActivityRepos.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("no repair activity found");
        }
    }

    public RepairActivityDto transferToDto(RepairActivity repairActivity) {
        RepairActivityDto dto = new RepairActivityDto();

        dto.setDescription(repairActivity.getDescription());
        dto.setHour(repairActivity.getHour());
        dto.setHour_price(repairActivity.getHour_price());
        dto.setStatus(repairActivity.getStatus());
        dto.setTitle(repairActivity.getTitle());
        dto.setNotes(repairActivity.getNotes());
        Collection<CarPart> repairActivityCarParts = repairActivity.getCarParts();
        dto.setCarParts(repairActivityCarParts);
        dto.setCars(repairActivity.getCars());

        return dto;
    }

    public RepairActivity transferToRepairActivity(RepairActivityInputDto dto) {
        var repairActivity = new RepairActivity();
        repairActivity.setDescription(dto.description);
        repairActivity.setTitle(dto.title);
        repairActivity.setHour(dto.hour);
        repairActivity.setNotes(dto.notes);
        repairActivity.setHour_price(dto.hour_price);
        repairActivity.setStatus(dto.status);

        List<CarPart> RepairActivityCarParts = new ArrayList<>();
        for (Long carParts : RepairActivityInputDto.carParts) {
            Optional<CarPart> or = carPartRepos.findById(carParts);
            if(or.isPresent()) {
                RepairActivityCarParts.add(or.get());
            }else {
                throw new RecordNotFoundException("no car parts found");
            }
        }

        repairActivity.setCarParts(RepairActivityCarParts);

       if(carRepos.existsById(dto.cars.getId())) {
            repairActivity.setCars(dto.cars);
        } else {
            throw new RecordNotFoundException("No car found");
        }

        return repairActivity;
    }
}
