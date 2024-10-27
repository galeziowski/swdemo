package com.example.demo;

import com.example.demo.exceptions.AuthorizationError;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({NotFoundException.class, FileNotFoundException.class})
    public ResponseEntity<Object> handleInternalServerError(Exception ex, WebRequest request) {

        String errorResponse = "No dupa blada "+ ex.getMessage();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({AuthorizationError.class})
    public ResponseEntity<Object> handleAuthError(AuthorizationError ex, WebRequest request) {

        String errorResponse = "Uzytkownik "+ ex.getUserName() + " zrobil cos zlego " + ex.getMessage();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Optional: You can add more handlers here for specific exceptions or error codes

    // Custom ErrorResponse class to structure the error response
    public static class ErrorResponse {
        private int status;
        private String error;
        private String message;

        public ErrorResponse(int status, String error, String message) {
            this.status = status;
            this.error = error;
            this.message = message;
        }

        // Getters and setters
        public int getStatus() { return status; }
        public String getError() { return error; }
        public String getMessage() { return message; }
    }
}

