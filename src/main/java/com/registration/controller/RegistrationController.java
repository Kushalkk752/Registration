package com.registration.controller;


import com.registration.entity.Registration;
import com.registration.payload.RegistrationDto;
import com.registration.repository.RegistrationRepository;
import com.registration.service.RegistrationService;
import java.util.*;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;
    private RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationService registrationService, RegistrationRepository registrationRepository) {
        this.registrationService = registrationService;
        this.registrationRepository = registrationRepository;
    }


    @PostMapping
    public ResponseEntity<?> createRegistration(
            @Valid @RequestBody RegistrationDto registrationDto,
            BindingResult result
    ){
        Optional<Registration> opEmail = registrationRepository.findByEmail(registrationDto.getEmail());
        if(opEmail.isPresent()){
            return new ResponseEntity<>("Email is already present",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<Registration> opMobile = registrationRepository.findByMobile(registrationDto.getMobile());
        if(opMobile.isPresent()){
            return new ResponseEntity<>("Mobile number is already present",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED);
        }
        RegistrationDto regDto = registrationService.create(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(){
        List<RegistrationDto> regDtos =  registrationService.findAllRegs();
        return new ResponseEntity<>(regDtos,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(
            @RequestParam Long id
    ){
        registrationService.delete(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> update(
            @PathVariable Long id,
            @RequestBody Registration registration
    ){
        Registration reg = registrationService.update(id,registration);
        return new ResponseEntity<>(reg,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegById(
            @PathVariable Long id
    ){
        RegistrationDto registrationDto = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(registrationDto,HttpStatus.OK);
    }
}
