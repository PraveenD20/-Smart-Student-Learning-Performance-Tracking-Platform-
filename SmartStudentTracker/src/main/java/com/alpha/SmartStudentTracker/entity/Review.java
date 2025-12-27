package com.alpha.SmartStudentTracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String submissiontype; //task or assesment
	@NotNull
	private Integer subid;
	@Positive
	private Integer marksObtained;
	@NotEmpty
	private String review;

	private Integer assesmentid;
	
	public Review() {
		super();
	}
	
	public Review(Integer id, String submissiontype, Integer subid,
			Integer marksObtained) {
		super();
		this.id = id; 
		this.submissiontype = submissiontype;
		this.subid = subid;
		this.marksObtained = marksObtained;
	}

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getSubmissiontype() {
		return submissiontype;
	}

	public void setSubmissiontype(String submissiontype) {
		this.submissiontype = submissiontype;
	}

	public Integer getSubid() {
		return subid;
	}

	public void setSubid(Integer subid) {
		this.subid = subid;
	}

	public Integer getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(Integer marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
 

	public Integer getAssesmentid() {
		return assesmentid;
	}

	public void setAssesmentid(Integer assesmentid) {
		this.assesmentid = assesmentid;
	}
	
 
}
