

package edu.nwmissouri.geoapp.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.controller.form.EmailData;
import edu.nwmissouri.geoapp.controller.form.ForgetPasswordEmailData;
import edu.nwmissouri.geoapp.controller.form.StudentEmailData;
import edu.nwmissouri.geoapp.manager.AdminEmailManager;
import edu.nwmissouri.geoapp.manager.ForgetPasswordEmailManager;
import edu.nwmissouri.geoapp.manager.SendActivationMail;
import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblPasswordChange;
import edu.nwmissouri.geoapp.model.TblQuiz;
import edu.nwmissouri.geoapp.model.TblRoletype;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.serviceImpl.PasswordChangeServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.PoolServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.StudentServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("view")
public class LoginController {
	
	public int flag=0;
	
	@Autowired
	private StudentServiceImpl stuService;
	
	@Autowired
	private UserServiceImpl service;

	@Autowired
	private PoolServiceImpl poolServiceImpl;
	
	@Autowired
	private PasswordChangeServiceImpl passChangeServ;
	
	@Autowired
	private ForgetPasswordEmailManager forgetPasswordEmailManager;
	
	@RequestMapping(value="/login")
	public ModelAndView index(HttpServletRequest request) {
		HttpSession session = request.getSession();
//		request.setAttribute("messageInfo", "<ul>"
//				+ "<li><b>One of this may be the reason you are unable to login.</b></li>"
//				+ "<li>User name or password does not exsit.</li>"
//				+ "<li>If you are a student, you might be in Inactive state. Please consult your Instructor.</li>"
//				+ "</ul>");
		session.invalidate();
		ModelMap model = new ModelMap();
        model.addAttribute("instructor", new TblUser());
        if(flag==101)
        model.addAttribute("messageInfo", "<ul>"
				+ "<li><b>One of this may be the reason you are unable to login.</b></li>"
				+ "<li>User name or password does not exsit.</li>"
				+ "<li>If you are a student, you might be in Inactive state. Please consult your Instructor.</li>"
				+ "</ul>");
        return new ModelAndView("newIndex", model);
	}
	
	@RequestMapping(value="/instructor", method=RequestMethod.POST)
	public ModelAndView header(@ModelAttribute("instructor") TblUser instructor, BindingResult result, HttpServletRequest request) {
		flag=0;
		System.out.println(instructor.getLoginName() +"*******"+instructor.getUserID());
		String data = service.checkLogin(instructor.getLoginName(), instructor.getUserPassword());
		TblUser userdetail=service.findUserByLoginName(instructor.getLoginName());
		System.out.println(userdetail.getUserID()+"****************************");
		ModelMap model = new ModelMap();
		model.addAttribute("userInfo",	instructor);
		//request.getSession().setAttribute("userdetailsinfo", userdetail);
		if(data == "instructor") {
			
			System.out.println(model);
			request.getSession().setAttribute("userdetailsinfo", userdetail);
	        model.addAttribute("sections", service.getAllSections(instructor.getLoginName()));
	        return new ModelAndView("instructorHome", model);
		}else if (data == "admin"){
			request.getSession().setAttribute("userdetailsinfo", userdetail);
	        return new ModelAndView("AdminHomeWelcome", model);
			
		}else if(data == "student"){
			request.getSession().setAttribute("userdetailsinfo", userdetail);
			return new ModelAndView("redirect:/student/studentHome",model);
		}
		
		else {	
			//request.getSession().setAttribute("userdetailsinfo", null);
			flag=101;
			request.setAttribute("messageInfo", "<ul>"
					+ "<li><b>One of this may be the reason you are unable to login.</b></li>"
					+ "<li>User name or password does not exsit.</li>"
					+ "<li>If you are a student, you might be in Inactive state. Please consult your Instructor.</li>"
					+ "</ul>");
			return new ModelAndView("redirect:/view/login");
		}
	}
	
	@RequestMapping(value="/instructorTestingPage", method=RequestMethod.GET)
	public ModelAndView testFuctionIns(){
		flag=0;
		ModelMap model = new ModelMap();
		model.addAttribute("userInfo", service.findbyuserID(100002));
		return new ModelAndView("instructorHome", model);
	}
	
