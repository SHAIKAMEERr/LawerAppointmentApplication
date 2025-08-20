package com.example.LawyerAppointmentApplication.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LawyerAppointmentApplication.entity.AppointmentEntity;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

	List<AppointmentEntity> findByLawyer_LawyerId(Long lawyerId);
    List<AppointmentEntity> findByClient_ClientId(Long clientId);

    boolean existsByLawyer_LawyerIdAndSlotDateAndStartTimeAndEndTime(
            Long lawyerId,
            LocalDate slotDate,
            LocalTime startTime,
            LocalTime endTime
    );


}
