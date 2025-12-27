package com.alpha.SmartStudentTracker.dto;

import jakarta.validation.constraints.NotNull;

public class AssigningBatchToStudent {
	@NotNull
	private Integer studentId;
	@NotNull
	private Integer batchId;
	public AssigningBatchToStudent() {
		super();
	}
	public AssigningBatchToStudent(Integer studentId, Integer batchId) {
		super();
		this.studentId = studentId;
		this.batchId = batchId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	
	

}
