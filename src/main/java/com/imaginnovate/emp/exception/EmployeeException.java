package com.imaginnovate.emp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmployeeException extends RuntimeException {
	 public EmployeeException(String message) {
	        super(message);
	    }
}
