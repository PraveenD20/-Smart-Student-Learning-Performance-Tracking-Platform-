package com.alpha.SmartStudentTracker.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Assesment; 
import com.alpha.SmartStudentTracker.entity.Batches;
import com.alpha.SmartStudentTracker.entity.Subject;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.exception.AssesmentNotFoundException;
import com.alpha.SmartStudentTracker.exception.BatchNotFoundException;
import com.alpha.SmartStudentTracker.exception.SubjectNotFoundException;
import com.alpha.SmartStudentTracker.exception.UserNotFoundException;
import com.alpha.SmartStudentTracker.repository.AssesmentRepository;
//import com.alpha.SmartStudentTracker.repository.AssesmentResultRepository;
import com.alpha.SmartStudentTracker.repository.AssesmentSubmissionRepository;
import com.alpha.SmartStudentTracker.repository.BatchesRepository;
import com.alpha.SmartStudentTracker.repository.SubjectRepository;
import com.alpha.SmartStudentTracker.repository.UserRepository;

@Service
public class AssesmentService {
	@Autowired
	private AssesmentRepository assesmentRepository;
	@Autowired
	private BatchesRepository batchesRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  AssesmentSubmissionRepository assesmentSubmissionRepository;
	
	public ResponseEntity<ResponseStructure<Assesment>> saveAssesment(Assesment assesment) {
		assesment.setDate(LocalDate.now());
		
		ResponseStructure<Assesment> rs=new ResponseStructure<Assesment>();
		 
		Subject subject=subjectRepository.findById(assesment.getSubject().getId()).orElseThrow( ( ) -> new SubjectNotFoundException("Subject Not Found") );
		Users trainer=userRepository.findById(assesment.getTrainer().getId()).orElseThrow( ( ) -> new UserNotFoundException("Trainer Not Found") ); 
 
        assesment.setSubject(subject);
        assesment.setTrainer(trainer);
        assesment.setStatus("Created");
          
        Optional<Assesment> opt=assesmentRepository.findByTrainerAndSubjectAndDate(trainer, subject, assesment.getDate());
        
		if(opt.isPresent()) {
			rs.setStatuscode(HttpStatus.CONFLICT.value());
			rs.setMessage("Already Exist");
			rs.setData( opt.get());
		}
		Assesment assesment2=assesmentRepository.save(assesment);
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Assesment Saved");
		rs.setData(assesment2);
		
		return new ResponseEntity<ResponseStructure<Assesment>>(rs,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Assesment>> assignAssesmentToBatch( Integer assesmentid,Integer batchid) {
	
		ResponseStructure<Assesment> rs=new ResponseStructure<Assesment>();
		
		Assesment assesment=assesmentRepository.findById(assesmentid).orElseThrow( ( )-> new AssesmentNotFoundException("Assesment Not Found") );
		Batches batch=batchesRepository.findById(batchid).orElseThrow( ( ) -> new BatchNotFoundException("Batch Not Found"));
		
		assesment.setBatch(batch);
		assesment.setStatus("Assigned to Batch");
		
		Assesment assesment2=assesmentRepository.save(assesment);
		
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Assesment assigned to Batch");
		rs.setData(assesment2);
		
		return new ResponseEntity<ResponseStructure<Assesment>>(rs, HttpStatus.OK); 	
		}
	
	

}
