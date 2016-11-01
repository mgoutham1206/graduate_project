package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.StudentRepository;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.repository.UserroleRepository;
import edu.nwmissouri.geoapp.service.StudentEnrollmentConfirmationService;

@Service
public class StudentEnrollmentConfirmationServiceImpl implements StudentEnrollmentConfirmationService{  
	
	@Autowired
	UserRepository userRepository;   
	
	@Autowired
    StudentRepository studentRepository;
   
	@Autowired
	UserroleRepository userroleRepository;

	@Override
	public TblUser save(TblUser tuser) throws Exception {
		// TODO Auto-generated method stub
		 
		
		
		TblUser result = userRepository.save(tuser); 
		userRepository.flush();
		return result; 
		
	}    
	
    @Override
    public TblStudent save(TblStudent tstudent) throws Exception {
       
        return studentRepository.save(tstudent);        
        
        
    }
    
//    @Override
//    public TblUserrole save(TblUserrole tuserole) throws Exception {   
//    	return userroleRepository.save(tuserole);
//    }
    
   
    @Override
    public TblUser findTblUserByloginName(String loginName){
    	return userRepository.findTblUserByloginName(loginName);
    }

	
	public TblStudent findOne(int studentId){
		return studentRepository.findOne(studentId);
	}
   
	@Autowired
	UserRepository aRepository; 

	public TblUser saveUser(TblUser tstudent) throws Exception {
		// TODO Auto-generated method stub
		return aRepository.save(tstudent);
	}     
	
	public String isValidUserLoginName(String loginName,String emailID){
		
		List<TblUser> allUsers  = userRepository.findAll();
		for(TblUser tempUser : allUsers){
			if((tempUser.getLoginName().equalsIgnoreCase(loginName))&&(tempUser.getEmailAddress().equalsIgnoreCase(emailID))){
				return "LEF"; //login&email already exists
			}else if((tempUser.getLoginName().equalsIgnoreCase(loginName))||(tempUser.getEmailAddress().equalsIgnoreCase(emailID))){
				if(tempUser.getLoginName().equalsIgnoreCase(loginName)){
					return "LF"; //Only login Already exists
				}else{
					return "EF";   //Only Email Already exists
				}
			}
		}
		
		return "LET";
	}
    
//	public boolean isValidEmailAddress(String emailID){
//         List<TblUser> allUsers  = userRepository.findAll();
//		
//		boolean flag = true;
//		
//		for(TblUser tempUser : allUsers){
//			if(tempUser.getEmailAddress().equalsIgnoreCase(emailID)){
//				flag = false;
//			}
//		}
//		
//		return flag;
//	}
//	
	
    


	
	

}
