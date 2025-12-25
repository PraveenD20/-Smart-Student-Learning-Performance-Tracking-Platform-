package com.alpha.SmartStudentTracker.service;

import java.net.ResponseCache;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Task;
import com.alpha.SmartStudentTracker.entity.TaskSubmission;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.exception.TaskNotFoundException;
import com.alpha.SmartStudentTracker.exception.UserNotFoundException;
import com.alpha.SmartStudentTracker.repository.TaskRepository;
import com.alpha.SmartStudentTracker.repository.TaskSubRepository;
import com.alpha.SmartStudentTracker.repository.UserRepository;

@Service
public class TasksSubmissionService {
	@Autowired
	private TaskSubRepository taskSubRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<ResponseStructure<TaskSubmission>> submitTask(TaskSubmission taskSubmission) {
		
		ResponseStructure<TaskSubmission> rs=new ResponseStructure<TaskSubmission>();
		
		Task task=taskRepository.findById(taskSubmission.getTask().getId()).orElseThrow( ( ) -> new TaskNotFoundException("Task Not Found") );
		task.setStatus("Submitted");
//		taskSubmission.setTask(task);
		
		Users student=userRepository.findById(taskSubmission.getStudent().getId()).orElseThrow( ( ) -> new UserNotFoundException("Student Not Found"));
		taskSubmission.setStudent(student);
		taskSubmission.setTask(task);
		
		
		Optional<TaskSubmission> opt=taskSubRepository.findByTaskAndStudent(task, student);
		
		if(opt.isPresent()) {
			
			rs.setStatuscode(HttpStatus.CONFLICT.value());
			rs.setMessage("Task Already submitted ");
			rs.setData(opt.get());
			 
			return new ResponseEntity<ResponseStructure<TaskSubmission>>(rs,HttpStatus.CONFLICT);
		}
		
		
		TaskSubmission taskSubmission2=taskSubRepository.save(taskSubmission);
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Task Submitted");
		rs.setData(taskSubmission2);
		
		return new ResponseEntity<ResponseStructure<TaskSubmission>>(rs,HttpStatus.OK);
	}
	
	
//	public void evaluateTask(Integer tasksubmiId) {
//		
//	}
	
	
	
	public ResponseEntity<ResponseStructure<TaskSubmission>> getTaskResult(Integer studentid,Integer taskid) {
		
		ResponseStructure<TaskSubmission> rs=new ResponseStructure<TaskSubmission>();
		
		Users student=userRepository.findById(studentid).orElseThrow( ( )-> new UserNotFoundException("Student Not Found"));
		Task task=taskRepository.findById(taskid).orElseThrow( ( )-> new TaskNotFoundException("Task Not Found"));
		
		TaskSubmission taskSubmission=taskSubRepository.findByTaskAndStudent(task,student).orElseThrow( ( )-> new TaskNotFoundException("Task Not Found"));
		
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("TaskResults are as Falow");
		rs.setData(taskSubmission);
		
		return new ResponseEntity<ResponseStructure<TaskSubmission>>(rs,HttpStatus.OK);
		
	}
	

}
