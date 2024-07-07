package com.capstone_project.Online_Bus_Ticket_Booking_Application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException
{
    public CustomException(String message)
    {
        super(message);
    }
}
