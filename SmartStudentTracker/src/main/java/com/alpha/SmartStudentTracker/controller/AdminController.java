package com.alpha.SmartStudentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.SmartStudentTracker.dto.AssigningBatchToStudent;
import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.dto.TrainersResponse;
import com.alpha.SmartStudentTracker.entity.Batches;
import com.alpha.SmartStudentTracker.entity.Courses;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.service.BatcheService;
import com.alpha.SmartStudentTracker.service.CoursesService;
import com.alpha.SmartStudentTracker.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userservice; // User service
	//Courses service
	@Autowired
	private CoursesService coursesservice;
	@Autowired
	private BatcheService batchservice;

	// this is method is to save the Amin  meaning to register the admin first
	@PostMapping("/RegisterAdmin")
	public ResponseEntity<ResponseStructure<Users>> saveAdmin(@RequestBody Users user) {
		return userservice.saveUsers(user);
	}

	// this is the method where admin can save the trainers
	@PostMapping("/SaveTrainer")	
	public ResponseEntity<ResponseStructure<Users>> saveTrainer(@RequestBody Users user) {
		return userservice.saveUsers(user);

	}
	// save student method
	@PostMapping("/SaveStudent")
	public ResponseEntity<ResponseStructure<Users>> saveStudent(@RequestBody Users user) {
		return userservice.saveUsers(user);

	}
	//method to save the course
	@PostMapping("/SaveCourse")
	public ResponseEntity<ResponseStructure<Courses>> saveCourse(@RequestBody Courses course) {
		return coursesservice.saveCourse(course);

	}
    // method to get all the Trainers
	@GetMapping("/getAllTrainers")
	public List<TrainersResponse> GetAllTrainers() {
		return userservice.getAllTrainers();

	}
	// method to get all the Courses
	@GetMapping("/getAllCourses")
	public List<Courses>  GetAllCourses() {
		return coursesservice.getAllCourse();
		
	}
	@PostMapping("/createBatches")
	public ResponseEntity<ResponseStructure<Batches>> createBatches(@RequestBody Batches batch) {
		return  batchservice.saveBatches(batch);
	}
   
    //assigining  batches to the student
	
	@PostMapping("/assignStudentToBatch")
	public ResponseEntity<ResponseStructure<Users>> assignStudentToBatch(
	        @RequestBody AssigningBatchToStudent request) {

	    return batchservice.assignStudentsToBatch(
	            request.getStudentId(),
	            request.getBatchId()
	    );
	}

	
	
}