	@RequestMapping(value="/instructor/openSection")
	public ModelAndView openSection(@ModelAttribute("secId") String secId , HttpServletRequest request) {
		System.out.println("##################################");
		flag=0;
		ModelMap model = new ModelMap();
		TblUser user = service.getInstructor(Integer.parseInt(secId));
		TblSection section = service.findBySectionID(Integer.parseInt(secId));
		
        //model.addAttribute("instructor", ((TblUser)(request.getSession().getAttribute("userdetailsinfo"))));
        request.getSession().setAttribute("section", section);
        List<TblStudent> studentList = stuService.getStudentsBySection(Integer.parseInt(secId));
        
        List<TblUser> userList = new ArrayList<>();
        
        
        
        for (TblStudent stuList : studentList) {
        	 int stuID = stuList.getStudentID();  //getting studentID for-each student in student table
        	 
             userList.add(service.findbyuserID(stuID));   //adding each student for that sectionID to userList
              
			
		}
        Collections.sort(userList, new Comparator<TblUser>() {
            public int compare(TblUser o1, TblUser o2)
            {
                return o1.getName().compareTo(o2.getName()); // Compare by name, for example
            }});
        Collections.sort(studentList, new Comparator<TblStudent>() {
            public int compare(TblStudent o1, TblStudent o2)
            {
                return o1.getTblUser().getName().compareTo(o2.getTblUser().getName()); // Compare by name, for example
            }});
        
        for (int i = 0; i < userList.size(); i++) {
 			System.out.println(userList.get(i).getName());
 		}
        for (int i = 0; i < studentList.size(); i++) {
 			System.out.println(studentList.get(i).getTblUser().getName());
 		}
        
       model.addAttribute("EnrolleduserList", userList);
       model.addAttribute("EnrolledstudentList", studentList);
      
        return new ModelAndView("instructorSectionHome", model);
	}  
	
