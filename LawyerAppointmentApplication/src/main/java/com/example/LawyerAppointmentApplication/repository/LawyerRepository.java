package com.example.LawyerAppointmentApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LawyerAppointmentApplication.entity.LawyerEntity;

public interface LawyerRepository extends JpaRepository<LawyerEntity, Long>{

}
