package com.alpha.SmartStudentTracker.entity;

import org.hibernate.annotations.ManyToAny;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Batches {
	@Id
	private Integer id;
	private String batchename;

	@ManyToOne 
	@JoinColumn(name = "trainer_id",nullable = false)
    private Users trainer_id1;
	@ManyToOne
	@JoinColumn(name="course_id",nullable = false)
	private Courses course_id;

	public Batches(Integer id, String batchename, Users trainer_id, Courses course_id) {
		super();
		this.id = id;
		this.batchename = batchename;
		this.trainer_id1 = trainer_id;
		this.course_id = course_id;
	}

	private Users trainer_id;

	public Batches() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBatchename() {
		return batchename;
	}

	public void setBatchename(String batchename) {
		this.batchename = batchename;
	}

	public Courses getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Courses course_id) {
		this.course_id = course_id;
	}

	public Users getTrainer_id() {
		return trainer_id1;
	}

	public void setTrainer_id(Users trainer_id) {
		this.trainer_id1 = trainer_id;
	}
	



}
