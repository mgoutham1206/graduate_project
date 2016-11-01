package edu.nwmissouri.geoapp.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import edu.nwmissouri.geoapp.model.TblInstructorsection;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblTermtype;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.InstructorsectionRepository;
import edu.nwmissouri.geoapp.repository.SectionRepository;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.service.SectionService;

@Service
public class  SectionServiceImpl implements SectionService{
	
	@Autowired
	private SectionRepository sectionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InstructorsectionRepository insSecRepo;
	
	@Override
	public void saveSection(TblSection tblSection) {
		// TODO Auto-generated method stub
		sectionRepository.save(tblSection);
	}
	
	public List<TblSection> getSectionData() {
		//Map<String,Object> map = new HashMap<String,Object>();
		//List<TblSection> tblSection=sectionRepository.findAll();
		//System.out.println(tblSection);
		//map.put("section",tblSection);
		
		System.out.println(sectionRepository.findAll());
		return sectionRepository.findAll();
		
	}
	
	public List<TblSection> getInstSectionData() {
		//Map<String,Object> map = new HashMap<String,Object>();
		//List<TblSection> tblSection=sectionRepository.findAll();
		//System.out.println(tblSection);
		//map.put("section",tblSection);
		
		System.out.println(sectionRepository.findAll());
		return sectionRepository.findAll();
		
	}
	
	@Override
	public TblSection getSectionByID(int id) {
		// TODO Auto-generated method stub
		return sectionRepository.findOne(id);
	}
	/*@Override
	public TblTermtype getSectionByIDTT(int id) {
		// TODO Auto-generated method stub
		return sectionRepository.findOne(id).;
	}*/

	@Override
	public void updateSection(TblSection tblSection) {		
		sectionRepository.save(tblSection);	
	}	
	
	@Override
	public List<TblSection> getSecByInstructor(String loginName){
	   TblUser user = userRepository.findTblUserByloginName(loginName);	   
	   List<TblInstructorsection> instructorSections = user.getTblInstructorsections();
	   List<TblSection> sections = new ArrayList<>();	   
	   for (int i = 0; i < instructorSections.size(); i++) {		   
		   sections.add(instructorSections.get(i).getTblSection());		
	   }	   
	   return sections;
	}

	@Override
	public void deleteSection(int secId) {
//		TblSection sec = new TblSection();
//		TblInstructorsection insSec = new TblInstructorsection();
//		sec.getTblInstructorsections();
//		//sec.getTblAssignments();
		
		
		
		sectionRepository.delete(secId);
		
	}

}
