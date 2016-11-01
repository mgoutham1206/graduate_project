package edu.nwmissouri.geoapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.controller.form.Mineral;
import edu.nwmissouri.geoapp.controller.form.PhaseOneForm;
import edu.nwmissouri.geoapp.controller.form.PhaseThreeForm;
import edu.nwmissouri.geoapp.controller.form.PhaseTwoForm;
import edu.nwmissouri.geoapp.generalinfo.repository.MineralRepository;
import edu.nwmissouri.geoapp.manager.PhaseOneManager;
import edu.nwmissouri.geoapp.manager.PhaseThreeManager;
import edu.nwmissouri.geoapp.manager.PhaseTwoManager;
import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblStudentquiz;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.StudentquizRepository;
import edu.nwmissouri.geoapp.service.AssignmentService;
import edu.nwmissouri.geoapp.service.FeedbackService;
import edu.nwmissouri.geoapp.service.GeometryTypeService;
import edu.nwmissouri.geoapp.service.HardnessTypeService;
import edu.nwmissouri.geoapp.service.LusterService;
import edu.nwmissouri.geoapp.service.RockTypeService;
import edu.nwmissouri.geoapp.service.SpecificgravityTypeService;
import edu.nwmissouri.geoapp.service.StreakColorTypeService;
import edu.nwmissouri.geoapp.service.StudentService;
//import scala.annotation.meta.setter;

