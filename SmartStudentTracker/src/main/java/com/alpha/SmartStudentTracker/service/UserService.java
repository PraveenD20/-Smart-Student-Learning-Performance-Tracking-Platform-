package com.alpha.SmartStudentTracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.SmartStudentTracker.controller.TrainerController;
import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.dto.TrainersResponse;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepository;
	// this method to save the user, we can save any user like Admin we can save,Trainer and Student
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

		return new ResponseEntity<>(rs, HttpStatus.OK);//200 OK
	}


	//to return the Trainers presen in the table 
	public List<TrainersResponse> getAllTrainers() {
		List<Users> trainerlist=userrepository.findByRole("TRAINER");
		List<TrainersResponse> alltrainers=new ArrayList<TrainersResponse>();

		for(Users user:trainerlist) {
			TrainersResponse tc= new TrainersResponse();
			tc.setId(user.getId());
			tc.setName(user.getName());
			tc.setEmail(user.getEmail());
			alltrainers.add(tc);

		}
		return alltrainers;

	}
	
	


}
