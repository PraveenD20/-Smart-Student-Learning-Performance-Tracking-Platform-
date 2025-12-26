package com.alpha.SmartStudentTracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.SmartStudentTracker.dto.LeaderBoardResponse;
import com.alpha.SmartStudentTracker.entity.AssesmentSubmission;
import com.alpha.SmartStudentTracker.entity.Review;
import com.alpha.SmartStudentTracker.entity.TaskSubmission;
import com.alpha.SmartStudentTracker.entity.Users;
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


	public List<LeaderBoardResponse> getLeaderboardForAssignment(
			String submissiontype,Integer subid) {

		// findBySubmissiontypeAndSubid(String submissiontype,Integer subid)

		List<Review> reviews =
				reviewRepository.findBySubmissiontypeAndAssesmentid(
						submissiontype.toUpperCase(), subid);

		List<LeaderBoardResponse> leaderboard = new ArrayList<>();

		for (Review review : reviews) {

			Users student;

			if (submissiontype.equalsIgnoreCase("TASK")) {

				TaskSubmission ts = taskSubRepository
						.findById(review.getSubid())
						.orElseThrow();

				student = ts.getStudent();

			} else{

				AssesmentSubmission as = assesmentSubmissionRepository
						.findById(review.getSubid())
						.orElseThrow();

				student = as.getStudent();
			}

			LeaderBoardResponse res = new LeaderBoardResponse();
			res.setStudentid(student.getId());
			res.setStudentName(student.getName());
			res.setTotalMarks(review.getMarksObtained());   // single value

			leaderboard.add(res);
		}

		// sort by marks DESC
		leaderboard.sort(
				(a, b) -> b.getTotalMarks() - a.getTotalMarks()
				);

		// assign rank
		int rank = 1;
		for (LeaderBoardResponse r : leaderboard) {
			r.setRank(rank++);
		}

		return leaderboard;
	}


}
