package com.alpha.SmartStudentTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.service.UserService;

@RestController
public class AdminController {
	
	@Autowired
	private UserService userservice;
	
	// this is method is to save the Amin  meaning to register the admin first
    @PostMapping("/RegisterAdmin")
	public ResponseEntity<ResponseStructure<Users>> saveAdmin(@RequestBody Users user) {
		return userservice.saveUsers(user);
	}
	

}
