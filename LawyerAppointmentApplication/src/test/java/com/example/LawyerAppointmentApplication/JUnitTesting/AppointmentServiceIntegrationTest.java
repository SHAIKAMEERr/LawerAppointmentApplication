package com.example.LawyerAppointmentApplication.JUnitTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.LawyerAppointmentApplication.dto.AppointmentResponse;
import com.example.LawyerAppointmentApplication.dto.AppointmentScheduleRequest;
import com.example.LawyerAppointmentApplication.repository.ClientRepository;
import com.example.LawyerAppointmentApplication.repository.LawyerRepository;
import com.example.LawyerAppointmentApplication.service.AppointmentService;


@SpringBootTest
class AppointmentServiceIntegrationTest {

    @Autowired 
    AppointmentService appointmentService;
    
    @Autowired 
    LawyerRepository lawyerRepository;
    
    @Autowired 
    ClientRepository clientRepository;

    @Test
    void scheduleAndFetch() {
        Long lawyerId = lawyerRepository.findAll().get(0).getLawyerId();
        Long clientId = clientRepository.findAll().get(0).getClientId();

        var req = new AppointmentScheduleRequest();
        req.setLawyerId(lawyerId);
        req.setClientId(clientId);
        req.setAppointmentDate(LocalDate.now().plusDays(1));
        req.setStartTime(LocalTime.of(10, 30));
        req.setEndTime(LocalTime.of(11, 30));

        AppointmentResponse res = appointmentService.schedule(req);
        assertNotNull(res.getId());

        AppointmentResponse fetched = appointmentService.getById(res.getId());
        assertEquals(res.getId(), fetched.getId());
    }
}