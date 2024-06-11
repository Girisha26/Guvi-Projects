package com.backend.Employee_Management_Application.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class APIException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
