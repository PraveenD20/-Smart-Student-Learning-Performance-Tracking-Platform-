package com.alpha.SmartStudentTracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Courses;
import com.alpha.SmartStudentTracker.repository.CoursesRepository;

@Service
public class CoursesService {
	@Autowired
    private  CoursesRepository coursesrepository;
	
	 public ResponseEntity<ResponseStructure<Courses>> saveCourse(@RequestBody Courses course) {
		 Optional<Courses> opt=coursesrepository.findByCoursename(course.getCoursename());
		 
		 ResponseStructure<Courses> rs=new ResponseStructure<Courses>();
		 
		 if(opt.isPresent()) {
			 rs.setStatuscode(HttpStatus.CONFLICT.value());
			 rs.setMessage("Course is already exist");
			 rs.setData(opt.get());
			 return new ResponseEntity<ResponseStructure<Courses>>(rs,HttpStatus.CONFLICT); //209 conflict
		 }
		 Courses saved=coursesrepository.save(course);
		 rs.setStatuscode(HttpStatus.OK.value());
		 rs.setMessage("Course is saved with"+course.getId());
		 rs.setData(saved);
		 return new ResponseEntity<ResponseStructure<Courses>>(rs,HttpStatus.OK); //200 OK
	 }
	 
	 
	 public List<Courses> getAllCourse() {
		List<Courses> coursesList=coursesrepository.findAll();
		return coursesList;
		 
	 }

	 
	 
	 
	 
	 
	 
}


