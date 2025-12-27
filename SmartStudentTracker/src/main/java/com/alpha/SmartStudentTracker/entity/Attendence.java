package com.alpha.SmartStudentTracker.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Attendence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate date; //local date will be set in the service logic
	@NotEmpty
	private String status;
	@ManyToOne
	@JoinColumn(name="student_id")
	private Users student;
	@ManyToOne
	@JoinColumn(name = "batch_id")
	private Batches batch;
	public Attendence() {
		super();
	}
	public Attendence(Integer id, String status, Users student, Batches batch) {
		super();
		this.id = id;
		this.status = status;
		this.student = student;
		this.batch = batch;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Users getStudent() {
		return student;
	}
	public void setStudent(Users student) {
		this.student = student;
	}
	public Batches getBatch() {
		return batch;
	}
	public void setBatch(Batches batch) {
		this.batch = batch;
	}
	 
	

}
