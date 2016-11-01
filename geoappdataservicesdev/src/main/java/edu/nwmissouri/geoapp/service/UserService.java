package edu.nwmissouri.geoapp.service;

import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.model.TblUserrole;

import java.util.List;
import java.util.Map;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblUser; 


public interface UserService {

	
	public TblUser findUserbyEmail(String emailAddress );
	
	public TblUser findbyuserID(int userid);	
	
	public TblUser findUserForgetPassword(String emailAddress, String newPassword);
	
	public TblUser findUserByLoginName(String loginName);
	
	public void createInstructor(TblUser instructor) throws Exception;
	
	public String checkLogin(String loginName, String userPassword);
    public List<TblSection> getAllSections(String loginName);
    
    void evictCache();
    
    public TblUser  findUserbyuserId(int userId);  
	
	public TblUser saveUser(TblUser tadmin) throws Exception;
	
	public void assignRoleForInstructor(TblUser tbluser);
	
	public List<TblAssignment> getAllSetionAssignments(String secId);
	
	public TblSection findBySectionID(int sectionId);
	
	public void updateAssignment(TblAssignment assignment);
	
	
}
