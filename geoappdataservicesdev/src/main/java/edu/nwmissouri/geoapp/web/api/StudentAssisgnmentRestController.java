package edu.nwmissouri.geoapp.web.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.serviceImpl.AsssignmentServiceImpl;

@RestController
@RequestMapping("/ds/assignment")
public class StudentAssisgnmentRestController {
	@Autowired
	private AsssignmentServiceImpl asssignmentServiceImpl;
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String,Object> getAssignment(){		
		return asssignmentServiceImpl.findAssignment();
	}
	
	@RequestMapping(value="getSectionId/{studentId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public TblSection getSectionId(@PathVariable("studentId") Integer studentId){
		return asssignmentServiceImpl.findSectionID(studentId);
	}
	
	@RequestMapping(value="/getAll/{sectionId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<TblAssignment> getAssigmentsBySectionID(@PathVariable("sectionId") Integer sectionId){
		return asssignmentServiceImpl.getAssigments(sectionId);
	}

}
