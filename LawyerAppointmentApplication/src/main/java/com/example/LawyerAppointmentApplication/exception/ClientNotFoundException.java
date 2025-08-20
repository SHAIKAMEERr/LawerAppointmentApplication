package com.example.LawyerAppointmentApplication.exception;

public class ClientNotFoundException extends RuntimeException{

	public ClientNotFoundException(String message) {
		super(message);
	}
}
