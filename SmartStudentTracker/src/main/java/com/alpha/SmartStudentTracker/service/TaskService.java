package com.alpha.SmartStudentTracker.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Batches;
import com.alpha.SmartStudentTracker.entity.Task;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.exception.BatchNotFoundException;
import com.alpha.SmartStudentTracker.exception.TaskNotFoundException;
import com.alpha.SmartStudentTracker.exception.UserNotFoundException;
import com.alpha.SmartStudentTracker.repository.BatchesRepository;
import com.alpha.SmartStudentTracker.repository.TaskRepository;
import com.alpha.SmartStudentTracker.repository.UserRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private BatchesRepository batchesRepository;
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<ResponseStructure<Task>> saveTask(Task task) {
		ResponseStructure<Task> rs=new ResponseStructure<Task>();
		
 	Users user=userRepository.findById(task.getTrainer().getId()).orElseThrow( () -> new UserNotFoundException("User Not Found")); 
	 String title=task.getTitle();
	 String description=task.getDescription();
 	
		Optional<Task> opt=taskRepository.findByTitleAndDescription(title,description);
		
		if(opt.isPresent()) {
			rs.setStatuscode(HttpStatus.CONFLICT.value());
			rs.setMessage("Task Already Exist");
			rs.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Task>>(rs, HttpStatus.CONFLICT);
		}
		task.setDate(LocalDate.now());
 
        task.setTrainer(user);
        task.setStatus("Created");
        
        Task task2=taskRepository.save(task);
        
        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Task Saved/Created");
        rs.setData(task2);
        
		return new ResponseEntity<ResponseStructure<Task>>(rs, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Task>> assignTaskToBatch(Integer taskid,Integer batchid) {
		
		ResponseStructure<Task> rs=new ResponseStructure<Task>();
		
		 Task task=taskRepository.findById(taskid).orElseThrow( ( ) -> new TaskNotFoundException("Task Not Found"));
		 Batches batch=batchesRepository.findById(batchid).orElseThrow( ( ) -> new BatchNotFoundException("Batch Not Found") );
		 
		 task.setBatch(batch);
		 task.setDate(LocalDate.now());
		 task.setStatus("Task Assigned");
		Task task2= taskRepository.save(task);
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Task Assigned to Batch");
		rs.setData(task2);
		
		return new ResponseEntity<ResponseStructure<Task>>(rs,HttpStatus.OK);
		 
	}
	

}
