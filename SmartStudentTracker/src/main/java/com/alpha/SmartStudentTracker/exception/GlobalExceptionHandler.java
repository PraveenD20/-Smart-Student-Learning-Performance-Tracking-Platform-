package com.alpha.SmartStudentTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//exception handler to handle the UserNotfound
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserNotFoundException( UserNotFoundException e) {
		ResponseStructure<String> rs=new ResponseStructure<String>();
		
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage( e.getMessage());
		rs.setData("Not_Found");

		return new  ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleBatchNotFoundException(BatchNotFoundException e) {
        ResponseStructure<String> rs=new ResponseStructure<String>();
		
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Batch Not Found");
		rs.setData("Not_Found");

		return new  ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
		
	}
	
    @ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleCourseNotFoundException(CourseNotFoundException e) {
        ResponseStructure<String> rs=new ResponseStructure<String>();
		
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Course Not Found");
		rs.setData("Not_Found");

		return new  ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	
    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleSubjectNotFoundException(SubjectNotFoundException e) {
    	ResponseStructure<String> rs=new ResponseStructure<String>();
    	
    	rs.setStatuscode(HttpStatus.NOT_FOUND.value());
    	rs.setMessage("Subject Not Found");
    	rs.setData(null);
    	
    	return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(AssesmentNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleAssesmentNotFoundException(AssesmentNotFoundException e) {
    	ResponseStructure<String> rs=new ResponseStructure<String>();
    	
    	rs.setStatuscode(HttpStatus.NOT_FOUND.value());
    	rs.setMessage("Assesment Not Found");
    	rs.setData(null);
     
    	return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
    	
    }
    
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleTaskNotFoundException(TaskNotFoundException e) {
    	ResponseStructure<String> rs=new ResponseStructure<String>();
    	
    	rs.setStatuscode(HttpStatus.NOT_FOUND.value());
    	rs.setMessage("Task Not Found");
    	rs.setData(null);
    	return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
    }
   
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> handleInvalidDataException( InvalidDataException e) {
    	ResponseStructure<String> rs=new ResponseStructure<String>();
    	
    	rs.setStatuscode(HttpStatus.BAD_REQUEST.value());
    	rs.setMessage("Invalid Data Insertion");
    	rs.setData("Something went wrong");
    	
    	return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AssesmentSubNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleAssesmentSubNotFoundException(AssesmentSubNotFoundException e) {
    	ResponseStructure<String> rs=new ResponseStructure<String>();
    	
    	rs.setStatuscode(HttpStatus.NOT_FOUND.value());
    	rs.setMessage(e.getMessage());
    	rs.setData("Submit Assesment/Task");
    	
    	return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
    }
}
