package edu.nwmissouri.geoapp.web.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.StudentRepository;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.service.UserService;
import edu.nwmissouri.geoapp.serviceImpl.UserServiceImpl;
import edu.nwmissouri.geoapp.web.api.util.UserDetailForm;

@RestController
@RequestMapping("/ds")
public class LoginRestController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	

	@RequestMapping(value = "/forgetPasword/UserDetails/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkUserLoginEmail(@RequestParam("email") String email,
			@RequestParam("loginName") String loginName) {

		List<TblUser> userList = new ArrayList<>();

		userList = userRepo.findAll();
		System.out.println("Here is the details :\n " + email + "|||||||******||||||||"
				+ loginName);
		for (TblUser tempUser : userList) {
			System.out.println(tempUser.getEmailAddress());
			if (tempUser.getEmailAddress().equals(email)) {

				System.out.println(tempUser.getEmailAddress() + " is matched");
				if (tempUser.getLoginName().equals(loginName)) {
					System.out.println(tempUser.getLoginName() + "Is matched with" + tempUser.getEmailAddress());
					return "F";
				} else {
					return "LNNF";
				}
			}
		}
		return "NF";
	}
	
	
	@RequestMapping(value = "/login/getDetails", method = RequestMethod.POST)
	public UserDetailForm getUserLoginDetails(@RequestBody UserDetailForm userDetailForm){
		String login = "false";
		if(userDetailForm != null){
			try{
				if(userDetailForm.getLoginName() != null && userDetailForm.getUserPassword() != null){
					List<TblUser> userList = userRepo.findByLoginName(userDetailForm.getLoginName());
					
					for(TblUser user : userList){
						if(user.getLoginName().equals(userDetailForm.getLoginName())){
							if(user.getUserPassword().equals(userDetailForm.getUserPassword())){
								if(user.getTblroles().get(0).getRoleTypeID() == 1){
									int studentId = user.getUserID();
									if(studentRepository.findOne(studentId).getIsActive().equalsIgnoreCase("Y")){
										login = "true";
										userDetailForm.setStudentId(studentId);
										userDetailForm.setEmailAddress(user.getEmailAddress());
										userDetailForm.setName(user.getName());
										userDetailForm.setInvalid("false");
										userDetailForm.setSectionID(studentRepository.findOne(studentId).getTblSection().getSectionID());
									}
								}
							}
						}
					}
					
					if(login.equals("true")){
						return userDetailForm;
					}else{
						userDetailForm.setStudentId(0);
						userDetailForm.setInvalid("true");
					}
					
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
				return userDetailForm;
			}
			
		}
		return userDetailForm;
		
	}
	

}
