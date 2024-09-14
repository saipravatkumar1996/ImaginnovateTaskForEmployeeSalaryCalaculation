package com.imaginnovate.emp.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.emp.bean.AuthRequest;
import com.imaginnovate.emp.bean.AuthResponse;
import com.imaginnovate.emp.model.User;
import com.imaginnovate.emp.service.IUserService;

import jakarta.validation.Valid;

/**
 * 
 * @author Pravat.Kumar
 * @Date 14-09-2024 03:06 PM 
 * @Description registration user and Authentication  
 *
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {
	private final IUserService userService;
	private final Logger logger;

	public UserController(IUserService userService, Logger logger) {
		this.userService = userService;
		this.logger = logger;
	}
	
	/**
	 * 
	 * @param User register parameter
	 * @return return success or failed status
	 */

	@PostMapping(value = "/createUser")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
		logger.info("Inside UserController registerUser method for save user");
		try {
			userService.userSave(user);
			logger.info("User created successfully");
			return ResponseEntity.ok("User created successfully");
		} 
        catch (Exception e) {
			logger.error("Exception in save method for "+e.toString());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/auth")
	public ResponseEntity<?> authentication(@Valid @RequestBody AuthRequest userrequest) {
		logger.info("Inside UserController authentication method");
		try {
			AuthResponse response=userService.userAuth(userrequest);
			logger.info("Login Service successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} 
        catch (Exception e) {
			logger.error("Exception in Auth method for "+e.toString());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
