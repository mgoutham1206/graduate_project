package edu.nwmissouri.geoapp.service;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblPool;
import edu.nwmissouri.geoapp.model.TblPoolquestion;
import edu.nwmissouri.geoapp.model.TblPoolquestionoption;
import edu.nwmissouri.geoapp.model.TblQuiz;

public interface PoolService {
	
	public List<String> getPoolQuestions(int poolID);
	
	public List<TblPoolquestionoption> getQuestionChoices();
	
	public String getCountofQuestions(int quizID);
	
	public void saveScore(int score, int studentID, int assignID)  throws ParseException ;
	
	public boolean checkAttempts(Integer assignID, Integer studentID) throws ParseException ;
	
	public void savePool(String[] arr, String poolName);
	
	public int findpoolIDByassignID(int assignID);
	
	public List<TblPool> getPools();
	
	public void createPool(String poolName, TblAssignment tblAssignment);

	public boolean checkAttemptsBeforeTakeQuiz(Integer assignID, Integer studentID) throws ParseException;
	
	public TblPool findTblPoolByassignID(int assignID);

	public void saveQuiz(TblQuiz tblQuiz);
 
 	

}
