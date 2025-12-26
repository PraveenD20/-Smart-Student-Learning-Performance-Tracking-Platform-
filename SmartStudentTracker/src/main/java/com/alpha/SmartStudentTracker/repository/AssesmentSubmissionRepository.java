package com.alpha.SmartStudentTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Assesment; 
import com.alpha.SmartStudentTracker.entity.AssesmentSubmission;
import com.alpha.SmartStudentTracker.entity.Users;

@Repository
public interface AssesmentSubmissionRepository extends JpaRepository<AssesmentSubmission, Integer> {
   
	Optional<AssesmentSubmission> findByStudentAndAssesment(Users student,Assesment assesment);
	
	void deleteByAssesmentId(Integer assesmentid);
}
