package com.example.LawyerAppointmentApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {

    private Long clientId;
    
    private String clientName;
    
    private String emailId;
    
    private String mobileNumber;
    
    private String address;
}
