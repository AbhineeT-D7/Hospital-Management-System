package com.abhi.gov_hos_app.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {

	@Serial
    private static final long serialVersionUID = 1L;

	public PatientNotFoundException(String exception) {
		super(exception);
	}
}
