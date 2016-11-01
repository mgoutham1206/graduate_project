package edu.nwmissouri.geoapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.manager.EnrollmentCountExceedEmailManager;
import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblPasswordChange;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.service.SubmissionService;
import edu.nwmissouri.geoapp.serviceImpl.AsssignmentServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.PasswordChangeServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.SectionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.StudentServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.SubmissionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("/instructor")
public class InstructorHomeController {
	@Autowired
	private PasswordChangeServiceImpl passwordServ;

	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private SectionServiceImpl sectionServiceImpl;

	@Autowired
	private AsssignmentServiceImpl assignmentServiceImpl;

	@Autowired
	private SubmissionServiceImpl submissionServiceImpl;
	
	@Autowired
	private EnrollmentCountExceedEmailManager enrollCountExEManager;

	@RequestMapping("/phase1")
	public String loadPhaseOne(@RequestParam("submissionID") String submissionID,@RequestParam("assignmentID") String assignmentID,
			 HttpServletRequest httpRequest) {
		System.out.println("Called");

		httpRequest.setAttribute("submissionID", submissionID);
		httpRequest.setAttribute("assignmentID", assignmentID);
//		httpRequest.setAttribute("assignmentID", assignmentID);

		return "Phase1_InstEvaluation";
	}

	@RequestMapping("/studentList")
	public String loadStudentList() {

		return "StudentsListPendingAssigns_InstEvaluation";
	}

	@RequestMapping("/phase2")
	public String loadPhaseTwo(@RequestParam("submissionID") String submissionID,@RequestParam("assignmentID") String assignmentID,
			 HttpServletRequest httpRequest) {
		httpRequest.setAttribute("submissionID", submissionID);
		httpRequest.setAttribute("assignmentID", assignmentID);
//		httpRequest.setAttribute("assignmentID", assignmentID);
		return "Phase2_InstEvaluation";
	}

	@RequestMapping("/phase3")
	public String loadPhaseThree(@RequestParam("submissionID") String submissionID,@RequestParam("assignmentID") String assignmentID,
			 HttpServletRequest httpRequest) {
		httpRequest.setAttribute("submissionID", submissionID);
		httpRequest.setAttribute("assignmentID", assignmentID);
		System.out.println("Called");
		return "Phase3_InstEvaluation";
	}

	// @RequestMapping("/getSections")
	// public String loadAllSections(@RequestParam("sectionID") int sectionID,
	// HttpServletRequest httpRequest) {
	// httpRequest.setAttribute("sectionID", sectionID);
	// return "InstructorEvaluationSetion.jsp";
	//
	// }
	// loading sections in Jsp page
	@RequestMapping("/getSections")
	public ModelAndView getSections() {
		ModelMap secModel = new ModelMap();
		secModel.addAttribute("InstructorEvaluationSetion", sectionServiceImpl.getInstSectionData());
		return new ModelAndView("InstructorEvaluationSetion", secModel);
	}

	// loading assignments by sectionId in Jsp
	@RequestMapping("/viewAssign")
	public ModelAndView viewAssignment(@RequestParam("sectionID") int sectionID) {
		Map<String, Object> assignmodel = new HashMap<>();
		assignmodel.put("sectionID", sectionID);
		List<TblAssignment> tblAssignments = assignmentServiceImpl.getAssigments(sectionID);
		assignmodel.put("assignments", tblAssignments);
		System.out.println("******************Called*************");
		// System.out.println(tblAssignments.size());
		return new ModelAndView("InstAssignmentList", assignmodel);
	}

	// //loading submissions by assignmentID in Jsp
	// @RequestMapping("/viewSubmissionsByAssignId")
	// public ModelAndView
	// viewSubmissionsByAssignId(@RequestParam("assignmentId") int
	// assignmentId){
	// Map<String,Object> submissionmodel = new HashMap<>();
	// submissionmodel.put("assignmentId", assignmentId);
	// List<TblSubmission>
	// tblSubmissions=submissionServiceImpl.findByAssignmentID(assignmentId);
	// submissionmodel.put("submissions", tblSubmissions);
	// System.out.println("******************Called******Submissions*******"+tblSubmissions.get(0).getSubmissionID());
	// //System.out.println(tblSubmissions.size());
	// //System.out.println("SubmissionID for AsignID=1
	// is*********"+tblSubmissions.get(0).getSubmissionID());
	// //System.out.println("SubmissionID for AsignID=1
	// is*********"+tblSubmissions.get(1).getSubmissionID());
	// return new ModelAndView("InstructorSubmissionsList",submissionmodel);
	// }
	//

