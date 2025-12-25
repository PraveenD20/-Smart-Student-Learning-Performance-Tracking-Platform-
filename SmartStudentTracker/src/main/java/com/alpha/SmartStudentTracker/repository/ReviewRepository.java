package com.alpha.SmartStudentTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	Optional<Review> findBySubmissiontypeAndSubid(String submissiontype,Integer subid);

}
