package edu.nwmissouri.geoapp.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblPool;
import edu.nwmissouri.geoapp.model.TblPoolquestionoption;
import edu.nwmissouri.geoapp.model.TblQuiz;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.serviceImpl.AsssignmentServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.PoolServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.UserServiceImpl;


@Controller
@RestController
@RequestMapping("Pool")
public class PoolController {
	
	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	private PoolServiceImpl poolserviceimpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private AsssignmentServiceImpl asssignmentServiceImpl;
	
	
	
	// getting Pools to show it in the create Assignment page for instructor 
	
	@RequestMapping(method = RequestMethod.GET, value = "/getPools")
	public @ResponseBody List<TblPool> getPools() {
	
	return poolserviceimpl.getPools();
	
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	
	public ModelAndView showPoolHome(@RequestParam("assignID") Integer assignID,
			@RequestParam("studentID") Integer studentId) {
	
		Map<String,Object> model=new HashMap<>();		
		
		 model.put("studentID", studentId);
		 model.put("assignID", assignID);
		 
		return new ModelAndView("QuizPage", "quizmodel", model);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/checkattempts/{studentID}/{assignID}")
	public @ResponseBody boolean checkAttempts(@PathVariable Integer studentID, @PathVariable Integer assignID) throws ParseException {
	
	
	return poolserviceimpl.checkAttempts(assignID,studentID );
	
	}



	@RequestMapping(method = RequestMethod.GET, value = "/checkattemptsBeforeTakeQuiz/{studentID}/{assignID}")
	public @ResponseBody boolean checkAttemptsBeforeTakeQuiz(@PathVariable Integer studentID, @PathVariable Integer assignID) throws ParseException {
	
	return poolserviceimpl.checkAttemptsBeforeTakeQuiz(assignID,studentID );
	
	}


	@RequestMapping(method = RequestMethod.POST, value = "/saveScore/{score}/{studentID}/{assignID}")	
	public void saveScore(@PathVariable Integer score, @PathVariable Integer studentID, @PathVariable Integer assignID) throws ParseException {	
		
	poolserviceimpl.saveScore(score, studentID, assignID);
	
	}

	
	@RequestMapping(value = "/quizResults", method = RequestMethod.GET)
	public ModelAndView showResultsPage(@RequestParam("questionsInQuiz") Integer questionsInQuiz,
			@RequestParam("scoreInQuiz") Integer scoreInQuiz, @RequestParam("assignID") Integer assignID,
			@RequestParam("studentID") Integer studentId) 
					{
		 
		TblAssignment assignment = service.getAssignment(assignID); 
		TblQuiz tblQuiz = assignment.getTblQuiz();

		 // To find percentage of marks got by a user
		 float q = questionsInQuiz;
		 float s = scoreInQuiz;
		 int minpercent = tblQuiz.getQualpercent(); 
		 int markspercent = (int) ((s/q) *100); 
		 int noofgivenattempts = tblQuiz.getNum_Takes_Max();  		 
		 // finding out whether the user has failed or passed the test
		 String passorfail = "";
		 if ( markspercent >= minpercent) {
			 passorfail = "Congratulations! You have passed the Quiz!";
			 
		 }else 
		 {
			 passorfail = "Sorry, you have failed the Quiz. Better Luck Next Time !";
		 }
				
		  TblUser tbluser = userServiceImpl.findbyuserID(studentId); 
		  
		  
		 
		 Map<String,Object> model=new HashMap<>();	
		 
		 model.put("studentID", studentId);
		 model.put("assignID", assignID);		
		 model.put("questionsInQuiz", questionsInQuiz);
		 model.put("scoreInQuiz", scoreInQuiz);
		 model.put("minpercent", minpercent);
		 model.put("markspercent", markspercent);
		 model.put("passorfail", passorfail);
		 model.put("noofgivenattempts", noofgivenattempts);
		 model.put("User", tbluser);
		 
		 return new ModelAndView("QuizResultsPage","quizresultsmodel",model);
		
		
	}
		

	//@RequestMapping(method = RequestMethod.GET, value = "/getquestions/{poolID}/{quizID}")
	@RequestMapping(method = RequestMethod.GET, value = "/getquestions/{assignID}/{studentID}")
	public @ResponseBody QuestionChoicesWrapper getPoolQuestions(@PathVariable Integer assignID,  @PathVariable Integer studentID) {

		
		
		int quizID =  asssignmentServiceImpl.findAssignmentById(assignID).getTblQuiz().getQuizID();
 
		int poolID; 
		
		TblPool tblPool = poolserviceimpl.findTblPoolByassignID(assignID);
		if(tblPool.getPoolID() == Integer.parseInt(tblPool.getPoolName().split(" ")[1])){
			poolID = tblPool.getPoolID();
		}else{
			poolID = Integer.parseInt(tblPool.getPoolName().split(" ")[1]);
		}
		
		TblQuiz tblQuiz = poolserviceimpl.findTblQuizbyQuizID(quizID);
		
		
		String timer = tblQuiz.getTimer();
		
		List<String> qlist1 = new ArrayList<String>();
		List<String> qlist2 = new ArrayList<String>();
		List<TblPoolquestionoption> choicelist1 = new ArrayList<TblPoolquestionoption>();
		List<String> choicelist2 = new ArrayList<String>();
		List<Integer> options = new ArrayList<Integer>();

		// numQuestions is the number of Questions that should randomly come
		// when a student takes a quiz , given a QuizID
		

		int numQuestionsperQuiz = Integer.parseInt(poolserviceimpl.getCountofQuestions(quizID));

		try {
            
			qlist1 = poolserviceimpl.getPoolQuestions(poolID);
			choicelist1 = poolserviceimpl.getQuestionChoices();

			int k = 0;

			// noofquestions is the total number of questions given a poolID

			int noofquestions = 0;

			noofquestions = qlist1.size();

			// method to randomize the no. of pool questions
			ArrayList<Integer> list = new ArrayList<Integer>();

			for (int i = 0; i < noofquestions; i++) {
				list.add(new Integer(i));
			}
			
			Collections.shuffle(list);

			// Random rg = new Random();

			for (int i = 0; i < numQuestionsperQuiz; i++) { // To generate and
															// send 3 (i<3)
															// Random questions

				// k= rg.nextInt(15);
				k = list.get(i);

				qlist2.add(qlist1.get(k)); // add total no. of question in place
											// of 5

				for (int j = (4 * k); j < 4 * k + 4; j++) {

					choicelist2.add(choicelist1.get(j).getChoice());
					options.add(choicelist1.get(j).getFractionCorrect());
				}

			}

		} catch (Exception e) {

		}

		QuestionChoicesWrapper qcw = new QuestionChoicesWrapper(qlist2, choicelist2, options, timer);

		return qcw;
	}

	public class QuestionChoicesWrapper {

		List<String> questionlist;
		List<String> choicelist;
		List<Integer> options;
		String timer; 

		public QuestionChoicesWrapper() {
			super();
		}

		public QuestionChoicesWrapper(List<String> questionlist, List<String> choicelist, List<Integer> options, String timer) {
			super();
			this.questionlist = questionlist;
			this.choicelist = choicelist;
			this.options = options;
			this.timer = timer;
		}

		public List<String> getQuestionlist() {
			return questionlist;
		}

		public void setQuestionlist(List<String> questionlist) {
			this.questionlist = questionlist;
		}

		public List<String> getChoicelist() {
			return choicelist;
		}

		public void setChoicelist(List<String> choicelist) {
			this.choicelist = choicelist;
		}

		public List<Integer> getOptions() {
			return options;
		}

		public void setOptions(List<Integer> options) {
			this.options = options;
		}

		public String getTimer() {
			return timer;
		}

		public void setTimer(String timer) {
			this.timer = timer;
		}

	}
	
	/*@RequestMapping(value="/showquizresults",method=RequestMethod.GET)
	public String showQuizResults(){
		return "QuizResultsPage";
	}*/
	
//	@RequestMapping(value = "/quizResults", method = RequestMethod.POST)
//	public ModelAndView showResultsPage(@RequestBody String resultJson) throws JsonProcessingException, IOException {
//		 
//		
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode root = mapper.readTree(resultJson);
//		
//		System.out.println("####");
//		System.out.println(root.path("questionsInQuiz").asInt());
//		System.out.println(root.path("scoreInQuiz").asInt());
//		 
//		ModelAndView model = new ModelAndView();
//		
////		 Map<String,Object> model=new HashMap<>();		
////		
////		 model.put("questionsInQuiz", root.path("questionsInQuiz").asInt());
////		 model.put("scoreInQuiz", root.path("scoreInQuiz").asInt());
//		
//		model.addObject("questionsInQuiz", root.path("questionsInQuiz").asInt());
//		model.addObject("scoreInQuiz", root.path("scoreInQuiz").asInt());
//		model.setViewName("QuizResultsPage");
//		 
//		// return "redirect:/showquizresults";
//		return model;
//		
//	}
	
	// uploading a document and parsing the questions and options and answers and saving them to different tables to the database
	
		@RequestMapping(method = RequestMethod.GET, value = "/redirecttoupload")	
		public ModelAndView Redirectoread() {	
		
		return new ModelAndView("CreateQuizPool");
		
	}
		
		@RequestMapping("/uploadandreadtextdoc")	
		public void fileread(MultipartHttpServletRequest request) throws IOException {

			
			MultipartFile fileToSave = request.getFile("documentcontent1");
			byte[] byteFile = fileToSave.getBytes();
			ByteArrayInputStream stream = new   ByteArrayInputStream(byteFile);
			String myString = IOUtils.toString(stream, "UTF-8");		
			String[] arr = myString.split("\n");		
			
			String poolName = request.getParameter("PoolName");
			poolserviceimpl.savePool(arr,poolName);
			
		
		}

}
