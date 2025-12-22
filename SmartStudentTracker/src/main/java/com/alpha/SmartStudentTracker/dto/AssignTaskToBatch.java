package com.alpha.SmartStudentTracker.dto;

public class AssignTaskToBatch {
	
	private Integer taskid;
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
