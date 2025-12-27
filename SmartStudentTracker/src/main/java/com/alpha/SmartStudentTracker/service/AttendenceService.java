package com.alpha.SmartStudentTracker.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Attendence;
import com.alpha.SmartStudentTracker.entity.Batches;
import com.alpha.SmartStudentTracker.exception.BatchNotFoundException;
import com.alpha.SmartStudentTracker.exception.UserNotFoundException;
import com.alpha.SmartStudentTracker.repository.AttendenceRepository; 
import com.alpha.SmartStudentTracker.repository.BatchesRepository;
import com.alpha.SmartStudentTracker.repository.UserRepository; 
import com.alpha.SmartStudentTracker.entity.Users;

@Service
public class AttendenceService {
	
	@Autowired
	private AttendenceRepository attendenceRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BatchesRepository batchesRepository;
	
	
	// mark the attendence 
	public ResponseEntity<ResponseStructure<Attendence>> markAttendence(Attendence attendence) {
		ResponseStructure<Attendence> rs=new ResponseStructure<Attendence>();
		
		// checck weather the given student id is there are not if not throw exception
		Users student=userRepository.findById(attendence.getStudent().getId())
				.orElseThrow( () -> new UserNotFoundException("Student Not Found"));
		
		//to check the batch is valid or not if not throw exception
		Batches batch=batchesRepository.findById(attendence.getBatch().getId())
				.orElseThrow( () -> new BatchNotFoundException("Batch Not Found"));
		
		
        //		Attendence attend=new Attendence();
 	
		attendence.setBatch(batch);
		attendence.setStudent(student);
		attendence.setDate(LocalDate.now());
		
		Attendence attend=attendenceRepository.save(attendence);
		
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Attendence Marked");
		rs.setData(attend);
		
		return new ResponseEntity<ResponseStructure<Attendence>>(rs,HttpStatus.OK);
	}
	 //get the attendence of the studnet
	public ResponseEntity<ResponseStructure<String>> getAttendence(Integer studentId,LocalDate date) {
		ResponseStructure<String> rs=new ResponseStructure<String>();
		
		//check wethere the student is valid or not if not throw exception
		Users student=userRepository.findById(studentId).orElseThrow( () -> new UserNotFoundException("Student Not Found") );
		//find the attendence wether is the student id and on perticular date wether he is present o not if not throw error 
		Attendence attend=attendenceRepository.findByStudentAndDate(student,date).orElseThrow(()-> new UserNotFoundException("Student Not Found") );
		
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage(attend.getDate()+" "+attend.getStatus());
        rs.setData(null);
        
        return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.OK);
	}
	
	
}
