package com.alpha.SmartStudentTracker.service;

import java.net.http.HttpClient;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Assesment;
import com.alpha.SmartStudentTracker.entity.AssesmentSubmission;
import com.alpha.SmartStudentTracker.entity.Review;
import com.alpha.SmartStudentTracker.entity.Task;
import com.alpha.SmartStudentTracker.entity.TaskSubmission;
import com.alpha.SmartStudentTracker.exception.AssesmentNotFoundException;
import com.alpha.SmartStudentTracker.exception.AssesmentSubNotFoundException;
import com.alpha.SmartStudentTracker.exception.InvalidDataException;
import com.alpha.SmartStudentTracker.exception.TaskNotFoundException;
import com.alpha.SmartStudentTracker.repository.AssesmentRepository;
import com.alpha.SmartStudentTracker.repository.AssesmentSubmissionRepository;
import com.alpha.SmartStudentTracker.repository.ReviewRepository;
import com.alpha.SmartStudentTracker.repository.TaskRepository;
import com.alpha.SmartStudentTracker.repository.TaskSubRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskSubRepository taskSubRepository;
	@Autowired
	private AssesmentRepository assesmentRepository;
	@Autowired
	private AssesmentSubmissionRepository assesmentSubmissionRepository;
	
	
	public ResponseEntity<ResponseStructure<Review>> reviewTaskAssesment(Review review) {
		
		ResponseStructure<Review> rs=new ResponseStructure<Review>();
		
		Optional<Review> opt=reviewRepository.findBySubmissiontypeAndSubid(review.getSubmissiontype(), review.getSubid());
		
		if(opt.isPresent()) {
			 
			rs.setStatuscode(HttpStatus.CONFLICT.value());
			rs.setMessage("Already Reviewd");
			rs.setData(opt.get());
			
			return new ResponseEntity<ResponseStructure<Review>>(rs,HttpStatus.CONFLICT);
	     }
		
		String subtype1=review.getSubmissiontype().toLowerCase();
		Integer id=review.getSubid();
		
		  if(subtype1.equals("task")) {
			  
			TaskSubmission taskSubmission=taskSubRepository.findById(id)
					.orElseThrow( ( )-> new AssesmentSubNotFoundException("Task not at Submitted")); 
			
			Task task=taskRepository.findById(id).orElseThrow( ( )-> new TaskNotFoundException("Task Not Found "));
			task.setStatus("Reviewed");
			
			Review review2=reviewRepository.save(review);
			
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setMessage("Task Reviewd");
			rs.setData(review2);
			
			return new ResponseEntity<ResponseStructure<Review>>(rs,HttpStatus.OK);
		 	 
		}
		  if(subtype1.equals("assesment")) {
				
//				Assesment assesment=assesmentRepository.findById(id).orElseThrow( ( )-> new AssesmentNotFoundException("Assesment Not Found "));
//				assesment.setStatus("Reviewed"); 
			  
			  AssesmentSubmission asSubmission=assesmentSubmissionRepository.findById(id)
					  .orElseThrow( ( )-> new AssesmentSubNotFoundException("Assement Not at Submitted"));
			  Assesment assesment=assesmentRepository.findById(asSubmission.getAssesment().getId()).orElseThrow( ( )-> new AssesmentNotFoundException("Assesment Not Found "));
			  assesment.setStatus("Reviewed"); 
			  
				
				Integer marks=review.getMarksObtained();
				Integer maxmarks=assesment.getMaxmarks();
				if(marks>maxmarks || marks<0) throw new  InvalidDataException("You can not award More than the maximum marks/ Less than the 0");
				 
				Review review2=reviewRepository.save(review);
				
				rs.setStatuscode(HttpStatus.OK.value());
				rs.setMessage("Assesment Reviewd");
				rs.setData(review2);
				
				return new ResponseEntity<ResponseStructure<Review>>(rs,HttpStatus.OK);
			 	 
			}
		return null;

    }

}