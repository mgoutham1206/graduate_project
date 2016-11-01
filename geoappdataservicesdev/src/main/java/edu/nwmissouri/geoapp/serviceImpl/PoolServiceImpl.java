package edu.nwmissouri.geoapp.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblPool;
import edu.nwmissouri.geoapp.model.TblPoolquestion;
import edu.nwmissouri.geoapp.model.TblPoolquestionoption;
import edu.nwmissouri.geoapp.model.TblQuiz;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblStudentquiz;
import edu.nwmissouri.geoapp.model.TblStudentquiztake;
import edu.nwmissouri.geoapp.repository.AssignmentRepository;
import edu.nwmissouri.geoapp.repository.PoolRepository;
import edu.nwmissouri.geoapp.repository.PoolquestionRepository;
import edu.nwmissouri.geoapp.repository.PoolquestionoptionRepository;
import edu.nwmissouri.geoapp.repository.QuizRepository;
import edu.nwmissouri.geoapp.repository.StudentRepository;
import edu.nwmissouri.geoapp.repository.StudentquizRepository;
import edu.nwmissouri.geoapp.repository.StudentquiztakeRepository;
import edu.nwmissouri.geoapp.service.PoolService;


@Service
public class PoolServiceImpl implements PoolService {
	
	@Autowired
	PoolquestionRepository poolQuestionRepository;	
	
	@Autowired
	PoolquestionoptionRepository poolQuestionOptionRepository;	
	
    @Autowired
	QuizRepository quizRepository; 
    
    @Autowired
	AssignmentRepository assignmentRepository;	
    
    @Autowired
    StudentRepository studentRepository;	
    
    @Autowired
    StudentquizRepository studentquizRepository;	
    
    @Autowired
    StudentquiztakeRepository studentquiztakeRepository;	
    
    @Autowired
    PoolRepository poolRepository;
    
   

	@Override 
	public List<String> getPoolQuestions(int poolID) {
		// TODO Auto-generated method stub
		return poolQuestionRepository.getPoolQuestions(poolID);
	}

	@Override 
	public List<TblPoolquestionoption> getQuestionChoices( ) {
		// TODO Auto-generated method stub
		return poolQuestionOptionRepository.findAll(); 
	}

    @Override 
    public String getCountofQuestions(int quizID) {
    	
    	return quizRepository.getCountofQuestions(quizID);
    }

	@Override
	public void saveScore(int score, int studentID, int assignID) throws ParseException {
		
		TblAssignment tblassignment = assignmentRepository.findTblAssignmentByassignID(assignID); 
		
		TblStudent tblstudent = studentRepository.findTblStudentBystudentID(studentID) ; 
		
		
		int takeNum = (int) studentquiztakeRepository.getCountofNumTakes(studentID, tblassignment.getTblQuiz().getQuizID());
		
		TblStudentquiztake tblstudentquiztake = new TblStudentquiztake( takeNum+1,  score,  tblassignment.getTblQuiz(),  tblstudent);
		
		TblStudentquiz tblStudentquiz = studentquizRepository.findTblStudentquizbystuIDnquizID(studentID, tblassignment.getTblQuiz().getQuizID());
		
		TblStudentquiztake tblstudentquiztake2 = studentquiztakeRepository.save(tblstudentquiztake);
		
		
		
		TblStudentquiztake tblstudentquiztake3 = studentquiztakeRepository.findTblStudentquiztakeBystudentQuizTakeID(tblstudentquiztake2.getStudentQuizTakeID());
		/*System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+tblstudentquiztake3.getUpdatedDate());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+tblstudentquiztake3.getTblQuiz().getQuizID());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@"+tblstudentquiztake3.getPointsCorrect());*/
		
		
				
		if (tblStudentquiz == null) {
			
			// int maxScore = Math.max(score, temp );
			TblStudentquiz tblStudentquiz1 = new TblStudentquiz( 1,  score,  tblassignment.getTblQuiz(),  tblstudent);			
			studentquizRepository.save(tblStudentquiz1);
		}
		else {
			
			int maxScore = Math.max(score, tblStudentquiz.getMaxScore() );
			tblStudentquiz.setMaxScore(maxScore);			
			tblStudentquiz.setNumTakes(tblStudentquiz.getNumTakes()+1);
			
			/*String date1 = new Date().toString() + " " + new Date().getTime();*/
			//Date date1 = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String myDate = fmt.format(new Date());
			//Date myDate1 = fmt.parse(myDate); 
			
			//System.out.println(myDate);
			tblStudentquiz.setLastUpdatedTime(myDate);
			studentquizRepository.save(tblStudentquiz);
		}
		
		
	}

