package edu.nwmissouri.geoapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblInstructorsection;
import edu.nwmissouri.geoapp.model.TblRocktype;
import edu.nwmissouri.geoapp.model.TblRoletype;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.model.TblUserrole;
import edu.nwmissouri.geoapp.model.TblUserrolePK;
import edu.nwmissouri.geoapp.service.UserService;
import edu.nwmissouri.geoapp.repository.AssignmentRepository;
import edu.nwmissouri.geoapp.repository.SectionRepository;
import edu.nwmissouri.geoapp.repository.StudentRepository;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.repository.UserroleRepository;




@Service
public class UserServiceImpl implements UserService {
	
	public static final int minLength = 8;
	public static final int maxLength = 16;
	

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserRepository repository;
	@Autowired
	private AssignmentRepository assignRepo;
	@Autowired
	private UserroleRepository UserroleRepository;
	@Autowired
	private SectionRepository sectionRepo;
	@Autowired
    StudentRepository studentRepository;
	@Autowired
	StudentServiceImpl studentserviceimpl;
	
	@Override
	public TblUser findUserbyEmail(String emailAddress) {
		
		
		return userRepo.findTblUserByemailAddress(emailAddress);
//		return null;
		
	}

	@Override
	public TblUser findUserForgetPassword(String emailAddress, String newPassword) {
		
		
		StringBuilder patternBuilder = new StringBuilder("((?=.*[a-z])"); // lower case alpha
		patternBuilder.append("(?=.*[@#$%])"); // special char
		patternBuilder.append("(?=.*[A-Z])"); // upper case alpha
		patternBuilder.append("(?=.*d)"); // digits 
		patternBuilder.append(".{" + minLength + "," + maxLength + "})"); // min and max of password;
		
		Pattern passwordPattern = Pattern.compile(patternBuilder.toString());
		
		if (passwordPattern.matcher(newPassword).matches()){
			//userRepo.save(arg0);   to add new password into the database.
			
		}
		return null;
	}

	@Override
	public TblUser findUserByLoginName(String loginName) {
		return userRepo.findTblUserByloginName(loginName);
		
	}

	@Override
	public void createInstructor(TblUser instructor) throws Exception {
		// TODO Auto-generated method stub
		// Implement code to insert username, default password and e-mail id into TblUser table, by using entered user email id
		userRepo.save(instructor);
	}

	@Override
	public void evictCache() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TblUser findUserbyuserId(int userId) {
		// TODO Auto-generated method stub
		return  userRepo.findTblUserByuserID(userId);
	}
	@Override
	public String checkLogin(String loginName, String userPassword) {
		List<TblUser> users = repository.findByLoginName(loginName);
		System.out.println("I'm in Check Login Page");
		for(TblUser user: users) {
			System.out.println("I'm in Check Login Page user info: "+user.getLoginName() +"*********"+user.getEmailAddress());
			if(user.getLoginName().equals(loginName)) {
				if(user.getUserPassword().equals(userPassword)) {
					if(user.getTblroles().get(0).getRoleTypeID() == 3){
						return "instructor";
					}else if (user.getTblroles().get(0).getRoleTypeID() == 2){
						return "admin";
					}else {
						int studID = user.getUserID();
						
						if((studentserviceimpl.findStudentByStudentID(studID).getIsActive().equalsIgnoreCase("y"))){
						 
						return "student";
						}else{
							return "wrong";
						}
					}
				}else {
					return "wrong";
				}
			}
		}
		return "wrong";
	}

	@Override
	public List<TblSection> getAllSections(String loginName) {
		List<TblUser> users = repository.findByLoginName(loginName);
		TblUser user = users.get(0);
		List<TblInstructorsection> instructorSectons = user.getTblInstructorsections();
		List<TblSection> sections = new ArrayList<>(instructorSectons.size());
		for(TblInstructorsection instructorSecton: instructorSectons) {
			sections.add(instructorSecton.getTblSection());
		}
		return sections;
	}

	public TblUser getInstructor(int secId) {
		List<TblUser> users = repository.findAll();
		List<TblInstructorsection> instructorSectons;
		for(TblUser user: users) {
			instructorSectons = user.getTblInstructorsections();
			for(TblInstructorsection instructorSecton: instructorSectons) {
				if(instructorSecton.getTblSection().getSectionID() == secId) {
					return user;
				}
			}
		}
		return new TblUser();
	}
	public List<TblAssignment> getAllAssignments() {
		return assignRepo.findAll();
	}
	
	public TblAssignment getAssignment(int assignId) {
		return assignRepo.findOne(assignId);
	}

	/*public void saveAssignment(TblAssignment assignment) {
		assignRepo.save(assignment);
	}*/  
	
	@Autowired
	UserRepository aRepository; 

	public TblUser saveUser(TblUser tadmin) throws Exception{
		
		return aRepository.save(tadmin);		
	}

	public boolean isValidUserLoginNameAndEmail(String loginName, String email){
		
		List<TblUser> allUsers  = userRepo.findAll();
		
		boolean flag = true;
		
		for(TblUser tempUser : allUsers){
			if(tempUser.getLoginName().equalsIgnoreCase(loginName) 
					|| tempUser.getEmailAddress().equalsIgnoreCase(email)){
				flag = false;
			}
		}
		
		return flag;
	}

	public List<TblAssignment> getAllSetionAssignments(String secId) {
		return assignRepo.findBySecID(Integer.parseInt(secId));
	}

	@Override
	public void assignRoleForInstructor(TblUser tableuser) {
		// TODO Auto-generated method stub
		TblUserrole role =new TblUserrole();
		role.setTblUser(tableuser);
		TblRoletype roleType=userRepo.findByUserType(1) ;		
		System.out.println("******"+userRepo.findByUserType(1));
		role.setTblRoletype(roleType);	
		TblUserrolePK tbluserpk=new TblUserrolePK();
		tbluserpk.setRoleTypeID(roleType.getRoleTypeID());
		tbluserpk.setUserID(tableuser.getUserID());
		role.setId(tbluserpk);
		UserroleRepository.save(role);
	}

	@Override
	public TblSection findBySectionID(int sectionId) {
		// TODO Auto-generated method stub
		return sectionRepo.findBysectionID(sectionId);
	}

	@Override
	public TblUser findbyuserID(int userid) {
		// TODO Auto-generated method stub
		return userRepo.findTblUserByuserID(userid);
	}
	
	// Changes by Krishna Reddy -- added method to udpate Assignment 
	
	@Override	
	public void updateAssignment(TblAssignment assignment) {
			
		assignRepo.save(assignment);	
	}  
	
public boolean saveAssignment(TblAssignment assignment) {
		
		long count = assignRepo.findIfAnAssignmentExists(assignment.getName());
		
		if(count == 0){
			assignRepo.save(assignment);
			return true;			
		}else{
			return false;
		}		
	}
}
