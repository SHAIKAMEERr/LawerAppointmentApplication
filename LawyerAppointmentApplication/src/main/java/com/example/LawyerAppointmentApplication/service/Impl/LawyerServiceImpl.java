package com.example.LawyerAppointmentApplication.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.LawyerAppointmentApplication.dto.AvailabilityDto;
import com.example.LawyerAppointmentApplication.dto.LawyerDto;
import com.example.LawyerAppointmentApplication.entity.AvailabilityEntity;
import com.example.LawyerAppointmentApplication.entity.LawyerEntity;
import com.example.LawyerAppointmentApplication.exception.LawyerNotFoundException;
import com.example.LawyerAppointmentApplication.repository.AvailabilityRepository;
import com.example.LawyerAppointmentApplication.repository.LawyerRepository;
import com.example.LawyerAppointmentApplication.service.LawyerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LawyerServiceImpl implements LawyerService {

    private final LawyerRepository lawyerRepository;
    private final AvailabilityRepository availabilityRepository;
    private final ModelMapper mapper;
    
    @Override
    public LawyerDto createLawyer(LawyerDto dto) {
        var entity = mapper.map(dto, LawyerEntity.class);
        
        var saved = lawyerRepository.save(entity);
        return mapper.map(saved, LawyerDto.class);
    }

    @Override
    public List<LawyerDto> getAllLawyers() {
        return lawyerRepository.findAll().stream()
                .map(e -> mapper.map(e, LawyerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AvailabilityDto> getAvailabilities(Long lawyerId) {
        var lawyer = lawyerRepository.findById(lawyerId)
                .orElseThrow(() -> new LawyerNotFoundException(
                        "Lawyer not found: " + lawyerId));

        return availabilityRepository.findByLawyer_LawyerId(lawyerId).stream()
                .map(a -> {
                    var dto = new AvailabilityDto();
                    dto.setId(a.getId());
                    dto.setName(lawyer.getLawyerName()); 
                    dto.setAvailableDate(a.getAvailableDate());
                    dto.setStartTime(a.getStartTime());
                    dto.setEndTime(a.getEndTime());
                    dto.setLawyerId(lawyerId);
                    return dto;
                }).collect(Collectors.toList());
    }


    @Override
    public AvailabilityDto addAvailability(Long lawyerId, AvailabilityDto dto) {
        var lawyer = lawyerRepository.findById(lawyerId)
                .orElseThrow(() -> new LawyerNotFoundException(
                        "Lawyer not found: " + lawyerId));

        var entity = new AvailabilityEntity();
        entity.setId(null);
        entity.setAvailableDate(dto.getAvailableDate());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setLawyer(lawyer);

        var saved = availabilityRepository.save(entity);

        var out = new AvailabilityDto();
        out.setId(saved.getId());
        out.setAvailableDate(saved.getAvailableDate());
        out.setStartTime(saved.getStartTime());
        out.setEndTime(saved.getEndTime());
        out.setLawyerId(lawyerId);
        out.setName(lawyer.getLawyerName()); 
        return out;
    }

}
