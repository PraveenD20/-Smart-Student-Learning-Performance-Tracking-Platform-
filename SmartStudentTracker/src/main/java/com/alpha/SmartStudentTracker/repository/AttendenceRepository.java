package com.alpha.SmartStudentTracker.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.SmartStudentTracker.entity.Attendence;
import com.alpha.SmartStudentTracker.entity.Users;


@Repository
public interface AttendenceRepository extends JpaRepository<Attendence, Integer>{
//		Optional<Attendence> findByStudent_Id(Integer studnetid);
	 Optional<Attendence> findByStudentAndDate(Users student, LocalDate date);

}
