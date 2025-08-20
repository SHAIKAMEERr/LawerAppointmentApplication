package com.example.LawyerAppointmentApplication.JUnitTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.LawyerAppointmentApplication.dto.AppointmentResponse;
import com.example.LawyerAppointmentApplication.dto.AppointmentScheduleRequest;
import com.example.LawyerAppointmentApplication.entity.ClientEntity;
import com.example.LawyerAppointmentApplication.entity.LawyerEntity;
import com.example.LawyerAppointmentApplication.repository.AppointmentRepository;
import com.example.LawyerAppointmentApplication.repository.ClientRepository;
import com.example.LawyerAppointmentApplication.repository.LawyerRepository;
import com.example.LawyerAppointmentApplication.service.AppointmentService;

@SpringBootTest
class AppointmentServiceIntegrationTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private LawyerRepository lawyerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private LawyerEntity lawyer;
    private ClientEntity client;

    @BeforeEach
    void setup() {
        appointmentRepository.deleteAll();

        lawyer = lawyerRepository.findAll().stream()
        		.findFirst().orElseGet(() -> {
        			
            LawyerEntity l = new LawyerEntity();
            l.setLawyerName("Test Lawyer");
            
            return lawyerRepository.save(l);
        });

        client = clientRepository.findAll().stream()
        		.findFirst().orElseGet(() -> {
        			
            ClientEntity c = new ClientEntity();
            c.setClientName("Test Client");
            
            return clientRepository.save(c);
        });
    }

    @Test
    void scheduleAndFetchAppointment() {
    	
        var req = new AppointmentScheduleRequest();
        req.setLawyerId(lawyer.getLawyerId());
        req.setClientId(client.getClientId());
        req.setAppointmentDate(LocalDate.now().plusDays(1));
        req.setStartTime(LocalTime.of(10, 0));
        req.setEndTime(LocalTime.of(11, 0));

        AppointmentResponse res = appointmentService.schedule(req);
        assertNotNull(res.getId());

        AppointmentResponse fetched = appointmentService.getById(res.getId());
        assertEquals(res.getId(), fetched.getId());
    }

    @Test
    void cancelAppointment() {
    	
        var req = new AppointmentScheduleRequest();
        req.setLawyerId(lawyer.getLawyerId());
        req.setClientId(client.getClientId());
        req.setAppointmentDate(LocalDate.now().plusDays(1));
        req.setStartTime(LocalTime.of(11, 0));
        req.setEndTime(LocalTime.of(12, 0));

        AppointmentResponse res = appointmentService.schedule(req);
        appointmentService.cancel(res.getId());

        AppointmentResponse canceled = appointmentService.getById(res.getId());
        assertEquals("CANCELED", canceled.getStatus());
    }

    @Test
    void rescheduleAppointment() {
    	
        var req = new AppointmentScheduleRequest();
        req.setLawyerId(lawyer.getLawyerId());
        req.setClientId(client.getClientId());
        req.setAppointmentDate(LocalDate.now().plusDays(1));
        req.setStartTime(LocalTime.of(12, 0));
        req.setEndTime(LocalTime.of(13, 0));

        AppointmentResponse res = appointmentService.schedule(req);

        var rescheduleReq = new AppointmentScheduleRequest();
        rescheduleReq.setLawyerId(lawyer.getLawyerId());
        rescheduleReq.setClientId(client.getClientId());
        rescheduleReq.setAppointmentDate(LocalDate.now().plusDays(1));
        rescheduleReq.setStartTime(LocalTime.of(13, 0));
        rescheduleReq.setEndTime(LocalTime.of(14, 0));

        AppointmentResponse updated = appointmentService
        		.reschedule(res.getId(), rescheduleReq);
        assertEquals("RESCHEDULED", updated.getStatus());
        assertEquals(LocalTime.of(13, 0), updated.getStartTime());
    }

    @Test
    void listAppointmentsByLawyer() {
    	
        var req1 = new AppointmentScheduleRequest();
        req1.setLawyerId(lawyer.getLawyerId());
        req1.setClientId(client.getClientId());
        req1.setAppointmentDate(LocalDate.now().plusDays(1));
        req1.setStartTime(LocalTime.of(14, 0));
        req1.setEndTime(LocalTime.of(15, 0));

        var req2 = new AppointmentScheduleRequest();
        req2.setLawyerId(lawyer.getLawyerId());
        req2.setClientId(client.getClientId());
        req2.setAppointmentDate(LocalDate.now().plusDays(1));
        req2.setStartTime(LocalTime.of(15, 0));
        req2.setEndTime(LocalTime.of(16, 0));

        appointmentService.schedule(req1);
        appointmentService.schedule(req2);

        List<AppointmentResponse> list = appointmentService
        		.listByLawyer(lawyer.getLawyerId());
        assertEquals(2, list.size());
    }

    @Test
    void listAppointmentsByClient() {
    	
        var req1 = new AppointmentScheduleRequest();
        req1.setLawyerId(lawyer.getLawyerId());
        req1.setClientId(client.getClientId());
        req1.setAppointmentDate(LocalDate.now().plusDays(1));
        req1.setStartTime(LocalTime.of(16, 0));
        req1.setEndTime(LocalTime.of(17, 0));

        var req2 = new AppointmentScheduleRequest();
        req2.setLawyerId(lawyer.getLawyerId());
        req2.setClientId(client.getClientId());
        req2.setAppointmentDate(LocalDate.now().plusDays(1));
        req2.setStartTime(LocalTime.of(17, 0));
        req2.setEndTime(LocalTime.of(18, 0));

        appointmentService.schedule(req1);
        appointmentService.schedule(req2);

        List<AppointmentResponse> list = appointmentService
        		.listByClient(client.getClientId());
        assertEquals(2, list.size());
    }
}
