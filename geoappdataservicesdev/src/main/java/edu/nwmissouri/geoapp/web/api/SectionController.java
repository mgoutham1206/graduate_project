package edu.nwmissouri.geoapp.web.api;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblInstructorsection;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblTermtype;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.AssignmentRepository;
import edu.nwmissouri.geoapp.repository.InstructorsectionRepository;
import edu.nwmissouri.geoapp.repository.SectionRepository;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.serviceImpl.SectionServiceImpl;

@RestController
@RequestMapping("/rest/section")
public class SectionController {
	@Autowired
	private SectionServiceImpl sectionServiceImpl;	
	
	 @Autowired
	 private SectionRepository sectionRepo;
	 
	 @Autowired
	 private UserRepository userRepo;
	 
	 @Autowired
	 private InstructorsectionRepository insSecRepo;
	 
	 @Autowired
	 private AssignmentRepository assRepo;
	
	//This GET method is for getting all sections
	@RequestMapping(value="/getAllSections",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<TblSection> getAssignment(){			
//		List<TblSection> li =  sectionServiceImpl.getSectionData();
//		
//		for (int i = 0; i < li.size(); i++) {
//			System.out.println("sdfa");
//			System.out.println("asdasd"+li.get(i).getTblTermtype().getTermID());
//		}
		
		 return sectionServiceImpl.getSectionData();
	}
	
	//This POST method is for creating a section
	@RequestMapping( value="/CreateSection/{tid}/{instructorID}",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public void save(@RequestBody TblSection sec, @PathVariable String tid,@PathVariable int instructorID){	
		
		TblTermtype tblTerm =  new TblTermtype();
		System.out.println("Hellooo*********"+instructorID);
		
		
		if(Integer.parseInt(tid) == 1){
			tblTerm.setTermID(1);
			tblTerm.setTermName("Fall");
			tblTerm.setDispayOrder(1);			
		}else if(Integer.parseInt(tid) == 2){
			tblTerm.setTermID(2);
			tblTerm.setTermName("Spring");
			tblTerm.setDispayOrder(2);
		}else{
			tblTerm.setTermID(3);
			tblTerm.setTermName("Summer");
			tblTerm.setDispayOrder(3);
		}
		
		sec.setTblTermtype(tblTerm);	
	
	TblUser user=userRepo.findTblUserByuserID(instructorID);
	//System.out.println(sec.getAllowEnrollement());
	//sec.setAllowEnrollement("Y");
	List<TblUser> userlist=new ArrayList<>();
	userlist.add(user);
	sec.setTblusers(userlist);
	sectionServiceImpl.saveSection(sec);	
	
	}
	
	//This GET method is for getting single JSON object by ID
	@RequestMapping(value="/getSectionByID/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Hashtable getAssignmentByID(@PathVariable int id){
		
		System.out.println("##################");
		TblSection sec = sectionServiceImpl.getSectionByID(id);
		
		System.out.println(sec.getTblTermtype().getTermID());
		
//			sec.getTblTermtype().getTermID();
//		System.out.println("asdfasdfsda"+sec.getTblTermtype().getTermID());	
		Hashtable table = new Hashtable();
		table.put("tblsec", sec);
		table.put("termID", sec.getTblTermtype().getTermID());
		
		return table;
	}
	
	
	@RequestMapping( value="/UpdateSection/{id}/{tid}",method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public void Update(@PathVariable String id,@PathVariable String tid,@RequestBody TblSection sec){
		
		TblSection PastSec = sectionServiceImpl.getSectionByID(Integer.parseInt(id));		
		TblTermtype tblTerm = new TblTermtype();
		
		PastSec.setTitle(sec.getTitle());
		PastSec.setClassURL(sec.getClassURL());
		PastSec.setExpectedNoofStudents(sec.getExpectedNoofStudents());
		PastSec.setEnrollmentPassword(sec.getEnrollmentPassword());
		PastSec.setAllowEnrollement(sec.getAllowEnrollement());	
		PastSec.setBasicdescription(sec.getBasicdescription());
        PastSec.setYear(sec.getYear());
		if(Integer.parseInt(tid) == 1){
			tblTerm.setTermID(1);
			tblTerm.setTermName("Fall");
			tblTerm.setDispayOrder(1);			
		}else if(Integer.parseInt(tid) == 2){
			tblTerm.setTermID(2);
			tblTerm.setTermName("Spring");
			tblTerm.setDispayOrder(2);
		}else{
			tblTerm.setTermID(3);
			tblTerm.setTermName("Summer");
			tblTerm.setDispayOrder(3);
		}
		
		PastSec.setTblTermtype(tblTerm);
		sectionServiceImpl.updateSection(PastSec);						
	}
	@RequestMapping(value="/deleteSectionByID/{id}",method=RequestMethod.DELETE)
	@ResponseBody public void Delete(@PathVariable String id){
		
		int secId = Integer.parseInt(id);
		
		//sectionServiceImpl.deleteSection(Integer.parseInt(id));
		TblSection sec = sectionRepo.getOne(secId);
		
		
		//System.out.println("id"+sec.getTblInstructorsections().get(0).getTblSection().getSectionID());
		List<TblInstructorsection> insList = sec.getTblInstructorsections();
		
		for (int i = 0; i < insList.size(); i++) {
			if(secId == insList.get(i).getTblSection().getSectionID()){
				System.out.println(secId == insList.get(i).getTblSection().getSectionID());
				//insList.remove();
				insSecRepo.delete(insList.get(i));
			}
		}
		
		sec.setTblInstructorsections(insList);		
		//System.out.println("null"+sec.getTblInstructorsections().get(0).getTblSection().getSectionID());
		
		List<TblAssignment> assListt  = sec.getTblAssignments();		
		for (TblAssignment tblAssignment : assListt) {
			System.out.println(tblAssignment.getTblSection().getSectionID());
			if(secId==tblAssignment.getTblSection().getSectionID()){
				//assListt.remove(tblAssignment);
				assRepo.delete(tblAssignment);
			}			
		}	
		
//		for (int i = 0; i < assListt.size(); i++) {
//			if(secId == assListt.get(i).getTblSection().getSectionID()){
//				System.out.println(secId == assListt.get(i).getTblSection().getSectionID());
//				insList.remove(assListt.get(i));
//			}
//		}
		
//		sectionServiceImpl.saveSection(sec);
//		sectionRepo.save(sec);	
//		sectionServiceImpl.updateSection(sec);
//		sectionServiceImpl.saveSection(sec);
		
		
		sectionRepo.delete(sec);
		//sectionRepo.de
		
	}
}
