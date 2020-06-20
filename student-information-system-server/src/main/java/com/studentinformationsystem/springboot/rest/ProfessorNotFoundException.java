package com.studentinformationsystem.springboot.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProfessorNotFoundException extends RuntimeException {

    public ProfessorNotFoundException() {
        super();
    }

    public ProfessorNotFoundException(int theId) {
        super("Pofessor id not found -" + theId);
    }

    public ProfessorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
