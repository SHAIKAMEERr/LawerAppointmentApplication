package com.example.LawyerAppointmentApplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LawyerAppointmentApplication.dto.AvailabilityDto;
import com.example.LawyerAppointmentApplication.dto.LawyerDto;
import com.example.LawyerAppointmentApplication.service.LawyerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lawyers")
@RequiredArgsConstructor
public class LawyerController {

    private final LawyerService lawyerService;

    @PostMapping
    public ResponseEntity<LawyerDto> create(@RequestBody LawyerDto dto) {
    	
        return ResponseEntity.ok(lawyerService.createLawyer(dto));
    }

    @GetMapping
    public ResponseEntity<List<LawyerDto>> list() {
        return ResponseEntity.ok(lawyerService.getAllLawyers());
    }

    @GetMapping("/{id}/availabilities")
    public ResponseEntity<List<AvailabilityDto>> getAvailabilities(
    		@PathVariable Long id) {
    	
        return ResponseEntity.ok(lawyerService.getAvailabilities(id));
    }

    @PostMapping("/{id}/availabilities")
    public ResponseEntity<AvailabilityDto> addAvailability(
    		@PathVariable Long id, @RequestBody AvailabilityDto dto) {
    	
        return ResponseEntity.ok(lawyerService.addAvailability(id, dto));
    }
}