@Controller
@RequestMapping(value="student",method=RequestMethod.GET)
@SessionAttributes({"phaseTwoForm","studentID"})
@Transactional
public class StudentAssignmentController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	AssignmentService assignmentService;
	
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	PhaseOneManager phaseOneManager;
	
	@Autowired
	PhaseThreeManager phaseThreeManager;
	
	@Autowired
	PhaseTwoManager phaseTwoManager;
	
	@Autowired
	private LusterService lusterService;
	
	@Autowired
	private GeometryTypeService geometryTypeService;
	
	@Autowired
	private HardnessTypeService hardnessTypeService;
	
	@Autowired
	private StreakColorTypeService streakColorTypeService;
	
	@Autowired
	private MineralRepository mineralRepository;
	
	@Autowired
	private SpecificgravityTypeService specificgravityTypeService;
	
	@Autowired
	private RockTypeService rockTypeService;
	
	@Autowired
	ServletContext servletContext;
	
	 @Autowired
	    StudentquizRepository studentquizRepository;	
	
	@RequestMapping(value="/studentHome",method=RequestMethod.GET)
		public ModelAndView studentHome(Model model, HttpServletRequest request){
		TblUser userdetailsinfo= (TblUser)request.getSession().getAttribute("userdetailsinfo");
		model.addAttribute("studentID", userdetailsinfo.getUserID());
			return new ModelAndView("studentHome");
		}
	
	 @RequestMapping(value = "/viewassignment",method=RequestMethod.GET)
	 public ModelAndView viewAssignment(@RequestParam("studentID") Integer studentId){
		 Map<String,Object> model = new HashMap<>();
		 model.put("studentID", studentId);
		 List<TblAssignment> tblAssignments=studentService.getTblAssignments(studentId);
		 model.put("assignments", tblAssignments);
		 
		return new ModelAndView("viewAssignment","model",model);
	 }
	 
	 @RequestMapping(value = "/openassignment",method=RequestMethod.GET)
	 public ModelAndView openAssignment(@RequestParam("assignID") Integer assignID,@ModelAttribute("studentID") Integer studentId){
		 Map<String,Object> model = new HashMap<>();
		 model.put("studentID", studentId);
		 TblAssignment tblAssignment=assignmentService.findAssignmentById(assignID);
		 model.put("assignment", tblAssignment);
		 
		 // Adding the Quiz details after Take Quiz -- Representing only the maximum score in a quiz -- Krishna Reddy
		TblStudentquiz tblStudentquiz = studentquizRepository.findTblStudentquizbystuIDnquizID(studentId, tblAssignment.getTblQuiz().getQuizID());
         		 
		 model.put("MaxScore", tblStudentquiz.getMaxScore() );
		 //Changes ended
		 
		return new ModelAndView("openAssignment","assignmentmodel",model);
	 }
	 
	 @RequestMapping(value = "/viewprogress",method=RequestMethod.GET)
	 public ModelAndView viewProgress(@RequestParam("assignID") Integer assignID,@ModelAttribute("studentID") Integer studentId){
		 Map<String,Object> model = new HashMap<>();
		 model.put("studentID", studentId);
		 TblSubmission tbs=studentService.getSubmission(assignID,studentId);
		 if(tbs==null){ 
			 tbs=new TblSubmission();
			 TblAssignment tblAssignment=new TblAssignment();
			 tblAssignment.setAssignID(assignID);
			 tbs.setTblAssignment(tblAssignment);
		 }
		 model.put("submission", tbs);	
		 model.put("assignID", assignID);
		 model.put("assignmentName",tbs.getTblAssignment().getName());
		 if(tbs.getTblPhaseevaluations()!=null){
		 for (TblPhaseevaluation tblPhaseevaluation : tbs.getTblPhaseevaluations()) {
		 //String flag=tblPhaseevaluation.getIsAccepted();
		 //model.put(""+tblPhaseevaluation.getTblPhasetype().getPhaseID(),tblPhaseevaluation);
		 if(tblPhaseevaluation.getTblPhasetype().getPhaseID() == 1){
			 model.put("Phase1", tblPhaseevaluation.getIsAccepted());
			 model.put("phase1Points", tblPhaseevaluation.getPoints());
		 }
		 else if(tblPhaseevaluation.getTblPhasetype().getPhaseID() == 2){
			 model.put("Phase2", tblPhaseevaluation.getIsAccepted());
			 model.put("phase2Points", tblPhaseevaluation.getPoints());
		 }
		 else if(tblPhaseevaluation.getTblPhasetype().getPhaseID() == 3){
			 model.put("Phase3", tblPhaseevaluation.getIsAccepted());
			 model.put("phase3Points", tblPhaseevaluation.getPoints());
		 }
		 }
		 }
		return new ModelAndView("viewProgress","progressmodel",model);
	 }
	 
	 @RequestMapping(value="/phaseoneFeedback",method=RequestMethod.GET)
		public ModelAndView viewPhaseOneFeedback(HttpServletRequest request,@RequestParam("assignID") Integer assignID,@ModelAttribute("studentID") Integer studentId) throws IOException{
		 String imagePath=servletContext.getRealPath("/static");
			PhaseOneForm phaseOneForm = phaseOneManager.getSubmission(assignID, studentId,imagePath);
			return new ModelAndView("phaseoneFeedback","phaseOneForm", phaseOneForm );
		}
	 
	 @RequestMapping(value="/phaseone",method=RequestMethod.GET)
		public ModelAndView viewPhaseOne(HttpServletRequest request,@RequestParam("assignID") Integer assignID,@ModelAttribute("studentID") Integer studentId) throws IOException{
			String imagePath=servletContext.getRealPath("/static");
			PhaseOneForm phaseOneForm = phaseOneManager.getSubmission(assignID, studentId,imagePath);
			return new ModelAndView("phaseone","phaseOneForm", phaseOneForm );
		}
		
		@RequestMapping(value="/phaseone/deleteimage",method=RequestMethod.POST)
		public void deleteImage(@RequestParam("imageID") Integer imageID){
			phaseOneManager.deleteImage(imageID);
		}
	
	 @RequestMapping(value = "/phaseone/submit", method = RequestMethod.POST)
	    public ModelAndView savePhase1(
	            @ModelAttribute("phaseOneForm") PhaseOneForm phaseOneForm,
	                    Model map) {
	     phaseOneManager.saveSubmission(phaseOneForm);
	     Map<String,Object> model=new HashMap<>();
	     model.put("studentID", phaseOneForm.getUserId());
	     model.put("assignID", phaseOneForm.getAssignID());
	     return new ModelAndView("instfeed","model",model);
	    }
	 
	 
	  @RequestMapping(value = "/phasethree",method=RequestMethod.GET)
	 public ModelAndView viewPhaseThree(@RequestParam("assignID") Integer assignID,@ModelAttribute("studentID") Integer studentId,Model model){
		 PhaseThreeForm phaseThreeForm = phaseThreeManager.getSubmission(assignID, studentId);
		 model.addAttribute("phaseThreeForm",phaseThreeForm);
		 model.addAttribute("rockTypes", rockTypeService.findAll());
		 //model.addAttribute("studentID", studentId);
		 model.addAttribute("assignID", assignID);
		 TblSubmission tbs=studentService.getSubmission(assignID, studentId);
		 String comment = null;
		 for(TblPhaseevaluation tblPhaseevaluation: tbs.getTblPhaseevaluations()){
			 if(tblPhaseevaluation.getTblPhasetype().getPhaseID()==3)
			 {
				 comment=tblPhaseevaluation.getComments();
			 }
		 }
		 model.addAttribute("comment",comment);
		 return new ModelAndView("phaseThree");
	 }
	  
	  @RequestMapping(value = "/phasethreefeedback",method=RequestMethod.GET)
		 public ModelAndView viewPhaseThreeFeedback(@RequestParam("assignID") Integer assignID,@ModelAttribute("studentID") Integer studentId,Model model){
			 PhaseThreeForm phaseThreeForm = phaseThreeManager.getSubmission(assignID, studentId);
			 model.addAttribute("phaseThreeForm",phaseThreeForm);
			 //model.addAttribute("studentID", studentId);
			 model.addAttribute("assignID", assignID);
			 TblSubmission tbs=studentService.getSubmission(assignID, studentId);
			 String comment = null;
			 for(TblPhaseevaluation tblPhaseevaluation: tbs.getTblPhaseevaluations()){
				 if(tblPhaseevaluation.getTblPhasetype().getPhaseID()==3)
				 {
					 comment=tblPhaseevaluation.getComments();
				 }
			 }
			 model.addAttribute("comment",comment);
			 return new ModelAndView("phasethreeFeedback");
		 }
	 
	 @RequestMapping(value = "/phasethree/submit", method = RequestMethod.POST)
	    public ModelAndView savePhaseThree(
	            @ModelAttribute("phaseThreeForm") PhaseThreeForm phaseThreeForm,
	                    Model map) {
	     phaseThreeManager.saveRockSubmission(phaseThreeForm);
	     Map<String,Object> model=new HashMap<>();
	     model.put("studentID", phaseThreeForm.getStudentID());
	     model.put("assignID", phaseThreeForm.getAssignID());
	     return new ModelAndView("instfeed","model",model);
	    }
	 
	 @RequestMapping(value = "/phasetwo",method=RequestMethod.GET)
	 public String viewPhaseTwo(@RequestParam("assignID") Integer assignID,@RequestParam("studentID") Integer studentId,@RequestParam(required=false) Integer mineralID,@RequestParam(required=false) String action,Model model){
		 
		 model.addAttribute("luster", lusterService.findAllLusterType());
		 model.addAttribute("hardness", hardnessTypeService.findAll());
		 model.addAttribute("cleavage",geometryTypeService.findAll());
		 model.addAttribute("streakcolor", streakColorTypeService.findAll());
		 model.addAttribute("specificgravity",specificgravityTypeService.findAll());
		 Mineral min=new Mineral();
		 
		 
		 PhaseTwoForm phaseTwoForm = phaseTwoManager.getSubmission(assignID, studentId);
		 model.addAttribute("phaseTwoForm",phaseTwoForm);
		 model.addAttribute("mineral",min);
		 return "phaseTwo";
	 }
	 
	 @RequestMapping(value = "/phasetwofeedback",method=RequestMethod.GET)
	 public String viewPhaseTwoFeedback(@RequestParam("assignID") Integer assignID,@ModelAttribute("studentID") Integer studentId,@RequestParam(required=false) Integer mineralID,@RequestParam(required=false) String action,Model model){
		 
		 model.addAttribute("studentID",studentId);
		 Mineral min=new Mineral();
		 PhaseTwoForm phaseTwoForm = phaseTwoManager.getSubmission(assignID, studentId);
		 model.addAttribute("phaseTwoForm",phaseTwoForm);
		 model.addAttribute("mineral",min);
		 return "phasetwoFeedback";
	 }

	 @RequestMapping(value = "/phasetwofeedbackview",method=RequestMethod.GET)
	 public String phaseTwoFeedbackView(@RequestParam("tempMinId") Integer tempMinId, @RequestParam("action") String action,@ModelAttribute("phaseTwoForm")PhaseTwoForm phaseTwoForm,Model model){

		 Mineral min=new Mineral();
		 min=phaseTwoManager.getMineral(phaseTwoForm,tempMinId);
		 min.setSubmitId(phaseTwoForm.getSubmitId());
		 
		 model.addAttribute("mineral",min);
		 return "phasetwoFeedback";
	 }
	 
	 @RequestMapping(value = "/phasetwoUpdate",method=RequestMethod.GET)
	 public String updatePhaseTwo(@RequestParam("tempMinId") Integer tempMinId, @RequestParam("action") String action,@ModelAttribute("phaseTwoForm")PhaseTwoForm phaseTwoForm,Model model){
		 
		 model.addAttribute("luster", lusterService.findAllLusterType());
		 model.addAttribute("hardness", hardnessTypeService.findAll());
		 model.addAttribute("cleavage",geometryTypeService.findAll());
		 model.addAttribute("streakcolor", streakColorTypeService.findAll());
		 model.addAttribute("specificgravity",specificgravityTypeService.findAll());
		 
		 //model.addAttribute("studentID", studentId);
		
		 
		 Mineral min=new Mineral();
		 
		 
			 if(action!=null && action.equals("delete"))
			 {
				 phaseTwoManager.deleteMineral(phaseTwoForm,tempMinId);
			 }	else {
				 min=phaseTwoManager.getMineral(phaseTwoForm,tempMinId);
			 }
		 min.setSubmitId(phaseTwoForm.getSubmitId());
		 
		 model.addAttribute("mineral",min);
		 return "phaseTwo";
	 }

	 @RequestMapping(value = "/phasetwo/savemineral", method = RequestMethod.POST)
	    public String saveMineral(
	            @ModelAttribute("mineral") Mineral mineral,@ModelAttribute("phaseTwoForm")PhaseTwoForm phaseTwoForm,
	                    Model model) {
		 if(phaseTwoForm.getMinerals() == null){
			phaseTwoForm.setMinerals(new ArrayList<>()); 
		 }
		 
		 if(mineral.getTempMinId()!=null){
			 phaseTwoManager.deleteMineral(phaseTwoForm, mineral.getTempMinId());
		 } 
		 phaseTwoForm.getMinerals().add(mineral);
		 mineral=new Mineral();
		 
		 model.addAttribute("luster", lusterService.findAllLusterType());
		 model.addAttribute("hardness", hardnessTypeService.findAll());
		 model.addAttribute("cleavage",geometryTypeService.findAll());
		 model.addAttribute("streakcolor", streakColorTypeService.findAll());
		 model.addAttribute("specificgravity",specificgravityTypeService.findAll());
		 
		 model.addAttribute("assignID", phaseTwoForm.getAssignID());
		 return "phaseTwo";

	    }
	 
	 @RequestMapping(value = "/phasetwo/submit", method = RequestMethod.POST)
	    public ModelAndView savePhaseTwo(
	            @ModelAttribute("phaseTwoForm")PhaseTwoForm phaseTwoForm,@ModelAttribute("studentID")Integer studentID,
	                    Model map) {
		 
		 
		 phaseTwoManager.saveMinerals(phaseTwoForm);
	     Map<String,Object> model=new HashMap<>();
	     model.put("assignID", phaseTwoForm.getAssignID());
	     model.put("studentID",studentID);
	     phaseTwoForm=new PhaseTwoForm();
	     return new ModelAndView("instfeed","model",model);
	    }
	 

	 
}
