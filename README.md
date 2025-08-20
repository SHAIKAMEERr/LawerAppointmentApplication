# Lawyer Appointment Scheduling Application

## 📌 Project Overview

The **Lawyer Appointment Scheduling Application** is a Spring Boot project designed to manage appointments between lawyers and clients. It allows clients to schedule appointments, lawyers to set availability, and the system to prevent overlapping appointments.

This project follows **3-tier architecture** (Controller → Service → Repository) and demonstrates the use of **DTOs, Entities, ModelMapper, Exception Handling, Enums, JPA, Lombok, and H2 Database**. It also includes **JUnit test cases** to validate core functionalities.

---

## 🚀 Features

* Client can book appointments with lawyers.
* Lawyer can set their availability.
* Prevents overlapping appointments.
* Proper **exception handling** with custom exceptions.
* Uses **DTOs** for request/response handling.
* Integration with **Spring Data JPA & H2 Database**.
* Unit & Integration testing using **JUnit**.
* Secure project structure with **constants, config, and utils** packages.

---

## 📂 Project Structure

```
com.example.LawyerAppointmentApplication
│
├── config
│   └── AppConfig.java
│
├── constants
│   ├── AppointmentStatus.java
│   └── Role.java
│
├── controller
│   ├── AppointmentController.java
│   ├── ClientController.java
│   └── LawyerController.java
│
├── dto
│   ├── LawyerDTO.java
│   ├── ClientDTO.java
│   ├── AvailabilityDTO.java
│   ├── AppointmentScheduleRequest.java
│   └── AppointmentResponse.java
│
├── entity
│   └── AppointmentEntity.java
│
├── exception
│   ├── InvalidAvailabilityException.java
│   ├── AppointmentNotFoundException.java
│   └── GlobalExceptionHandler.java
│
├── repository
│   └── AppointmentRepository.java
│
├── service
│   ├── AppointmentService.java
│   ├── ClientService.java
│   ├── LawyerService.java
│   └── impl
│       ├── AppointmentServiceImpl.java
│       ├── ClientServiceImpl.java
│       └── LawyerServiceImpl.java
│
├── JUnitTesting
│   └── AppointmentServiceIntegrationTest.java
│
├── LawyerAppointmentApplication.java
└── application.properties
```

---

## ⚙️ Tech Stack

* **Java 17**
* **Spring Boot 3.5.4**
* **Spring Data JPA**
* **H2 Database (In-Memory)**
* **Lombok**
* **ModelMapper**
* **JUnit 5**
* **Maven**
* **Model Mapper**

---

## 🛠️ Installation & Setup

### 1. Clone the repository

```bash
git clone https://github.com/your-username/LawyerAppointmentApplication.git
cd LawyerAppointmentApplication
```

### 2. Build the project

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn spring-boot:run
```

### 4. Access H2 Database

* URL: `http://localhost:8080/h2-console`
* JDBC URL: `jdbc:h2:mem:testdb`
* User: `UserName`
* Password: `Password`

---

---

## 🖥️ API Endpoints

### Clients
| Method | URL             | Body | Description |
|--------|----------------|------|-------------|
| POST   | `/api/clients` | `{ "name": "John Doe", "email": "john@example.com" }` | Create a new client |
| GET    | `/api/clients/{id}` | N/A | Get client details by ID |

### Lawyers
| Method | URL                           | Body | Description |
|--------|-------------------------------|------|-------------|
| POST   | `/api/lawyers`                | `{ "name": "Jane Smith", "email": "jane@example.com" }` | Create a new lawyer |
| GET    | `/api/lawyers`                | N/A | Get all lawyers |
| GET    | `/api/lawyers/{id}/availabilities` | N/A | Get lawyer's availability |
| POST   | `/api/lawyers/{id}/availabilities` | `{ "availableDate": "2025-08-21", "startTime": "10:00", "endTime": "12:00" }` | Add availability for a lawyer |

### Appointments
| Method | URL                          | Body | Description |
|--------|------------------------------|------|-------------|
| POST   | `/api/appointments/schedule` | `{ "lawyerId": 1, "clientId": 1, "appointmentDate": "2025-08-21", "startTime": "10:30", "endTime": "11:30" }` | Schedule an appointment |
| GET    | `/api/appointments/{id}`     | N/A | Get appointment by ID |
| PUT    | `/api/appointments/reschedule/{id}` | `{ "appointmentDate": "2025-08-22", "startTime": "11:00", "endTime": "12:00" }` | Reschedule an appointment |
| DELETE | `/api/appointments/cancel/{id}` | N/A | Cancel an appointment |
| GET    | `/api/appointments/lawyer/{lawyerId}` | N/A | List all appointments of a lawyer |
| GET    | `/api/appointments/client/{clientId}` | N/A | List all appointments of a client |

---


## 🧪 Testing

The project includes **JUnit test cases** to ensure reliability.

### Run tests:

```bash
mvn test
```

### Current Test Coverage

* ✅ Appointment scheduling without overlap
* ✅ Prevent overlapping appointments (throws `InvalidAvailabilityException`)
* ✅ Fetch appointment details
* ✅ Lawyer availability management
* ✅ Client creation & retrieval

---

## 🔄 Project Flow

1. **Client** registers in the system.
2. **Lawyer** sets their availability.
3. **Client** requests an appointment.
4. **System** checks if requested time overlaps with existing appointments.

   * If overlap → `InvalidAvailabilityException` is thrown.
   * Else → Appointment is booked.
5. **Client & Lawyer** get appointment confirmation response.

---

## 📝 Future Enhancements

* ✅ Add authentication & authorization with **Spring Security (JWT/OAuth2)**
* ✅ Implement email notifications for appointments
* ✅ Dockerize the application
* ✅ Extend to support multiple appointment types

---

## 👨‍💻 Author

**Shaik Ameerjan**
Java Developer | Skilled in Java, Spring Boot, REST APIs, and Microservices
🚀 Passionate about Backend Development

## 📢 Let's Connect!
📧 [EMail](shaikameerjann@gmail.com)  
💼 [LinkedIn](https://www.linkedin.com/in/ameer-shaikk/)
<br>📘 [LeetCode](https://leetcode.com/u/SHAIK_AMEER_/)
<br>🔗 [PortFolio](https://shaikameer.netlify.app/)
