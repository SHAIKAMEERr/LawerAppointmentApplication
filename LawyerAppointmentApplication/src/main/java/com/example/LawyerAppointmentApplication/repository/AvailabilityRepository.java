package com.example.LawyerAppointmentApplication.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LawyerAppointmentApplication.entity.AvailabilityEntity;
import com.example.LawyerAppointmentApplication.entity.LawyerEntity;

public interface AvailabilityRepository extends JpaRepository<AvailabilityEntity, Long> {

	List<AvailabilityEntity> findByLawyerAndAvailableDate(LawyerEntity lawyer,
			LocalDate date);
	
    List<AvailabilityEntity> findByLawyer_LawyerId(Long lawyerId);
}
