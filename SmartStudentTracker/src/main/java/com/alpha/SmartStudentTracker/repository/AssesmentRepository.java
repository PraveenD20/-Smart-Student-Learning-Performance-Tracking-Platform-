package com.alpha.SmartStudentTracker.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Assesment;
import com.alpha.SmartStudentTracker.entity.Batches;
import com.alpha.SmartStudentTracker.entity.Subject;
import com.alpha.SmartStudentTracker.entity.Users;
@Repository
public interface AssesmentRepository  extends JpaRepository<Assesment, Integer>{
	
	Optional<Assesment> findByBatchAndSubjectAndDate(Batches batch, Subject subject, LocalDate date);
   Optional<Assesment> findByTrainerAndSubjectAndDate(Users trainer,Subject subject,LocalDate date);

}
