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
	 
		//check wether the task is created by the trainer or not
     Users user=userRepository.findById(task.getTrainer().getId()).orElseThrow( () -> new UserNotFoundException("User Not Found")); 
	 String title=task.getTitle();
	 String description=task.getDescription();
 	    
	 //if the task is already there dont create agaain
		Optional<Task> opt=taskRepository.findByTitleAndDescription(title,description);
		
		if(opt.isPresent()) {
			rs.setStatuscode(HttpStatus.CONFLICT.value());
			rs.setMessage("Task Already Exist");
			rs.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Task>>(rs, HttpStatus.CONFLICT);
		}
		//set the date on wich the task is created
		task.setDate(LocalDate.now());
 
        task.setTrainer(user);
        task.setStatus("Created");
        //save the task if it is not there/created
        Task task2=taskRepository.save(task);
        
        rs.setStatuscode(HttpStatus.OK.value());
        rs.setMessage("Task Saved/Created");
        rs.setData(task2);
        
		return new ResponseEntity<ResponseStructure<Task>>(rs, HttpStatus.OK);
	}
	
	// assign the task to batch
	public ResponseEntity<ResponseStructure<Task>> assignTaskToBatch(Integer taskid,Integer batchid) {
		
		ResponseStructure<Task> rs=new ResponseStructure<Task>();
		
	//	check wether the task and batch is there if not throw exception
		
		 Task task=taskRepository.findById(taskid).orElseThrow( ( ) -> new TaskNotFoundException("Task Not Found"));
		 Batches batch=batchesRepository.findById(batchid).orElseThrow( ( ) -> new BatchNotFoundException("Batch Not Found") );
		 //set the baseic values
		 task.setBatch(batch);
		 task.setDate(LocalDate.now());
		 task.setStatus("Task Assigned");
		 
		 //save the task meaning update the existing task
		Task task2= taskRepository.save(task);
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Task Assigned to Batch");
		rs.setData(task2);
		
		return new ResponseEntity<ResponseStructure<Task>>(rs,HttpStatus.OK);
		 
	}
	

}
