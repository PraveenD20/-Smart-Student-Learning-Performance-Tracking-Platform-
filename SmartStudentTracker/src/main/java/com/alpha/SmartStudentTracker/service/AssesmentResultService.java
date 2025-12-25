package com.alpha.SmartStudentTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Assesment; 
import com.alpha.SmartStudentTracker.entity.AssesmentSubmission;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.exception.AssesmentNotFoundException;
import com.alpha.SmartStudentTracker.exception.InvalidDataException;
import com.alpha.SmartStudentTracker.exception.UserNotFoundException;
import com.alpha.SmartStudentTracker.repository.AssesmentRepository; 
import com.alpha.SmartStudentTracker.repository.AssesmentSubmissionRepository;
import com.alpha.SmartStudentTracker.repository.UserRepository;

@Service
public class AssesmentResultService {
	@Autowired 
	private AssesmentSubmissionRepository assesmentSubmissionRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AssesmentRepository assesmentRepository;
	
	public ResponseEntity<ResponseStructure<AssesmentSubmission>>saveResult(AssesmentSubmission assesmentResult) {
		
		ResponseStructure<AssesmentSubmission> rs=new ResponseStructure<AssesmentSubmission>();
		 
		//check weather the given student and assesment are valid or not
		Users studnet=userRepository.findById(assesmentResult.getStudent().getId()).orElseThrow( ( ) -> new UserNotFoundException("User Not Found"));
		Assesment assesment=assesmentRepository.findById(assesmentResult.getAssesment().getId()).orElseThrow( ( ) -> new AssesmentNotFoundException("User Not Found") );

//		
		assesment.setStatus("Submitted");
		Optional<AssesmentSubmission> opt= assesmentSubmissionRepository.findByStudentAndAssesment(studnet,assesment);
		
		
		assesmentResult.setAssesment(assesment);
		assesmentResult.setStudent(studnet);
		
		if(opt.isPresent()) {
			rs.setStatuscode(HttpStatus.CONFLICT.value());
			rs.setMessage("Alredy Updated");
			rs.setData(opt.get());
			return new ResponseEntity<ResponseStructure<AssesmentSubmission>>(rs,HttpStatus.CONFLICT);
		}
		AssesmentSubmission assesmentResult2= assesmentSubmissionRepository.save(assesmentResult);
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Result saved");
		rs.setData(assesmentResult2);
		return new ResponseEntity<ResponseStructure<AssesmentSubmission>>(rs,HttpStatus.OK);
		
		
	}

   public ResponseEntity<ResponseStructure<AssesmentSubmission>> getResult(Integer studentid,Integer assesmentid) {
		
	    ResponseStructure<AssesmentSubmission> rs=new ResponseStructure<AssesmentSubmission>();
	    
	    Users student=userRepository.findById(studentid).orElseThrow( ( ) -> new UserNotFoundException("Student Not Found"));
	    Assesment assesment=assesmentRepository.findById(assesmentid).orElseThrow( ( )-> new AssesmentNotFoundException("Assesment Not Found") );
	    
	    AssesmentSubmission assesmentResult= assesmentSubmissionRepository.findByStudentAndAssesment(student, assesment).orElseThrow( ( )-> new RuntimeException("Assesment Results Not Found") ) ;
		
	    rs.setStatuscode(HttpStatus.OK.value());
	    rs.setMessage("Assesment results are as Fallow");
	    rs.setData(assesmentResult);
	    
	    return new ResponseEntity<ResponseStructure<AssesmentSubmission>>(rs,HttpStatus.OK);
	    
	}
	
	
}
