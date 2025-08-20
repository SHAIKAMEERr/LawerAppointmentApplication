package com.example.LawyerAppointmentApplication.service;

import com.example.LawyerAppointmentApplication.dto.ClientDto;

public interface ClientService {

	ClientDto createClient(ClientDto dto);
	
    ClientDto getClient(Long id);
}
