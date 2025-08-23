package com.example.LawyerAppointmentApplication.service.Impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.LawyerAppointmentApplication.constants.AppointmentStatus;
import com.example.LawyerAppointmentApplication.dto.AppointmentResponse;
import com.example.LawyerAppointmentApplication.dto.AppointmentScheduleRequest;
import com.example.LawyerAppointmentApplication.entity.AppointmentEntity;
import com.example.LawyerAppointmentApplication.entity.LawyerEntity;
import com.example.LawyerAppointmentApplication.exception.AppointmentNotFoundException;
import com.example.LawyerAppointmentApplication.exception.ClientNotFoundException;
import com.example.LawyerAppointmentApplication.exception.InvalidAvailabilityException;
import com.example.LawyerAppointmentApplication.exception.LawyerNotFoundException;
import com.example.LawyerAppointmentApplication.repository.AppointmentRepository;
import com.example.LawyerAppointmentApplication.repository.AvailabilityRepository;
import com.example.LawyerAppointmentApplication.repository.ClientRepository;
import com.example.LawyerAppointmentApplication.repository.LawyerRepository;
import com.example.LawyerAppointmentApplication.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AvailabilityRepository availabilityRepository;
    private final LawyerRepository lawyerRepository;
    private final ClientRepository clientRepository;

    @Override
    public AppointmentResponse schedule(AppointmentScheduleRequest req) {
    	
        var lawyer = lawyerRepository.findById(req.getLawyerId())
                .orElseThrow(() -> new LawyerNotFoundException(
                		"Lawyer not found: " + req.getLawyerId()));
        
        var client = clientRepository.findById(req.getClientId())
                .orElseThrow(() -> new ClientNotFoundException(""
                		+ "Client not found: " + req.getClientId()));

        validateWithinAvailability(lawyer, req.getAppointmentDate()
        		, req.getStartTime(), req.getEndTime());
        
        validateNoOverlap(lawyer.getLawyerId(), req.getAppointmentDate()
        		, req.getStartTime(), req.getEndTime());

        var appt = AppointmentEntity.builder()
                .slotDate(req.getAppointmentDate())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .status(AppointmentStatus.BOOKED)
                .lawyer(lawyer)
                .client(client)
                .build();
        return toResponse(appointmentRepository.save(appt));
    }

    private void validateWithinAvailability(LawyerEntity lawyer,
    		LocalDate date, LocalTime start, LocalTime end) {
    	
        var slots = availabilityRepository.findByLawyerAndAvailableDate(lawyer, date);
        
        boolean fits = slots.stream().anyMatch(s -> !start.isBefore(s.getStartTime()) 
        		&& !end.isAfter(s.getEndTime()));
        
        
        if (!fits) throw new InvalidAvailabilityException(""
        		+ "Requested time is outside lawyer availability for " + date);
    }

    private void validateNoOverlap(Long lawyerId, LocalDate date,
    		LocalTime start, LocalTime end) {
    	
        boolean overlap = appointmentRepository
        		.existsByLawyer_LawyerIdAndSlotDateAndStartTimeAndEndTime(
        				lawyerId, date, start, end);
             
        
        if (overlap) throw new InvalidAvailabilityException(""
        		+ "Requested time overlaps with existing appointment");
    }

    @Override
    public AppointmentResponse getById(Long id) {
        return appointmentRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new AppointmentNotFoundException(
                		"Appointment not found: " + id));
    }

    @Override
    public void cancel(Long id) {
        var appt = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(
                		"Appointment not found: " + id));
        appt.setStatus(AppointmentStatus.CANCELED);
        appointmentRepository.save(appt);
    }

    @Override
    public AppointmentResponse reschedule(Long id, AppointmentScheduleRequest req) {
        var appt = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(
                		"Appointment not found: " + id));

        validateWithinAvailability(appt.getLawyer(), req
        		.getAppointmentDate(), req.getStartTime(), req.getEndTime());
        
        validateNoOverlap(appt.getLawyer().getLawyerId(), 
        		req.getAppointmentDate(), req.getStartTime(), req.getEndTime());

        appt.setSlotDate(req.getAppointmentDate());
        appt.setStartTime(req.getStartTime());
        appt.setEndTime(req.getEndTime());
        appt.setStatus(AppointmentStatus.RESCHEDULED);
        return toResponse(appointmentRepository.save(appt));
    }

    @Override
    public List<AppointmentResponse> listByLawyer(Long lawyerId) {
        return appointmentRepository.findByLawyer_LawyerId(lawyerId)
        		.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> listByClient(Long clientId) {
        return appointmentRepository.findByClient_ClientId(
        		clientId).stream().map(this::toResponse).collect(Collectors.toList());
    }

    private AppointmentResponse toResponse(AppointmentEntity e) {
        var r = new AppointmentResponse();
        r.setId(e.getId());
        r.setLawyerId(e.getLawyer().getLawyerId());
        r.setClientId(e.getClient().getClientId());
        r.setAppointmentDate(e.getSlotDate());
        r.setStartTime(e.getStartTime());
        r.setEndTime(e.getEndTime());
        r.setStatus(e.getStatus().name());
        return r;
    }
}