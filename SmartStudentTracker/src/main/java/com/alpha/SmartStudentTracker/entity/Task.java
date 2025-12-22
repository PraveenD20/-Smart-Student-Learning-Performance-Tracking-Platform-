package com.alpha.SmartStudentTracker.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String description;
	private LocalDate date;
	@ManyToOne
	@JoinColumn(name="batch_id")
	private Batches batch;
	@ManyToOne
	@JoinColumn(name="trainer_id")
	private Users trainer;
	
	public Task() {
		super();
	}
	public Task(Integer id, String title, String description, Batches batch, Users trainer) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.batch = batch;
		this.trainer = trainer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Batches getBatch() {
		return batch;
	}
	public void setBatch(Batches batch) {
		this.batch = batch;
	}
	public Users getTrainer() {
		return trainer;
	}
	public void setTrainer(Users trainer) {
		this.trainer = trainer;
	}
	
	

}
