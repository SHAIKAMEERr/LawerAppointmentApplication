package com.example.LawyerAppointmentApplication.security;

import com.example.LawyerAppointmentApplication.constants.Role;

import lombok.Data;

@Data
public class RegisterRequest {
	
	private String userName;
	
	private String password;
	
	private Role role;
}
