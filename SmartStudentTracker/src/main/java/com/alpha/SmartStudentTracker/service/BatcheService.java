package com.alpha.SmartStudentTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.SmartStudentTracker.dto.AssigningBatchToStudent;
import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Batches;
import com.alpha.SmartStudentTracker.entity.Courses;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.exception.BatchNotFoundException;
import com.alpha.SmartStudentTracker.exception.CourseNotFoundException;
import com.alpha.SmartStudentTracker.exception.UserNotFoundException;
import com.alpha.SmartStudentTracker.repository.BatchesRepository;
import com.alpha.SmartStudentTracker.repository.CoursesRepository;
import com.alpha.SmartStudentTracker.repository.UserRepository;

@Service
public class BatcheService {
	@Autowired
	private BatchesRepository batchesrepository;
	@Autowired
	private CoursesRepository coursesRepository;
	@Autowired
	private UserRepository usersRepository;
	
		public ResponseEntity<ResponseStructure<Batches>> saveBatches(Batches batch) {
			Optional<Batches> opt=batchesrepository.findByBatchname(batch.getBatchname());
			ResponseStructure<Batches> rs=new ResponseStructure<Batches>();
			
			 if(opt.isPresent()) {
				 rs.setStatuscode(HttpStatus.CONFLICT.value());
				 rs.setMessage("Batche is already exist");
				 rs.setData(opt.get());
				 return new ResponseEntity<ResponseStructure<Batches>>(rs,HttpStatus.CONFLICT); //209 conflict
			 }
			 
			 //   IMPORTANT PART
			 //   fetching the details or like basic details of the trainer and users from the db
			    Integer courseId = batch.getCourse().getId();
			    Integer trainerId = batch.getTrainer().getId();
                
			    
			    //checking weather the given id by the user are present or not
			    
			    Courses course = coursesRepository.findById(courseId)
			            .orElseThrow(() -> new CourseNotFoundException("Course not found"));

			    Users trainer = usersRepository.findById(trainerId)
			            .orElseThrow(() -> new UserNotFoundException("Trainer not found"));

			    // attach managed entities
			    batch.setCourse(course);
			    batch.setTrainer(trainer);
			 
			 Batches saved=batchesrepository.save(batch);
			 rs.setStatuscode(HttpStatus.OK.value());
			 rs.setMessage("Batches is saved with");
			 rs.setData(saved);
			 return new ResponseEntity<ResponseStructure<Batches>>(rs,HttpStatus.OK); //200 OK
		}
	
		public ResponseEntity<ResponseStructure<Users>> assignStudentsToBatch(Integer studentId, Integer batchId) {
			ResponseStructure<Users> rs=new ResponseStructure<Users>();
			
			//fetching user meaning student details
			Users student=usersRepository.findById(studentId).
					orElseThrow(() -> new UserNotFoundException("Student with given Id is not Found") );
			
			if(!student.getRole().equals("STUDENT")) {
				throw new RuntimeException("Batch can be Assigned to Students Only");
				
			}
			
			//now cross checking with the batch details
			Batches bacth=batchesrepository.findById(batchId)
					.orElseThrow( () -> new BatchNotFoundException("Batch with provided id is not found")); 
			
			student.setBatch(bacth);
			
			Users updatedStudent=usersRepository.save(student);
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setMessage("Student Assigned to the Batch"+updatedStudent.getBatch());
			rs.setData(updatedStudent);
			return new ResponseEntity<ResponseStructure<Users>>(rs,HttpStatus.OK);
		}
		
		// assigning the Trainer To Batch Or assigning the Batch to the trainer
		public ResponseEntity<ResponseStructure<String>> assignBatchToTrainer(Integer trainerid,Integer batchid) {
			ResponseStructure<String> rs=new ResponseStructure<String>();
			Users trainer=usersRepository.findById(trainerid).orElseThrow(( ) -> new UserNotFoundException("User Not Found"));
			Batches batch=batchesrepository.findById(batchid).orElseThrow( ( ) -> new BatchNotFoundException("Batch Not Found"));
			
			trainer.setBatch(batch);
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setMessage("Batch Aasigned to Traine");
			rs.setData(trainer.getName()+" is Assigned to "+batch.getId());
			
            return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.OK);
			
		}
		
	

}
