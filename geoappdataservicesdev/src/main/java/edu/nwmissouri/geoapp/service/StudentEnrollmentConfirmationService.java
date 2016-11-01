package edu.nwmissouri.geoapp.service;



import java.util.Map;

import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.model.TblUserrole;     

public interface StudentEnrollmentConfirmationService {   
	
//	Map<String,Object> findAll();	 
	
	public TblUser save(TblUser tuser) throws Exception; 
	
    public TblStudent save(TblStudent tstudent) throws Exception;
    
    
    
    public TblUser findTblUserByloginName(String loginName) throws Exception;
    
   
    public String isValidUserLoginName(String loginName,String emailID) throws Exception;
    
    //public boolean isValidEmailAddress(String emailID) throws Exception;
    
    
    

	//public void evictCache();

	//public TblUser findOne(int userID);  
	

}

