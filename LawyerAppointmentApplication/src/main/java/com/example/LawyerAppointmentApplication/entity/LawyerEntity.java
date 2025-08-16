package com.example.LawyerAppointmentApplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LawyerEntity {
	
	private Long lawyerId;
	
	private String lawyerName;
	
	private String emailId;
	
	private Long MobileNumber;
	
	private Integer yearsOfExperience;
	
	private Integer solvedCases;
	
	private String specialization;
	
}