	@RequestMapping(method = RequestMethod.GET, value = "/ds/UpdateStudents", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean UpdateStudentActivation(@RequestParam("dataArray") String[] tempList) {
		flag=0;
		try {
		for(String temp : tempList) {
		String[] tempSingleData = temp.split("QQ");
		//String sectionID = tuser.getLoginName().split(",")[1]
		TblStudent tempStud = stuService.findStudentByStudentID(Integer.parseInt(tempSingleData[0]));
		tempStud.setIsActive(tempSingleData[1]);
		stuService.save(tempStud);
		}
		}catch(Exception e) {
			return false;
		}
		return true;
	}  
	
	
	@RequestMapping(value="/instructor/openSection/listAssignments")
	public ModelAndView header(@ModelAttribute("secId") String secId) {
		flag=0;
		ModelMap model = new ModelMap();
        model.addAttribute("assignments", service.getAllSetionAssignments(secId));
        model.addAttribute("sectionId", secId);
        return new ModelAndView("listAssignments", model);
	}
	
	@RequestMapping(value="/instructor/openSection/newAssignment")
	public ModelAndView newAssignment(@ModelAttribute("sectionId") String secId) {
		flag=0;
		ModelMap model = new ModelMap();
        model.addAttribute("assignment", new TblAssignment());
        model.addAttribute("secId", secId);
        return new ModelAndView("assignment2", model);
	}
	
@RequestMapping(value="/instructor/openSection/openAssignment")
public ModelAndView openAssignment(@ModelAttribute("assignId") String assignId, 
		@ModelAttribute("sectionId") String secId) {
	flag=0;
		TblAssignment assignment = service.getAssignment(Integer.parseInt(assignId));
		ModelMap model = new ModelMap();
        model.addAttribute("assignment", assignment);
        model.addAttribute("secId", secId);
        model.addAttribute("assignId", assignId);
        return new ModelAndView("assignment", model);
	}
	
	/*@RequestMapping(value="/instructor/openSection/saveAssignment")
	public ModelAndView saveAssignment(@ModelAttribute("assignment") TblAssignment assignment, BindingResult result,
			@ModelAttribute("sectionId") String secId) {
		flag=0;
		System.out.println(result.getRawFieldValue("due_date"));
//		assignment.setSecID(Integer.parseInt(secId));
		System.out.println("Here is the info of date: "+assignment.getDue_date());
		int assgnId = assignment.getAssignID();
		System.out.println("*******************"+service.findBySectionID(Integer.parseInt(secId)).getSectionID());
		assignment.setTblSection(service.findBySectionID(Integer.parseInt(secId)));
		service.saveAssignment(assignment);
		ModelMap model = new ModelMap();
		model.addAttribute("secId", assignment.getTblSection().getSectionID());
		model.addAttribute("assgnId", assgnId);
        return new ModelAndView("success", model);
	}	*/
	/*@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request) {
		flag=0;
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("redirect:/view/login");
	}*/
	
	//Changes by Krishna Reddy to create an assignment to select a pool while creating an assignment 
	
	@RequestMapping(value="/instructor/openSection/updateAssignment") 
	  public ModelAndView updateAssignment(@ModelAttribute("assignment") TblAssignment assignment, BindingResult result,  
			  @ModelAttribute("sectionId") String secId, @ModelAttribute("quizID") String quizID) {
	  
				  int assgnId =  assignment.getAssignID();
				  
				  assignment.setTblSection(service.findBySectionID(Integer.parseInt(secId)));
				  assignment.setTblQuiz(poolServiceImpl.findTblQuizbyQuizID(Integer.parseInt(quizID)));
			 
				  service.updateAssignment(assignment); 
				  
				  ModelMap model = new ModelMap();
				  
				  model.addAttribute("secId", assignment.getTblSection().getSectionID());
				  model.addAttribute("assgnId", assgnId); 
				  model.addAttribute("quizID", quizID); 
			      
				  return new  ModelAndView("success", model); 
	  }
	
	
	@RequestMapping(value = "/instructor/openSection/saveAssignmentsuccess/{sectionID}")
	public ModelAndView saveAssignmentsuccess(@PathVariable("sectionID") String sectionID) {

		int assignID = 0;
		ModelMap model = new ModelMap();
		model.addAttribute("assignID", assignID);
		model.addAttribute("secId", sectionID);
		return new ModelAndView("success", model);
	}

	@RequestMapping(value = "/instructor/openSection/saveAssignment/{sectionID}/{poolName}/{hiddenDateId}", method = RequestMethod.POST)
	public @ResponseBody boolean saveAssignment(@RequestBody TblAssignment assignment,
			@PathVariable("sectionID") String sectionID, @PathVariable("poolName") String poolName, 
			@PathVariable("hiddenDateId") String hiddenDateId) throws ParseException {
		
		//String date = request.getParameter("date");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
		//Date due_date = formatter.parse(hiddenDateId.substring(0, 10));
		java.util.Date due_date_temp = formatter.parse(hiddenDateId.substring(0, 10));
		Date due_date = new java.sql.Date(due_date_temp.getTime());
		assignment.setDue_date(due_date);
		
		
		TblQuiz tblQuiz = new TblQuiz (3,70,5,"Quiz", "Quiz", "00:02:00");
		poolServiceImpl.saveQuiz(tblQuiz);
				
		int assignID = assignment.getAssignID();
		assignment.setTblSection(service.findBySectionID(Integer.parseInt(sectionID)));
		assignment.setTblQuiz(tblQuiz);

		if (service.saveAssignment(assignment)) {
			poolServiceImpl.createPool(poolName, assignment); 
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/logout")
	public @ResponseBody ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("redirect:/GeoApp/view/login");
	}
	
	
	@RequestMapping(value="/forgetPassword")
	public ModelAndView forgetPassword() {
		flag=0;
        return new ModelAndView("ForgetPassword");
	}
	
	@RequestMapping(value="/ds/sendEmail" , method = RequestMethod.GET)
	public @ResponseBody boolean getConfirmationEmailPage(@RequestParam("email") String emailId) {
		ForgetPasswordEmailData tempEmailInfo = new ForgetPasswordEmailData();
		flag=0;
		try {
		TblUser user = service.findUserbyEmail(emailId);
			
		user.setUserPassword(tempEmailInfo.generateRandomString(user.getLoginName()));
		
		TblUser tbluser = service.saveUser(user);
		TblPasswordChange tempPasschange = new TblPasswordChange();
		tempPasschange.setIsUsed('N');
		tempPasschange.setUserInfo(tbluser.getUserID());
		tempPasschange = passChangeServ.save(tempPasschange);
		forgetPasswordEmailManager.sendEmail(emailId , tempPasschange.getID() , tbluser.getUserPassword(), tbluser.getLoginName());
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	

	

	
}

