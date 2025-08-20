package com.example.LawyerAppointmentApplication.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AvailabilityDto {

	private Long id;
	
	private String name;
    
	private LocalDate availableDate;
    
	private LocalTime startTime;
    
	private LocalTime endTime;
    
	private Long lawyerId;
}
