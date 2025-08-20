package com.example.LawyerAppointmentApplication.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ LawyerNotFoundException.class, 
		ClientNotFoundException.class, AppointmentNotFoundException.class })
	
    public ResponseEntity<ErrorDetails> handleNotFound(RuntimeException ex
    		, WebRequest req) {
		
        var error = new ErrorDetails(LocalDateTime.now(), ex.getMessage()
        		, req.getDescription(false));
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ InvalidAvailabilityException.class })
    
    public ResponseEntity<ErrorDetails> handleBadRequest(RuntimeException ex
    		, WebRequest req) {
    	
        var error = new ErrorDetails(LocalDateTime.now(), ex.getMessage()
        		, req.getDescription(false));
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    
    public ResponseEntity<ErrorDetails> handleValidation(
    		MethodArgumentNotValidException ex, WebRequest req) {
    	
        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        var error = new ErrorDetails(LocalDateTime.now(), msg
        		, req.getDescription(false));
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGeneral(Exception ex, WebRequest req) {
        var error = new ErrorDetails(LocalDateTime.now(), ex.getMessage()
        		, req.getDescription(false));
        
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
