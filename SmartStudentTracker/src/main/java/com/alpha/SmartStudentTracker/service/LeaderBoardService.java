package com.alpha.SmartStudentTracker.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.LeaderBoardResponse;
import com.alpha.SmartStudentTracker.entity.AssesmentSubmission;
import com.alpha.SmartStudentTracker.entity.Review;
import com.alpha.SmartStudentTracker.entity.TaskSubmission;
import com.alpha.SmartStudentTracker.entity.Users;
import com.alpha.SmartStudentTracker.exception.AssesmentNotFoundException;
import com.alpha.SmartStudentTracker.exception.TaskNotFoundException;
import com.alpha.SmartStudentTracker.repository.AssesmentSubmissionRepository;
import com.alpha.SmartStudentTracker.repository.ReviewRepository;
import com.alpha.SmartStudentTracker.repository.TaskSubRepository;

@Service
public class LeaderBoardService {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private AssesmentSubmissionRepository assesmentSubmissionRepository;
	@Autowired
	private TaskSubRepository taskSubRepository;

     //leader board 
	public List<LeaderBoardResponse> getLeaderboardForAssignment(
			String submissiontype,Integer subid) {

		// findBySubmissiontypeAndSubid(String submissiontype,Integer subid)
        // get the list of review cause in this only we have the marks of student we have to get the review based on the assesment and assessment subid 
		List<Review> reviews =
				reviewRepository.findBySubmissiontypeAndAssesmentid(
						submissiontype.toUpperCase(), subid);
          
		List<LeaderBoardResponse> leaderboard = new ArrayList<>();

		for (Review review : reviews) {

			Users student;
            // based on the assesment submission type there we can create the leaderboard
			if (submissiontype.equalsIgnoreCase("TASK")) {

				TaskSubmission ts = taskSubRepository
						.findById(review.getSubid())
						.orElseThrow( ( )-> new TaskNotFoundException("Task Not Found"));

				student = ts.getStudent();

			} else{

				AssesmentSubmission as = assesmentSubmissionRepository
						.findById(review.getSubid())
						.orElseThrow( ( ) -> new AssesmentNotFoundException("Assesment Not Foud"));

				student = as.getStudent();
			}

			LeaderBoardResponse res = new LeaderBoardResponse();
			res.setStudentid(student.getId());
			res.setStudentName(student.getName());
			res.setTotalMarks(review.getMarksObtained());    
// add the student objects in to the list
			leaderboard.add(res);
		}

		//  sorting the students based on the marks they obatained
		leaderboard.sort(
				(a, b) -> b.getTotalMarks() - a.getTotalMarks()
				);
		//Collections.sort(leaderboard,Collections.reverseOrder());
		

		// assign rank from top starting from 1
		
		int rank = 1;
		for (LeaderBoardResponse r : leaderboard) {
			r.setRank(rank++);
		}

		return leaderboard; // return the list leaderborad of type of LeaderBoardResponse dto
	}


}
