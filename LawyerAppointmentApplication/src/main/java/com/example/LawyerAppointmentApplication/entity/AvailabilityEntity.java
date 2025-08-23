package com.example.LawyerAppointmentApplication.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailabilityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String lawyerName;
	
	private LocalDate availableDate;
	
	private LocalTime startTime;
	
	private LocalTime endTime;
	
	@ManyToOne
    @JoinColumn(name = "lawyer_id", nullable = false)
    private LawyerEntity lawyer;
}
