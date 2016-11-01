package edu.nwmissouri.geoapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.repository.StudentRepository;
import edu.nwmissouri.geoapp.service.StudentTblService;

public class StudentTblServiceImpl implements StudentTblService{  
	

	@Autowired
	private StudentRepository studentRepositary1;
	
	// saving studentID and sectionID  in student table
		public void saveStudent(TblStudent tblStudent) {		
			studentRepositary1.save(tblStudent);  
			
			
		}


}
