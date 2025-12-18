package com.alpha.SmartStudentTracker.dto;

public class AssigningBatchToStudent {
	private Integer studentId;
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
