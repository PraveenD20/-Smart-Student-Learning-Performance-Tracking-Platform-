package com.alpha.SmartStudentTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.SmartStudentTracker.dto.ResponseStructure;
import com.alpha.SmartStudentTracker.entity.Batches;
import com.alpha.SmartStudentTracker.entity.Courses;
import com.alpha.SmartStudentTracker.repository.BatchesRepository;

@Service
public class BatcheService {
	@Autowired
	private BatchesRepository batchesrepository;
	
		public ResponseEntity<ResponseStructure<Batches>> saveBatches(@RequestBody Batches batche) {
			Optional<Batches> opt=batchesrepository.findById(batche.getId());
			ResponseStructure<Batches> rs=new ResponseStructure<Batches>();
			
			 if(opt.isPresent()) {
				 rs.setStatuscode(HttpStatus.CONFLICT.value());
				 rs.setMessage("Batche is already exist");
				 rs.setData(opt.get());
				 return new ResponseEntity<ResponseStructure<Batches>>(rs,HttpStatus.CONFLICT); //209 conflict
			 }
			 Batches saved=batchesrepository.save(batche);
			 rs.setStatuscode(HttpStatus.OK.value());
			 rs.setMessage("Batches is saved with"+batche.getId());
			 rs.setData(saved);
			 return new ResponseEntity<ResponseStructure<Batches>>(rs,HttpStatus.OK); //200 OK
		}
	
	

}
