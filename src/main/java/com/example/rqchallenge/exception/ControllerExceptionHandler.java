package com.example.rqchallenge.exception;

import com.example.rqchallenge.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleNotFound(EmployeeNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return errorResponse;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleInternalError(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return errorResponse;
    }

}
