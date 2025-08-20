package com.example.LawyerAppointmentApplication.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AppointmentScheduleRequest {

	private Long lawyerId;
    
	private Long clientId;
    
	private LocalDate appointmentDate;
    
	private LocalTime startTime;
    
	private LocalTime endTime;
}
