package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblQuiz;

public interface QuizRepository extends JpaRepository<TblQuiz, Integer> {
	

	@Query("select numQuestions from TblQuiz i where i.quizID = ?1")
    public String getCountofQuestions(int quizID);
	
	public TblQuiz findTblQuiztByquizID(int quizID);
	
	
	
}
