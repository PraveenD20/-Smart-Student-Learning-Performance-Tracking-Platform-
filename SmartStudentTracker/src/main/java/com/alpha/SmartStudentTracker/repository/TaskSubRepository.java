package com.alpha.SmartStudentTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Task;
import com.alpha.SmartStudentTracker.entity.TaskSubmission;
import com.alpha.SmartStudentTracker.entity.Users;

@Repository
public interface TaskSubRepository extends JpaRepository<TaskSubmission, Integer>{
	Optional<TaskSubmission> findByTaskAndStudent(Task taskid,Users studentid);
	

}
