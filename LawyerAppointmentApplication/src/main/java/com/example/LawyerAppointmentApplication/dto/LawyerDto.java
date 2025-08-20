package com.example.LawyerAppointmentApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawyerDto {

    private Long lawyerId;
        
    private String emailId;
    
    private Long mobileNumber;
    
    private Integer yearsOfExperience;
    
    private Integer solvedCases;
    
    private String specialization;
}
