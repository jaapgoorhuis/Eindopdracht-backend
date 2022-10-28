package com.example.Eindopdracht.controller;

import com.example.Eindopdracht.dto.ReceiptDto;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptController {

    private final ReceiptService service;

    public ReceiptController(ReceiptService service) {
        this.service = service;
    }

    @GetMapping("receipt/car/{id}")
    public ResponseEntity<Object> getReceipt(@PathVariable Long id) {
        try {
            ReceiptDto receipt = service.getReceipt(id);
            return ResponseEntity.ok().body(receipt);
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car found");
        }
    }
}
