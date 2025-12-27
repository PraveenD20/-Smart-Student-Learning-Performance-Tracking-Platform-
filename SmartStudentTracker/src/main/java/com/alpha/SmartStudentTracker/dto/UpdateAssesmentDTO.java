package com.alpha.SmartStudentTracker.dto;

import com.alpha.SmartStudentTracker.entity.Batches;
import com.alpha.SmartStudentTracker.entity.Subject;
import com.alpha.SmartStudentTracker.entity.Users;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class UpdateAssesmentDTO {
	@NotNull
	private Integer assesmentid;
	private Batches batch;
    private Subject subject;
	private Users trainer;
    private Integer maxmarks;
	public Integer getAssesmentid() {
		return assesmentid;
	}
	public void setAssesmentid(Integer assesmentid) {
		this.assesmentid = assesmentid;
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
	public Integer getMaxmarks() {
		return maxmarks;
	}
	public void setMaxmarks(Integer maxmarks) {
		this.maxmarks = maxmarks;
	}
    
    

}
