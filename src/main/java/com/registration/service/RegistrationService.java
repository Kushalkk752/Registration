package com.registration.service;

import com.registration.entity.Registration;
import com.registration.exception.ResourceNotFoundException;
import com.registration.payload.RegistrationDto;
import com.registration.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Registration savedRegistration = registrationRepository.save(registration);
        RegistrationDto regDto = mapToDto(savedRegistration);
        return regDto;
    }

    private RegistrationDto mapToDto(Registration registration) {
        RegistrationDto regDto = modelMapper.map(registration, RegistrationDto.class);
        return regDto;
    }

    private Registration mapToEntity(RegistrationDto registrationDto) {
        Registration registration = modelMapper.map(registrationDto, Registration.class);
        return registration;
    }

    public List<RegistrationDto> findAllRegs() {
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> regDtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return regDtos;
    }

    public void delete(Long id) {
        registrationRepository.deleteById(id);
    }

    public Registration update(Long id, Registration registration) {
        Registration reg = registrationRepository.findById(id).get();
        reg.setFirstName(registration.getFirstName());
        reg.setEmail(registration.getEmail());
        reg.setMobile(registration.getMobile());
        return reg;
    }

    public RegistrationDto getRegistrationById(Long id) {
        Registration reg = registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Resource Not Found")
        );
        RegistrationDto registrationDto = mapToDto(reg);
        return registrationDto;
    }
}
