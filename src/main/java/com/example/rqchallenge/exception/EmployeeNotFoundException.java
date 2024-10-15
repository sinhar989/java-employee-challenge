package com.example.rqchallenge.exception;

import java.io.IOException;


public class EmployeeNotFoundException
        extends RuntimeException
{
    static final long serialVersionUID = -5369283889045833024L;

    private final String message;


    public EmployeeNotFoundException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


