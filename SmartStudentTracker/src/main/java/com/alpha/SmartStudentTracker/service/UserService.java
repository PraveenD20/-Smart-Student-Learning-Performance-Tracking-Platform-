package com.alpha.SmartStudentTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
 
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepository;
	
	
	public ResponseEntity<ResponseStructure<Users>> saveUsers(Users user) {

	    ResponseStructure<Users> rs = new ResponseStructure<>();

	    // Check duplicate contact
	    Optional<Users> opt = userrepository.findByContact(user.getContact());
	    
	    

	    if (opt.isPresent()) {
	        rs.setStatuscode(HttpStatus.CONFLICT.value());
	        rs.setMessage("User already registered with contact: " + user.getContact());
	        rs.setData(opt.get());
	        return new ResponseEntity<>(rs, HttpStatus.CONFLICT); // 409 Conflict
	    }

	    // Save user
	    Users saved = userrepository.save(user);

	    rs.setStatuscode(HttpStatus.OK.value());
	    rs.setMessage("User saved successfully with ID: " + saved.getId());
	    rs.setData(saved);

	    return new ResponseEntity<>(rs, HttpStatus.OK);
	}


//	public ResponseEntity<ResponseStructure<Users>> saveUsers(Users user) {
//	
//		ResponseStructure<Users> rs = new ResponseStructure<Users>();
//		
//		Optional<Users> opt=userrepository.findByContact(user.getContact());
//		
//		if(opt.isPresent()) {
//			rs.setStatuscode(HttpStatus.CONFLICT.value());
//	        rs.setMessage("User already registered with ");
//	        rs.setData(null);
//	        return new ResponseEntity<ResponseStructure<Users>>(rs,HttpStatus.BAD_REQUEST);
//		}
//
//		else {
//			Users saved = userrepository.save(user);
//
//		    rs.setStatuscode(HttpStatus.OK.value());
//		    rs.setMessage("User saved successfully"+user.getId()+" : "+user.getRole());
//		    rs.setData(saved);
//			
//			
//			return new ResponseEntity<ResponseStructure<Users>>(rs,HttpStatus.OK);
//		}
//		
//	}

}
