package com.example.controller;

import com.example.entity.Registration;
import com.example.payload.RegistrationDto;
import com.example.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<RegistrationDto> createRegistration(
            @RequestBody RegistrationDto registrationDto){
        RegistrationDto regDto = registrationService.create(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<java.util.List<RegistrationDto>> getAllRegistrations(){
        List<RegistrationDto> regDtos = registrationService.findAllRegistrations();
        return new ResponseEntity<>(regDtos,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(
            @RequestParam Long id){
        registrationService.deleteRegById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(
            @PathVariable Long id,
            @RequestBody Registration registration
    ){
        Registration reg = registrationService.update(id,registration);
        return new ResponseEntity<>(reg,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(@PathVariable Long id){
        RegistrationDto registrationDto = registrationService.getRegById(id);
        return new ResponseEntity<>(registrationDto,HttpStatus.OK);
    }
}
