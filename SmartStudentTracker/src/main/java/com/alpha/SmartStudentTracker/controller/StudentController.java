package com.alpha.SmartStudentTracker.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.repository.AttendenceRepository;
import com.alpha.SmartStudentTracker.service.AttendenceService;

@RestController
@RequestMapping("/student")
public class StudentController {
  
	@Autowired
	private AttendenceService attendenceService;
	
	@GetMapping("/getAttendence")
	public ResponseEntity<ResponseStructure<String>> studentAttendence(@RequestParam Integer studentId,@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return attendenceService.getAttendence(studentId,date);
		
	}
}