	// loading submissions by assignmentID in Jsp
	@RequestMapping("/viewSubmissionsByAssignId")
	public ModelAndView viewSubmissionsByAssignId(@RequestParam("assignmentId") int assignmentId) {
		Map<String, Object> submissionmodel = new HashMap<>();
		submissionmodel.put("assignmentId", assignmentId);
		List<TblSubmission> tblSubmissions = submissionServiceImpl.findByAssignmentID(assignmentId);
		submissionmodel.put("submissions", tblSubmissions);

		System.out.println("******************Called******Submissions*******");
		return new ModelAndView("InstructorSubmissionsList", submissionmodel);
	}

	// loading particular student submissions (Hars)by assignmentID in Jsp
	@RequestMapping("/viewPartiStudSubmissionsByAssignId")
	public ModelAndView viewStudSubmissionsByAssignId(@RequestParam("assignmentId") int assignmentId) {
		Map<String, Object> submissionmodel = new HashMap<>();
		submissionmodel.put("assignmentId", assignmentId);
		List<TblSubmission> tblSubmissions = submissionServiceImpl.findByAssignmentID(assignmentId);
		submissionmodel.put("submissions", tblSubmissions);

		System.out.println("******************Called******Submissions*******");
		return new ModelAndView("InstructorPartStudSubmissions", submissionmodel);
	}

	@RequestMapping(value = "/changePassword/{passwordChangeID}", method = RequestMethod.GET)
	public ModelAndView changePasswordPage(@PathVariable("passwordChangeID") String passwordChangeID) {
		int id = Integer.parseInt(passwordChangeID);
		TblPasswordChange tempPassword = passwordServ.findByID(id);
		if (tempPassword != null) {
			if (tempPassword.getIsUsed() == 'N') {
				TblUser tempUser = userService.findUserbyuserId(tempPassword.getUserInfo());
				System.out.println(tempUser.getUserID());
				System.out.println(tempUser.getEmailAddress());
				System.out.println(tempUser.getUserPassword());
				ModelMap model = new ModelMap();
				model.addAttribute("userInfo", tempUser);
				return new ModelAndView("ChangePassword", model);
			} else {
				return new ModelAndView("AlreadyUsed");
			}
		} else {
			return new ModelAndView("AlreadyUsed");
		}
	}

	@RequestMapping(value = "/ds/PasswordUpdate/{newPassword}/{userID}", method = RequestMethod.GET)
	public void updatePassword(@PathVariable("userID") String userID, @PathVariable("newPassword") String newPassword) {
		int userId = Integer.parseInt(userID);
		TblUser user = userService.findUserbyuserId(userId);
		user.setUserPassword(newPassword);
		TblUser tempNewUser = new TblUser();
		try {
			tempNewUser = userService.saveUser(user);
			if (tempNewUser.getUserPassword().compareTo(newPassword) == 0) {
				List<TblPasswordChange> tempPassChangeList = passwordServ.findByUserInfo(userId);
				for (TblPasswordChange tempPassChange : tempPassChangeList) {
					tempPassChange.setIsUsed('Y');
					passwordServ.delete(tempPassChange);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// boolean flag = false;
		// if(tempNewUser.getUserPassword().compareTo(newPassword) == 0){
		// flag = true;
		// }
		// return flag;
	}
	
	@RequestMapping("/getPhases")
	public ModelAndView loadPhaseInfo(@RequestParam("submissionID") String submissionID,@RequestParam("assignmentID") String assignmentID,
			 HttpServletRequest httpRequest) {
		System.out.println("Called");

		httpRequest.setAttribute("submissionID", submissionID);
		httpRequest.setAttribute("assignmentID", assignmentID);
		
		Map<String, Object> submissionmodel = new HashMap<>();
//		submissionmodel.put("assignmentId", assignmentId);
//		List<TblSubmission> tblSubmissions = submissionServiceImpl.findByAssignmentID(assignmentId);
//		submissionmodel.put("submissions", tblSubmissions);

		System.out.println("******************Called******Submissions*******");
		return new ModelAndView("InstructorPhasesInfo", submissionmodel);

		

//		return "InstructorPhasesInfo";
	}
	
		@RequestMapping("/ds/countExceeded")
		
		public void exceedLimitOfSectionEnrollment(@RequestParam ("sectionId") int sectionId){
			
			int sectionLimit = studentServiceImpl.getStudentCountBySection(sectionId);
			TblSection tempSec = sectionServiceImpl.getSectionByID(sectionId);
			TblUser tempUser = userService.getInstructor(sectionId);
			
			if(sectionLimit == tempSec.getExpectedNoofStudents() + 1)
				
			enrollCountExEManager.sendEmail(tempUser.getEmailAddress(), tempSec.getTitle(), tempSec.getExpectedNoofStudents(), sectionLimit);
			
		}
	

}
