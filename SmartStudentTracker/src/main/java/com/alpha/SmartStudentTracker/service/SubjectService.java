package com.alpha.SmartStudentTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Courses;
import com.alpha.SmartStudentTracker.entity.Subject;
import com.alpha.SmartStudentTracker.exception.CourseNotFoundException;
import com.alpha.SmartStudentTracker.repository.CoursesRepository;
import com.alpha.SmartStudentTracker.repository.SubjectRepository;

@Service
public class SubjectService {
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private CoursesRepository coursesRepository;
	
	//save the subject
	public ResponseEntity<ResponseStructure<Subject>> saveSubject(Subject subject) {
		ResponseStructure<Subject> rs=new ResponseStructure<Subject>();
		
		Integer course_id=subject.getCourse().getId();
		
		Courses course=coursesRepository.findById(course_id)
				.orElseThrow( () -> new CourseNotFoundException("Course Not Found")) ;
		
			subject.setCourse(course);
			
		Subject sub=subjectRepository.save(subject);
		rs.setStatuscode(HttpStatus.OK.value());
		rs.setMessage("Subject Saved");
		rs.setData(sub);
		return new ResponseEntity<ResponseStructure<Subject>>(rs,HttpStatus.OK);
		
	}
	

}
