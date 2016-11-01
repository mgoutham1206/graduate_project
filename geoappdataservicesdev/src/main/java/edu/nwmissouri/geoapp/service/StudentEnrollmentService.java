package edu.nwmissouri.geoapp.service;

import java.util.Map;

import edu.nwmissouri.geoapp.model.TblSection;

public interface StudentEnrollmentService {
	
	Map<String,Object> findAll();
	
	TblSection findOne(int id);
	
	Map<String,Object> findAllActiveSections();
	
}
