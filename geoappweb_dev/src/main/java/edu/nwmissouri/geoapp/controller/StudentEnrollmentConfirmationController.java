package edu.nwmissouri.geoapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.controller.form.StudentEmailData;
import edu.nwmissouri.geoapp.manager.SendActivationMail;
import edu.nwmissouri.geoapp.manager.StudentEmailManager;
import edu.nwmissouri.geoapp.model.TblPasswordChange;
import edu.nwmissouri.geoapp.model.TblRoletype;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.model.TblUserrole;
import edu.nwmissouri.geoapp.model.TblUserrolePK;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.serviceImpl.StudentEnrollmentConfirmationServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.StudentEnrollmentServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.StudentTblServiceImpl;

@RestController
@RequestMapping("/student/enrollment")
public class StudentEnrollmentConfirmationController {

	@Autowired
	private StudentEnrollmentConfirmationServiceImpl sc;

	@Autowired
	private StudentEnrollmentServiceImpl sectionEnroll;

	@Autowired
	private StudentEmailManager studentEmailManager;

	@Autowired
	private UserRepository userRepo;

	@RequestMapping(method = RequestMethod.POST, value = "/ds/submit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String save(@RequestBody TblUser tuser) {
		String checkcond = "";
		try {

			// System.out.println("iyfasdfasd");
			String loginName = tuser.getLoginName().split(",")[0];
			String sectionID = tuser.getLoginName().split(",")[1];
			
			String emailID = tuser.getEmailAddress();

			// System.out.println("fasdfasdf"+loginName+""+sectionID);

			if (sc.isValidUserLoginName(loginName,emailID) == "LET") {
				System.out.println("new Loginname");

				tuser.setLoginName(loginName);

				System.out.println("inserting data to user and user role tables");
				TblRoletype roleType = userRepo.findByUserType(1);

				List<TblRoletype> roleList = new ArrayList<>();
				roleList.add(roleType);

				tuser.setTblroles(roleList);

				TblUser u = sc.save(tuser);
				System.out.println("User:" + u.getUserID());

				TblSection tblSection = sectionEnroll.findOne(Integer.parseInt(sectionID));
				System.out.println("Section:" + tblSection.getSectionID());

				System.out.println("Testing to send Email");
				StudentEmailData obj2 = new StudentEmailData();
				obj2.setToEmailId(tuser.getEmailAddress());
				System.out.println("Testing to send Email" + tuser.getEmailAddress());
				// String toEmailId = u.getEmailAddress();
				obj2.setName(u.getName());

				// Student Table
				TblStudent tblStudentObj = new TblStudent();

				// Integer uid =
				// sc.findTblUserByloginName(loginName).getUserID();
				System.out.println("Setting Values");
				System.out.println(u.getUserID());
				System.out.println(tblSection.getSectionID());
				// tblStudentObj.setTblUser(u);
				tblStudentObj.setStudentID(u.getUserID());
				tblStudentObj.setIsActive("N");
				tblStudentObj.setTblSection(tblSection);

				System.out.println("Just about to start data into student table");
				System.out.println(tblStudentObj);
				System.out.println(tblStudentObj.getStudentID());
				TblStudent tempStudentDetails = sc.save(tblStudentObj);

				System.out.println("Sending email...");
				SendActivationMail r = new SendActivationMail(tuser.getEmailAddress(), tuser.getLoginName(),
						tempStudentDetails.getStudentID());
				Thread t = new Thread(r);
				t.start();

				/*
				 * studentEmailManager.sendEmail(tempStudentDetails.getStudentID
				 * (), tuser.getEmailAddress(), tuser.getLoginName());
				 */

				checkcond = "LET";

			} else if(sc.isValidUserLoginName(loginName,emailID) == "LEF"){
				checkcond = "LEF";
			}else if(sc.isValidUserLoginName(loginName,emailID) == "LF"){
				checkcond = "LF";
			}else{
				checkcond = "EF";
			}


		} catch (Exception e) {

			// return msg;
		}
		
         return checkcond;
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/activatestudent")
	// public ModelAndView save() {
	//
	// return new ModelAndView("activatethankyou");
	// if() {
	// return new ModelAndView("AlreadyUsed");
	// }
	// }

	@RequestMapping(method = RequestMethod.GET, value = "/activatestudent/{studentID}")
	public ModelAndView save(@PathVariable("studentID") String studentID) {
		TblStudent tempStudentInfo = sc.findOne(Integer.parseInt(studentID));
		System.out.println("The result of the activation is = " + tempStudentInfo.getIsActive().charAt(0));
		if (tempStudentInfo.getIsActive().charAt(0) == 'N') {
			ModelMap secModel1 = new ModelMap();
			// tempStudentInfo.setIsActive("Y");
			secModel1.addAttribute("studentInfo", userRepo.findOne(Integer.parseInt(studentID)));
			// tempStudentInfo.setIsActive("Y");
			return new ModelAndView("activatethankyou", secModel1);
		} else {
			return new ModelAndView("AlreadyUsed");
		}
	}

	@RequestMapping(value = "/ds/activateStudent/{userID}", method = RequestMethod.GET)
	public @ResponseBody boolean studentActivation(@PathVariable("userID") String userID) {
		int userId = Integer.parseInt(userID);
		TblStudent tempStudentInfo = sc.findOne(userId);
		tempStudentInfo.setIsActive("Y");
		TblStudent temp2 = new TblStudent();
		try {
			temp2 = sc.save(tempStudentInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (temp2.getIsActive() == "Y")
			return true;
		return false;
	}

	@RequestMapping(value = "/confirm")
	public ModelAndView confirm() {
		ModelMap secModel1 = new ModelMap();
		// secModel1.addAttribute("StudentEnroll", enrollment.findAll());
		// return enrollment.findAll() ;
		return new ModelAndView("StudentEnrollConfirm", secModel1);
	}

	@RequestMapping(value = "/thankyou")
	public ModelAndView thankyou() {
		// ModelMap secModel1 = new ModelMap();
		// secModel1.addAttribute("StudentEnroll", enrollment.findAll());

		// return enrollment.findAll() ;
		return new ModelAndView("thankyou");
	}

	// @RequestMapping(value="/activatethankyou")
	// public ModelAndView activatethankyou(){
	//
	//
	// //ModelMap secModel1 = new ModelMap();
	// //secModel1.addAttribute("StudentEnroll", enrollment.findAll());
	//
	// //return enrollment.findAll() ;
	// return new ModelAndView("activatethankyou" );
	// }

	@RequestMapping(method = RequestMethod.POST, value = "/student/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void save(@PathVariable Integer id, @RequestBody TblStudent tbStudentObj) {
		try {

			// tbStudentObj.setStudentID(id);
			System.out.println(id + "" + tbStudentObj.getStudentID());
			//
			// String loginName = tuser.getLoginName().split(",")[0];
			// String sectionID = tuser.getLoginName().split(",")[1];
			//
			System.out.println("fasdfasdf");
			// tuser.setLoginName(loginName);
			TblSection tblSection = sectionEnroll.findOne(id);
			System.out.println(tblSection.getTitle());

			TblStudent tblStudentObj = new TblStudent();
			// TblSection sec = new TblSection();

			// tblSection.getSectionID();

			tblStudentObj.setTblSection(tblSection);

			TblUser user = new TblUser();

			user.setTblStudent(tblStudentObj);

			/*
			 * tblStudentObj.setStudentID(100003);
			 * tblStudentObj.setTblSection(tblSection);
			 */
			// tblSection.setSectionID(Integer.parseInt(sectionID));
			// tblStudentObj.setTblSection(tblSection);

			sc.save(user);
			// sc.save(tblStudentObj);
			// studentServiceImpl.saveStudent(tbStudentObj);

		} catch (Exception e) {

			// return null;
		}
	}
}