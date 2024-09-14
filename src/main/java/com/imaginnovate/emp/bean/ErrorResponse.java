package com.imaginnovate.emp.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	
	private List<String> details;
	
	private String message;
	
	private Integer statusCode;

}
