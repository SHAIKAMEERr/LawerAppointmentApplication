package com.example.LawyerAppointmentApplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LawyerAppointmentApplication.dto.AppointmentResponse;
import com.example.LawyerAppointmentApplication.dto.AppointmentScheduleRequest;
import com.example.LawyerAppointmentApplication.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/schedule")
    public ResponseEntity<AppointmentResponse> schedule(
    		@RequestBody AppointmentScheduleRequest req) {
    	
        return ResponseEntity.ok(appointmentService.schedule(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getById(id));
    }

    @PutMapping("/reschedule/{id}")
    public ResponseEntity<AppointmentResponse> reschedule(
    		@PathVariable Long id, @RequestBody AppointmentScheduleRequest req) {
    	
        return ResponseEntity.ok(appointmentService.reschedule(id, req));
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        appointmentService.cancel(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lawyer/{lawyerId}")
    public ResponseEntity<List<AppointmentResponse>> listByLawyer(
    		@PathVariable Long lawyerId) {
    	
        return ResponseEntity.ok(appointmentService.listByLawyer(lawyerId));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<AppointmentResponse>> listByClient(
    		@PathVariable Long clientId) {
    	
        return ResponseEntity.ok(appointmentService.listByClient(clientId));
    }
}
