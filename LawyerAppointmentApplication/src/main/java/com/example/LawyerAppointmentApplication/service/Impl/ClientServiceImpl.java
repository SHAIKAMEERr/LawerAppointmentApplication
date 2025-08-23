package com.example.LawyerAppointmentApplication.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.LawyerAppointmentApplication.dto.ClientDto;
import com.example.LawyerAppointmentApplication.dto.LawyerDto;
import com.example.LawyerAppointmentApplication.entity.ClientEntity;
import com.example.LawyerAppointmentApplication.exception.ClientNotFoundException;
import com.example.LawyerAppointmentApplication.repository.ClientRepository;
import com.example.LawyerAppointmentApplication.service.ClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper mapper;
    
	@Override
	public ClientDto createClient(ClientDto dto) {
		var e = mapper.map(dto, ClientEntity.class);
		e.setClientId(null);
       return mapper.map(clientRepository.save(e), ClientDto.class);
	}
	
	@Override
    public ClientDto getClient(Long id) {
        var e = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(""
                		+ "Client not found: " + id));
        return mapper.map(e, ClientDto.class);
    }

	@Override
    public List<ClientDto> getAllClients() {
        List<ClientEntity> clients = clientRepository.findAll();
        return clients.stream()
                .map(client -> mapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }
}