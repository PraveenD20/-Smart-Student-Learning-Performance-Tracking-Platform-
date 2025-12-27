package com.alpha.SmartStudentTracker.dto;

import jakarta.validation.constraints.NotNull;

public class AssignTaskToBatch {
	@NotNull
	private Integer taskid;
	@NotNull
	private Integer batchid;
	
	public AssignTaskToBatch() {
		 
	}
	public AssignTaskToBatch(Integer taskid, Integer batchid) {
		super();
		this.taskid = taskid;
		this.batchid = batchid;
	}
	public Integer getTaskid() {
		return taskid;
	}
	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}
	public Integer getBatchid() {
		return batchid;
	}
	public void setBatchid(Integer batchid) {
		this.batchid = batchid;
	}
	
	
	

}
