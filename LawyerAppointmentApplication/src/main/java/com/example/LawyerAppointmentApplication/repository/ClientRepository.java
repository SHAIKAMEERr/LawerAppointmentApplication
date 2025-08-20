package com.example.LawyerAppointmentApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LawyerAppointmentApplication.entity.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
