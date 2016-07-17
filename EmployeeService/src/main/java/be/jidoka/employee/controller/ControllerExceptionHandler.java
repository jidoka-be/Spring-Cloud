package be.jidoka.employee.controller;

import be.jidoka.employee.application.api.service.project.ProjectClient.ProjectClientErrorDecoder.ProjectAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ProjectAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    public void entityAlreadyExists() {
    }

}