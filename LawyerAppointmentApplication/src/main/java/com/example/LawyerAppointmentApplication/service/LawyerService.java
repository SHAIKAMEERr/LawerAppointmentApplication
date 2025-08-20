package com.example.LawyerAppointmentApplication.service;

import java.util.List;

import com.example.LawyerAppointmentApplication.dto.AvailabilityDto;
import com.example.LawyerAppointmentApplication.dto.LawyerDto;

public interface LawyerService {

	LawyerDto createLawyer(LawyerDto dto);
	
    List<LawyerDto> getAllLawyers();
    
    List<AvailabilityDto> getAvailabilities(Long lawyerId);
    
    AvailabilityDto addAvailability(Long lawyerId, AvailabilityDto dto);
}
