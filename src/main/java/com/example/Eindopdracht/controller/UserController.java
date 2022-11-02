package com.example.Eindopdracht.controller;

import com.example.Eindopdracht.dto.UserDto;
import com.example.Eindopdracht.dto.UserInputDto;
import com.example.Eindopdracht.exceptions.CannotUpdateException;
import com.example.Eindopdracht.exceptions.DuplicatedEntryException;
import com.example.Eindopdracht.exceptions.RecordNotFoundException;
import com.example.Eindopdracht.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserInputDto userDto, Principal principal) {
        try {
            UserDto userData = service.createUser(userDto, principal);
            return new ResponseEntity<>(userData, HttpStatus.CREATED);
        }catch (DuplicatedEntryException re) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Username already exists");
        }
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> dtos;
        dtos = service.getAllUsers();
        if (!dtos.isEmpty()) {
            return ResponseEntity.ok().body(dtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable Long id) {
        try {
            UserDto user = service.getUser(id);
            return ResponseEntity.ok().body(user);
        } catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
        }
    }

    @PutMapping("users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @Valid @RequestBody UserInputDto userDto, Principal principal) {
        try {
            UserDto dto = service.updateUser(id, userDto,principal);
            return ResponseEntity.ok().body(dto);
        } catch (RecordNotFoundException | DuplicatedEntryException | CannotUpdateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try {
            service.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        }catch (RecordNotFoundException re) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(re.getMessage());
        }
    }
}