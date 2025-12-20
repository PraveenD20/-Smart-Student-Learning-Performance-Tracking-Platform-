package com.alpha.SmartStudentTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Assesment;
import com.alpha.SmartStudentTracker.entity.AssesmentResult;
import com.alpha.SmartStudentTracker.entity.Users;

@Repository
public interface AssesmentResultRepository extends JpaRepository<AssesmentResult, Integer> {
   
	Optional<AssesmentResult> findByStudentAndAssesment(Users student,Assesment assesment);
}
