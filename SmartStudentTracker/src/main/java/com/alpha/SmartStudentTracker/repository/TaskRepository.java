package com.alpha.SmartStudentTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Batches;
import com.alpha.SmartStudentTracker.entity.Task;
import com.alpha.SmartStudentTracker.entity.Users;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	Optional<Task> findByBatchAndTrainer(Batches batch,Users trainer);

}
