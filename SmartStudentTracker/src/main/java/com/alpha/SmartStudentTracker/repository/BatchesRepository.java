package com.alpha.SmartStudentTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Batches;

@Repository
public interface BatchesRepository extends JpaRepository<Batches, Integer> {
	
	Optional<Batches> findByBatchname(String bacthname);

}
