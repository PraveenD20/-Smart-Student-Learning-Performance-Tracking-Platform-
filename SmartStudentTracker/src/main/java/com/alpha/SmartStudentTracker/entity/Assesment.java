package com.alpha.SmartStudentTracker.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Assesment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @ManyToOne
    @JoinColumn(name="batch_id")
	private Batches batch;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name="trainer_id")
	private Users trainer;
    private Integer maxMarks;
    private LocalDate date;
    
    
	public Assesment() {
		super();
	}

	public Assesment(Integer id, Batches batch, Subject subject, Users trainer, Integer maxMarks) {
		super();
		this.id = id;
		this.batch = batch;
		this.subject = subject;
		this.trainer = trainer;
		this.maxMarks = maxMarks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Batches getBatch() {
		return batch;
	}

	public void setBatch(Batches batch) {
		this.batch = batch;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Users getTrainer() {
		return trainer;
	}

	public void setTrainer(Users trainer) {
		this.trainer = trainer;
	}

	public Integer getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(Integer maxMarks) {
		this.maxMarks = maxMarks;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
    
    
    
}
