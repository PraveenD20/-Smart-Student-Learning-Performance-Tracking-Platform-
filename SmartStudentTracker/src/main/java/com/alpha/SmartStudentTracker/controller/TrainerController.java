package com.alpha.SmartStudentTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Assesment;
import com.alpha.SmartStudentTracker.entity.AssesmentResult;
import com.alpha.SmartStudentTracker.entity.Attendence;
import com.alpha.SmartStudentTracker.entity.Task;
import com.alpha.SmartStudentTracker.service.AssesmentResultService;
import com.alpha.SmartStudentTracker.service.AssesmentService;
import com.alpha.SmartStudentTracker.service.AttendenceService;
import com.alpha.SmartStudentTracker.service.TaskService;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
	
	@Autowired
	private AttendenceService attendenceService;
	@Autowired
	private AssesmentService assesmentService;
	@Autowired
	private AssesmentResultService assesmentResultService;
	@Autowired
	private TaskService taskService;
	
	@PutMapping("/markAttendence")
	public ResponseEntity<ResponseStructure<Attendence>> markAttendence(@RequestBody Attendence attendence) {
		return attendenceService.markAttendence(attendence);
	}
    
	@PostMapping("/createAssesment")
	public ResponseEntity<ResponseStructure<Assesment>> createAssesment(@RequestBody Assesment assesment) {
		return assesmentService.saveAssesment(assesment);	
	}
	
	@PostMapping("/saveResult")
	public ResponseEntity<ResponseStructure<AssesmentResult>> saveResult(@RequestBody AssesmentResult assesmentResult) {
		return assesmentResultService.saveResult(assesmentResult);
	} 
	@PostMapping("/createTask")
	public ResponseEntity<ResponseStructure<Task>> createTask(@RequestBody Task task) {
		 return taskService.saveTask(task);
	}
}
