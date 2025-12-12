package com.alpha.SmartStudentTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	 Optional<Users> findByContact(String contact);

}
