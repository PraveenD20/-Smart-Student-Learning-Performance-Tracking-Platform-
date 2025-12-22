package com.alpha.SmartStudentTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Assesment;
import com.alpha.SmartStudentTracker.entity.AssesmentResult;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.exception.AssesmentNotFoundException;
import com.alpha.SmartStudentTracker.exception.UserNotFoundException;
import com.alpha.SmartStudentTracker.repository.AssesmentRepository;
import com.alpha.SmartStudentTracker.repository.AssesmentResultRepository;
import com.alpha.SmartStudentTracker.repository.UserRepository;

@Service
public class AssesmentResultService {
	@Autowired 
	private AssesmentResultRepository assesmentResultRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AssesmentRepository assesmentRepository;
	
	public ResponseEntity<ResponseStructure<AssesmentResult>>saveResult(AssesmentResult assesmentResult) {
		
		ResponseStructure<AssesmentResult> rs=new ResponseStructure<AssesmentResult>();
		 
		//check weather the given student and assesment are valid or not
		Users studnet=userRepository.findById(assesmentResult.getStudent().getId()).orElseThrow( ( ) -> new UserNotFoundException("User Not Found"));
		Assesment assesment=assesmentRepository.findById(assesmentResult.getAssesment().getId()).orElseThrow( ( ) -> new AssesmentNotFoundException("User Not Found") );
		
		Optional<AssesmentResult> opt=assesmentResultRepository.findByStudentAndAssesment(studnet,assesment);
		
		
		assesmentResult.setAssesment(assesment);
		assesmentResult.setStudent(studnet);
		
		if(opt.isPresent()) {
			rs.setStatuscode(HttpStatus.CONFLICT.value());
			rs.setMessage("Alredy Updated");
			rs.setData(opt.get());
			return new ResponseEntity<ResponseStructure<AssesmentResult>>(rs,HttpStatus.CONFLICT);
		}
		AssesmentResult assesmentResult2=assesmentResultRepository.save(assesmentResult);
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Result saved");
		rs.setData(assesmentResult2);
		return new ResponseEntity<ResponseStructure<AssesmentResult>>(rs,HttpStatus.OK);
		
		
	}

   public ResponseEntity<ResponseStructure<AssesmentResult>> getResult(Integer studentid,Integer assesmentid) {
		
	    ResponseStructure<AssesmentResult> rs=new ResponseStructure<AssesmentResult>();
	    
	    Users student=userRepository.findById(studentid).orElseThrow( ( ) -> new UserNotFoundException("Student Not Found"));
	    Assesment assesment=assesmentRepository.findById(assesmentid).orElseThrow( ( )-> new AssesmentNotFoundException("Assesment Not Found") );
	    
	    AssesmentResult assesmentResult=assesmentResultRepository.findByStudentAndAssesment(student, assesment).orElseThrow( ( )-> new RuntimeException("Assesment Results Not Found") ) ;
		
	    rs.setStatuscode(HttpStatus.OK.value());
	    rs.setMessage("Assesment results are as Fallow");
	    rs.setData(assesmentResult);
	    
	    return new ResponseEntity<ResponseStructure<AssesmentResult>>(rs,HttpStatus.OK);
	    
	}
	
	
}
