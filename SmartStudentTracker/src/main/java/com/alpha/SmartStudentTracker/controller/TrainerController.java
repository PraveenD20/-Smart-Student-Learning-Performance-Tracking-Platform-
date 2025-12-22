package com.alpha.SmartStudentTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Assesment;
import com.alpha.SmartStudentTracker.entity.AssesmentResult;
import com.alpha.SmartStudentTracker.entity.Attendence;
import com.alpha.SmartStudentTracker.entity.Task;
import com.alpha.SmartStudentTracker.entity.TaskSubmission;
import com.alpha.SmartStudentTracker.service.AssesmentResultService;
import com.alpha.SmartStudentTracker.service.AssesmentService;
import com.alpha.SmartStudentTracker.service.AttendenceService;
import com.alpha.SmartStudentTracker.service.TaskService;
import com.alpha.SmartStudentTracker.service.TasksSubmissionService;

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
    @Autowired
    private TasksSubmissionService tasksSubmissionService;
	
	@PutMapping("/markAttendence")
	public ResponseEntity<ResponseStructure<Attendence>> markAttendence(@RequestBody Attendence attendence) {
		return attendenceService.markAttendence(attendence);
	}
    
	@PostMapping("/createAssesment")
	public ResponseEntity<ResponseStructure<Assesment>> createAssesment(@RequestBody Assesment assesment) {
		return assesmentService.saveAssesment(assesment);	
	}
	
	@PostMapping("/assignAssesmentToBatch")
	public ResponseEntity<ResponseStructure<Assesment>> assignAssesmentToBatch(@RequestParam Integer assesmentid,@RequestParam Integer batchid) {
		return assesmentService.assignAssesmentToBatch(assesmentid, batchid);
	}
	
	@PostMapping("/saveResult")
	public ResponseEntity<ResponseStructure<AssesmentResult>> saveResult(@RequestBody AssesmentResult assesmentResult) {
		return assesmentResultService.saveResult(assesmentResult);
	} 
	@PostMapping("/createTask")
	public ResponseEntity<ResponseStructure<Task>> createTask(@RequestBody Task task) {
		 return taskService.saveTask(task);
	}
	@PostMapping("/assignTaskToBatch")
	public ResponseEntity<ResponseStructure<Task>> assignTaskToBatch(@RequestParam Integer taskid,@RequestParam Integer batchid) {
		
		return taskService.assignTaskToBatch(taskid, batchid);
	}
   
	@PostMapping("/submitTask")
	public ResponseEntity<ResponseStructure<TaskSubmission>> submitTask(@RequestBody TaskSubmission taskSubmission) {
	return tasksSubmissionService.submitTask(taskSubmission);	
	
	}

}
