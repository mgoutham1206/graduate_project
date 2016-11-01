package edu.nwmissouri.geoapp.controller;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.controller.form.EmailData;
import edu.nwmissouri.geoapp.model.TblPasswordChange;
import edu.nwmissouri.geoapp.model.TblRoletype;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.serviceImpl.ImageSubmissionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.PasswordChangeServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.UserServiceImpl;
import edu.nwmissouri.geoapp.manager.AdminEmailManager;;

@Controller
@RequestMapping("/geoapp/admin")
public class AdminHomeController {

	@Autowired
	private UserServiceImpl userserv;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AdminEmailManager adminEmailManager;
	
	@Autowired
	private PasswordChangeServiceImpl passChangeServ;

	// This is just for testing.
	@RequestMapping("/sendemail")
	public void sendEmail(){
		System.out.println("in send email controller");
		String toEmailId = "s522585@mail.nwmissouri.edu";
		
//		//send email by calling this method
		adminEmailManager.sendEmail(toEmailId,1 , "testPassword");
	}
	
	@RequestMapping(value = "/ds/validateEmail/{emailId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean validateEmail(@PathVariable("emailId") String emailId){
		String[] tempName= emailId.split("@");
		return userserv.isValidUserLoginNameAndEmail(tempName[0], emailId);
	}
	
	
	
	@RequestMapping(value = "/createInstructor")
	public ModelAndView getAdminToInstructorCreationPage() {
		return new ModelAndView("CreateInstructor");
	}
	
	@RequestMapping(value = "/welcomeAdmin")
	public ModelAndView getAdminToInstructorHomePage() {
		return new ModelAndView("AdminHomeWelcome");
	}

	@RequestMapping(value = "/ds/userInfoByLoginName/{loginName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TblUser getUserInfoByLoginName(@PathVariable("loginName") String loginName) {

		return userserv.findUserByLoginName(loginName);
	}

	@RequestMapping(value="/ds/createInstructor/{emailId}/{adminInfo}" , method = RequestMethod.GET)
	public void getConfirmationEmailPage(@PathVariable("emailId") String emailId, @PathVariable("adminInfo") String adminInfo) {
		
//		System.out.println("I'm here.... "+emailId);
		System.out.println(emailId+"******************");
		EmailData tempEmailInfo = new EmailData();
		String[] tempName= emailId.split("@");
//		tempEmailInfo.setName(tempName[0]);
//		tempEmailInfo.setPassword(tempEmailInfo.generateRandomString(tempEmailInfo.getName()));
//		tempEmailInfo.s
		
		try {
		TblUser user = new TblUser();
			
		user.setLoginName(tempName[0]);
		
		user.setUserPassword(tempEmailInfo.generateRandomString(user.getLoginName()));
		
		user.setName(emailId);
		
		user.setEmailAddress(emailId);
		
		user.setUpdatedBy(adminInfo);
		
		TblRoletype roleType=userRepo.findByUserType(3) ;
		
		
		
		List<TblRoletype> roleList = new ArrayList<>();
		roleList.add(roleType);
		
		user.setTblroles(roleList);
		TblUser tbluser = userserv.saveUser(user);
		TblPasswordChange tempPasschange = new TblPasswordChange();
		tempPasschange.setIsUsed('N');
		tempPasschange.setUserInfo(tbluser.getUserID());
		tempPasschange = passChangeServ.save(tempPasschange);
		adminEmailManager.sendEmail(emailId , tempPasschange.getID() , tbluser.getUserPassword());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * @RequestMapping(value="/createInstructor/confirmation") public
	 * ModelAndView confirmationEmail() { return new
	 * ModelAndView("ConfirmationEmail"); }
	 */

	// @RequestMapping(method = RequestMethod.POST, value =
	// "/createInstructor/save", consumes = MediaType.APPLICATION_JSON_VALUE,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestMapping(method = RequestMethod.POST, value = "/createInstructor",
	// consumes = "application/json")
	// @ResponseBody
	// public TblUser save(@RequestBody TblUser tadmin) {
	// try {
	// userserv.save(tadmin);
	// } catch (Exception e) {
	//
	//
	// return null;
	// }
	// return tadmin;
	//
	// }

}

