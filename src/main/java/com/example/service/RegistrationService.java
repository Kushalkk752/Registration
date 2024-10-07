package com.example.service;

import com.example.entity.Registration;
import com.example.payload.RegistrationDto;
import com.example.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.*;
import com.example.exception.ResoucreNotFoundException;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }


    public RegistrationDto create(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration savedReg = registrationRepository.save(registration);
        RegistrationDto regDto = mapToDto(savedReg);
        return regDto;
    }

    private RegistrationDto mapToDto(Registration savedReg) {
        RegistrationDto dto = modelMapper.map(savedReg, RegistrationDto.class);
        return dto;
    }

    private Registration mapToEntity(RegistrationDto registrationDto) {
        Registration reg = modelMapper.map(registrationDto,Registration.class);
        return reg;
    }

    public java.util.List<RegistrationDto> findAllRegistrations() {
        java.util.List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> regDtos = registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return regDtos;
    }

    public void deleteRegById(Long id) {
        registrationRepository.deleteById(id);
    }

    public Registration update(Long id, Registration registration) {
        Registration reg = registrationRepository.findById(id).get();
        reg.setFirstName(registration.getFirstName());
        reg.setEmail(registration.getEmail());
        reg.setMobile(registration.getMobile());
        return reg;
    }

    public RegistrationDto getRegById(Long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()-> new ResoucreNotFoundException("Resource not found")
        );
        return mapToDto(registration);
    }
}
