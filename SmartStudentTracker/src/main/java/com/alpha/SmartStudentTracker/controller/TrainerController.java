package com.alpha.SmartStudentTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.dto.UpdateAssesmentDTO;
import com.alpha.SmartStudentTracker.entity.Assesment;
import com.alpha.SmartStudentTracker.entity.AssesmentSubmission;
import com.alpha.SmartStudentTracker.entity.Attendence;
import com.alpha.SmartStudentTracker.entity.Review;
import com.alpha.SmartStudentTracker.entity.Task;
import com.alpha.SmartStudentTracker.entity.TaskSubmission;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.repository.ReviewRepository;
import com.alpha.SmartStudentTracker.service.AssesmentResultService;
import com.alpha.SmartStudentTracker.service.AssesmentService;
import com.alpha.SmartStudentTracker.service.AttendenceService;
import com.alpha.SmartStudentTracker.service.ReviewService;
import com.alpha.SmartStudentTracker.service.TaskService;
import com.alpha.SmartStudentTracker.service.TasksSubmissionService;
import com.alpha.SmartStudentTracker.service.UserService;

import jakarta.validation.Valid;

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
	@Autowired
    private UserService userService;
	@Autowired
	private ReviewService reviewService;
    
    @GetMapping("/login")
	public ResponseEntity<ResponseStructure<Users>> login(@Valid @RequestParam String email,@Valid @RequestParam String password,@RequestParam String role) {
		return userService.getUser(email, password, role);
	}
    
    
	@PutMapping("/markAttendence")
	public ResponseEntity<ResponseStructure<Attendence>> markAttendence(@Valid @RequestBody Attendence attendence) {
		return attendenceService.markAttendence(attendence);
	}
    
	@PostMapping("/createAssesment")
	public ResponseEntity<ResponseStructure<Assesment>> createAssesment(@Valid @RequestBody Assesment assesment) {
		return assesmentService.saveAssesment(assesment);	
	}
	
	@PostMapping("/assignAssesmentToBatch")
	public ResponseEntity<ResponseStructure<Assesment>> assignAssesmentToBatch(@Valid @RequestParam Integer assesmentid,@RequestParam Integer batchid) {
		return assesmentService.assignAssesmentToBatch(assesmentid, batchid);
	}
	
//	@PostMapping("/saveResult")
//	public ResponseEntity<ResponseStructure<AssesmentSubmission>> saveResult(@RequestBody AssesmentSubmission assesmentSubmission) {
//		return assesmentResultService.saveResult(assesmentSubmission);
//	} 
	@PostMapping("/createTask")
	public ResponseEntity<ResponseStructure<Task>> createTask(@Valid @RequestBody Task task) {
		 return taskService.saveTask(task);
	}
	@PostMapping("/assignTaskToBatch")
	public ResponseEntity<ResponseStructure<Task>> assignTaskToBatch(@RequestParam Integer taskid,@RequestParam Integer batchid) {
		
		return taskService.assignTaskToBatch(taskid, batchid);
	}
    @PostMapping("/review")
	public ResponseEntity<ResponseStructure<Review>> reviewTask(@Valid @RequestBody Review review) {
		return reviewService.reviewTaskAssesment(review);
	}
	
    @PostMapping("/updateAssesment")
    public ResponseEntity<ResponseStructure<Assesment>> updateAssesment(@Valid @RequestBody UpdateAssesmentDTO upAssesmentDTO) {
    	return assesmentService.updateAssesment(upAssesmentDTO);
    }
	 
    
}
