package com.alpha.SmartStudentTracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TaskSubmission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;
	@ManyToOne
	@JoinColumn(name="student_id")
	private Users student;
	
	 
	public TaskSubmission() {
		super();
	}
	public TaskSubmission(Integer id, Task task, Users student, String status) {
		super();
		this.id = id;
		this.task = task;
		this.student = student;
		 
		 
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Users getStudent() {
		return student;
	}
	public void setStudent(Users student) {
		this.student = student;
	}
	 
	 
	
}
