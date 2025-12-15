package com.alpha.SmartStudentTracker.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Courses {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String coursename;
	private String description;
	public Courses() {
		super();
	}
	public Courses(Integer id, String coursename, String description) {
		super();
		this.id = id;
		this.coursename = coursename;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