	public boolean checkAttempts(Integer assignID, Integer studentID) throws ParseException {
		
		
		TblAssignment tblassignment = assignmentRepository.findTblAssignmentByassignID(assignID); 
		
		int num_takes_max = quizRepository.findTblQuiztByquizID(tblassignment.getTblQuiz().getQuizID()).getNum_Takes_Max();
		
/*		TblStudentquiztake tblstudentquiztake = studentquiztakeRepository.findTblStudentquiztakeBymaxTakeNum(studentID, tblassignment.getTblQuiz().getQuizID(), num_takes_max);
*/

		TblStudentquiz tblStudentquiz = studentquizRepository.findTblStudentquizbystuIDnquizID(studentID, tblassignment.getTblQuiz().getQuizID());

		int takeCount = tblStudentquiz.getNumTakes();
		
		if (takeCount < num_takes_max){  
			return true;
		}
		else {	
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String myDate = fmt.format(new Date());
			SimpleDateFormat fmt2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			Date currentDate = fmt2.parse(myDate);
						
			String lastUpdatedDate = tblStudentquiz.getLastUpdatedTime();
			Date lastUpdated = fmt2.parse(lastUpdatedDate);
						
			if((currentDate.getTime() - lastUpdated.getTime()) >= 60000){
				
				tblStudentquiz.setNumTakes(0);
				studentquizRepository.save(tblStudentquiz);
				return true;
			}	
			else{
				return false;
			}
		}			
}
		
			
	public TblQuiz findTblQuizbyQuizID (int quizID) {
		
		return quizRepository.findTblQuiztByquizID(quizID);
		
		
		
	}

	
	@Override
	public void savePool(String[] arr, String poolName) {

		TblAssignment tblassignment = assignmentRepository.findTblAssignmentByassignID(400005); 
		TblPool tblPool = new TblPool(poolName,tblassignment );
		poolRepository.save(tblPool);
          int poolID = poolRepository.findPoolID(poolName, 400005);
		for(int i = 0; i < (arr.length/5)*5; i = i+5){
			
			TblPoolquestion tblPoolquestion = new TblPoolquestion(3, arr[i], poolRepository.findTblPooltBypoolID(poolID));
			poolQuestionRepository.save(tblPoolquestion);
			
			for(int j=i+1; j<i+5; j++){
				String answer = arr[j].toLowerCase();
				System.out.println(answer);
				
				if(answer.contains("correct answer")){
					answer = answer.replace("correct answer", "");
					//String choice, int displayOrder, int fractionCorrect,TblPoolquestion tblPoolquestion
					TblPoolquestionoption tblPoolquestionoption = new TblPoolquestionoption(answer,5,1,tblPoolquestion);
					poolQuestionOptionRepository.save(tblPoolquestionoption);
				}else{
					TblPoolquestionoption tblPoolquestionoption = new TblPoolquestionoption(answer,5,0,tblPoolquestion);
					poolQuestionOptionRepository.save(tblPoolquestionoption);
				}
				
				
			}
		}
		
		
		
	}

	public int  findpoolIDByassignID(int assignID) {
		// TODO Auto-generated method stub
		return poolRepository.findpoolIDByassignID(assignID);
	}

	public List<TblPool> getPools() {
		// TODO Auto-generated method stub
		
		return poolRepository.findAll();
	}

	@Override
	public void createPool(String poolName, TblAssignment tblAssignment) {
		// TODO Auto-generated method stub
		
		
		poolRepository.save(new TblPool(poolName,tblAssignment ));
		
		
	}
	
	
	@Override
	public boolean checkAttemptsBeforeTakeQuiz(Integer assignID, Integer studentID) throws ParseException {

		TblAssignment tblassignment = assignmentRepository.findTblAssignmentByassignID(assignID); 
		
		TblStudent tblstudent = studentRepository.findTblStudentBystudentID(studentID) ;
				
		TblStudentquiz tblStudentquiz = studentquizRepository.findTblStudentquizbystuIDnquizID(studentID, tblassignment.getTblQuiz().getQuizID());
		
		int num_takes_max = quizRepository.findTblQuiztByquizID(tblassignment.getTblQuiz().getQuizID()).getNum_Takes_Max(); 
		
		if (tblStudentquiz == null) {
			
			/*TblStudentquiz tblStudentquiz1 = new TblStudentquiz( 1,  score,  tblassignment.getTblQuiz(),  tblstudent);			
			studentquizRepository.save(tblStudentquiz1);*/
			
			return true;
		}
		else {
			
			int takeCount = tblStudentquiz.getNumTakes();
			
			if (takeCount < num_takes_max) {  
				return true;
			}
			else {		
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String myDate = fmt.format(new Date());
					SimpleDateFormat fmt2 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
					Date currentDate = fmt2.parse(myDate);
					
					
					//long currentTime = new Date().getTime();				
					String lastUpdatedDate = tblStudentquiz.getLastUpdatedTime();
					Date lastUpdated = fmt2.parse(lastUpdatedDate);
					/*System.out.println("%%%%%%%%%");
					System.out.println(currentDate.getTime() + " " + lastUpdated.getTime());
					System.out.println("#############");					
					System.out.println(currentDate.getTime() - lastUpdated.getTime());*/
					
					
					if((currentDate.getTime() - lastUpdated.getTime()) >= 30000){
						
						tblStudentquiz.setNumTakes(0);
						studentquizRepository.save(tblStudentquiz);
						return true;
					}	
					else{
						return false;
					}
			}	
		}
		
	}

	@Override
	public TblPool findTblPoolByassignID(int assignID) {
		// TODO Auto-generated method stub
		return poolRepository.findTblPoolByassignID(assignID);
	}

	@Override
	public void saveQuiz(TblQuiz tblQuiz) {
		// TODO Auto-generated method stub
		quizRepository.save(tblQuiz);
		
	}

	

	
}
