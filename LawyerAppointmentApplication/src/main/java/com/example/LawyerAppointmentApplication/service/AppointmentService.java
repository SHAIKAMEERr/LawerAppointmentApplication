package com.example.LawyerAppointmentApplication.service;

import java.util.List;

import com.example.LawyerAppointmentApplication.dto.AppointmentResponse;
import com.example.LawyerAppointmentApplication.dto.AppointmentScheduleRequest;

public interface AppointmentService {

	AppointmentResponse schedule(AppointmentScheduleRequest req);
    
	AppointmentResponse getById(Long id);
    
	void cancel(Long id);
    
	AppointmentResponse reschedule(Long id, AppointmentScheduleRequest req);
    
	List<AppointmentResponse> listByLawyer(Long lawyerId);
    
	List<AppointmentResponse> listByClient(Long clientId);
}
