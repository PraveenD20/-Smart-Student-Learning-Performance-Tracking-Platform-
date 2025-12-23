package com.alpha.SmartStudentTracker.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.AssesmentResult;
import com.alpha.SmartStudentTracker.entity.TaskSubmission;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.repository.AttendenceRepository;
import com.alpha.SmartStudentTracker.service.AssesmentResultService;
import com.alpha.SmartStudentTracker.service.AttendenceService;
import com.alpha.SmartStudentTracker.service.TasksSubmissionService;
import com.alpha.SmartStudentTracker.service.UserService;

@RestController
@RequestMapping("/student")
public class StudentController {
  
	@Autowired
	private AttendenceService attendenceService;
	@Autowired
	private TasksSubmissionService tasksSubmissionService;
	@Autowired
	private AssesmentResultService assesmentResultService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<Users>> login(@RequestParam String email,@RequestParam String password,@RequestParam String role) {
		return userService.getUser(email, password, role);
	}
	
	@GetMapping("/getAttendence")
	public ResponseEntity<ResponseStructure<String>> studentAttendence(@RequestParam Integer studentId,@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return attendenceService.getAttendence(studentId,date);

	}
	
	@GetMapping("/getAsssementResult")
	public ResponseEntity<ResponseStructure<AssesmentResult>> getAsssementResult(@RequestParam Integer studentid,@RequestParam Integer assesmentid) {
		return assesmentResultService.getResult(studentid, assesmentid);		
	}
    @GetMapping("/getTaskResult")
	public ResponseEntity<ResponseStructure<TaskSubmission>> getTaskResult(@RequestParam Integer studentid,@RequestParam Integer tasksubId) {
		return tasksSubmissionService.getTaskResult(studentid, tasksubId);
	}	
 
}
