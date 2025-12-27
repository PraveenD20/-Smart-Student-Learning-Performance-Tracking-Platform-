package com.alpha.SmartStudentTracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.SmartStudentTracker.dto.LeaderBoardResponse;
import com.alpha.SmartStudentTracker.dto.ResponseStructure; 
import com.alpha.SmartStudentTracker.entity.AssesmentSubmission;
import com.alpha.SmartStudentTracker.entity.TaskSubmission;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.repository.AttendenceRepository;			
import com.alpha.SmartStudentTracker.service.AssesmentResultService;
import com.alpha.SmartStudentTracker.service.AttendenceService;
import com.alpha.SmartStudentTracker.service.LeaderBoardService;
import com.alpha.SmartStudentTracker.service.ReviewService;
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
	@Autowired
	private LeaderBoardService leaderBoardService;
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<Users>> login(@RequestParam String email,@RequestParam String password,@RequestParam String role) {
		return userService.getUser(email, password, role);
	}
	
	@GetMapping("/getAttendence")
	public ResponseEntity<ResponseStructure<String>> studentAttendence(@RequestParam Integer studentId,@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return attendenceService.getAttendence(studentId,date);

	}
	 
	@PostMapping("/submitTask")
	public ResponseEntity<ResponseStructure<TaskSubmission>> submitTask(@RequestBody TaskSubmission taskSubmission) {
	return tasksSubmissionService.submitTask(taskSubmission);	
	
	}
	
    @GetMapping("/getTaskResult")
	public ResponseEntity<ResponseStructure<TaskSubmission>> getTaskResult(@RequestParam Integer studentid,@RequestParam Integer tasksubId) {
		return tasksSubmissionService.getTaskResult(studentid, tasksubId);
	}	
    
    @PostMapping("/submitAssesment")
	public ResponseEntity<ResponseStructure<AssesmentSubmission>> saveResult(@RequestBody AssesmentSubmission assesmentSubmission) {
		return assesmentResultService.saveResult(assesmentSubmission);	
	}
    
    @GetMapping("/getAsssementResult")
	public ResponseEntity<ResponseStructure<AssesmentSubmission>> getAsssementResult(@RequestParam Integer studentid,@RequestParam Integer assesmentid) {
		return assesmentResultService.getResult(studentid, assesmentid);		
	}
    
    @GetMapping("/leaderBoard")
    public List<LeaderBoardResponse> viewLeaderBoard(@RequestParam String submissiontype,@RequestParam Integer subid) {
    	return leaderBoardService.getLeaderboardForAssignment(submissiontype,subid );
    }
   @GetMapping("/assesmentReports")
    public ResponseEntity<ResponseStructure<String>> getReports(@RequestParam Integer assesmentid,@RequestParam  Integer subid) {
    	return reviewService.getReports(assesmentid, subid);
    	
    }
    
    
}
