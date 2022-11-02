package com.example.Eindopdracht.controller;

import com.example.Eindopdracht.dto.CustomerDto;
import com.example.Eindopdracht.dto.CustomerInputDto;
import com.example.Eindopdracht.exceptions.DuplicatedEntryException;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("customers")
    public ResponseEntity<Object> createCustomer(@Valid @RequestBody CustomerInputDto dto) {
        try {
            CustomerDto customerData = service.createCustomer(dto);
            return new ResponseEntity<>(customerData, HttpStatus.OK);
        } catch (RecordNotFoundException | DuplicatedEntryException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
    }

    @PutMapping("customers/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerInputDto customerDto) {
        try {
            CustomerDto dto = service.updateCustomer(id, customerDto);
            return ResponseEntity.ok().body(dto);
        } catch (RecordNotFoundException | DuplicatedEntryException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
        }
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        try {
            service.deleteCustomer(id);
            return ResponseEntity.status(HttpStatus.OK).body("Customer deleted");
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No customer found");
        }
    }

    @GetMapping("customers")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<CustomerDto> dtos;
        dtos = service.getAllCustomers();
        if (!dtos.isEmpty()) {
            return ResponseEntity.ok().body(dtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Object> getOneCustomer(@PathVariable Long id) {
        try {
            CustomerDto customer = service.getOneCustomer(id);
            return ResponseEntity.ok().body(customer);
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found");
        }
    }
}
