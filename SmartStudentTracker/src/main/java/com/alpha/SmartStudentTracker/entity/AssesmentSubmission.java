package com.alpha.SmartStudentTracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AssesmentSubmission{
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "assesment_id")
	private Assesment assesment;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Users student; 
	private String status;
	
	public AssesmentSubmission() {
		super();
	}

	public AssesmentSubmission(Integer id, Assesment assesment, Users student, String status) {
		super();
		this.id = id;
		this.assesment = assesment;
		this.student = student;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Assesment getAssesment() {
		return assesment;
	}

	public void setAssesment(Assesment assesment) {
		this.assesment = assesment;
	}

	public Users getStudent() {
		return student;
	}

	public void setStudent(Users student) {
		this.student = student;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
  
}
