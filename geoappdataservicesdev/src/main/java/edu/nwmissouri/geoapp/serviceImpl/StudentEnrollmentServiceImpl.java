package edu.nwmissouri.geoapp.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.repository.SectionRepository;
import edu.nwmissouri.geoapp.service.StudentEnrollmentService;

@Service
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {
	
	@Autowired
	private SectionRepository sectionRepository;
	
	//public List<TblSection> findAllTitles();

	@Override
	public Map<String, Object> findAll() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<TblSection> tblSection=sectionRepository.findAll();
		System.out.println(tblSection);
		map.put("section",tblSection);
		return map;
		
	}
	

	@Override
	public TblSection findOne(int id) {
		// TODO Auto-generated method stub
		return sectionRepository.findOne(id);
		
		
	}
	
	@Override
	public Map<String, Object> findAllActiveSections() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<TblSection> tblSection=sectionRepository.getAllActiveSections("Y");
		System.out.println(tblSection);
		map.put("section",tblSection);
		return map;
	}



}
