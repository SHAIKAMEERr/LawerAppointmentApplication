package com.example.LawyerAppointmentApplication.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawyerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lawyerId;

    private String lawyerName;

    private String emailId;

    private Long mobileNumber;

    private Integer yearsOfExperience;

    private Integer solvedCases;

    private String specialization;

    @OneToMany(mappedBy = "lawyer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // ignore in JSON to prevent infinite recursion
    private List<AvailabilityEntity> availabilities;

    @OneToMany(mappedBy = "lawyer")
    @JsonIgnore // ignore in JSON to prevent infinite recursion
    private List<AppointmentEntity> appointments;
}
