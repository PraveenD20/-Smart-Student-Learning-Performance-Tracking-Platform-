package com.alpha.SmartStudentTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
	Optional<Courses> findByCoursename(String coursename);
   

}
