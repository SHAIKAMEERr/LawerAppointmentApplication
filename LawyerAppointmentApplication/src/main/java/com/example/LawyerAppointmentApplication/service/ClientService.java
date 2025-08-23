package com.example.LawyerAppointmentApplication.service;

import java.util.List;

import com.example.LawyerAppointmentApplication.dto.ClientDto;

public interface ClientService {

	ClientDto createClient(ClientDto dto);
	
    ClientDto getClient(Long id);
    
    List<ClientDto> getAllClients();
}
