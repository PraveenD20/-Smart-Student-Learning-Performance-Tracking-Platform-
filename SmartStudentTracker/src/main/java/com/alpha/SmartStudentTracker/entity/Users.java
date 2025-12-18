package com.alpha.SmartStudentTracker.entity;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Users{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String contact;
	private String email;
	private String password;
	private String role;
	@ManyToOne
	@JoinColumn(name="batch_id")
	private Batches batch;

	public Users() {
		super();
	}

	public Users(Integer id, String name, String contact, String email, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.email = email; 
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	//for batch id only for assigning to student 
	public Batches getBatch() {
	    return batch;
	}

	public void setBatch(Batches batch) {
	    this.batch = batch;
	}




}