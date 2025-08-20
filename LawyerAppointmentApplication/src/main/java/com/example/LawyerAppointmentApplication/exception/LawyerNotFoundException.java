package com.example.LawyerAppointmentApplication.exception;

public class LawyerNotFoundException extends RuntimeException{
	
	public LawyerNotFoundException(String message) { 
		super(message);
	}
}
