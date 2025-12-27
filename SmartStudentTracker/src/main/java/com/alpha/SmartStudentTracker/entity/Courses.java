package com.alpha.SmartStudentTracker.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Courses {
	
	//course id based
	@Id
	private Integer id;
	@NotEmpty
	@Length(min=3)
	private String coursename;
	@NotEmpty 
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
