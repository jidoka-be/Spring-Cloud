package be.jidoka.customer.controller;

import be.jidoka.customer.application.api.exception.EntityAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    public void entityAlreadyExists() {
    }

}
