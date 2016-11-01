package edu.nwmissouri.geoapp.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.serviceImpl.StudentEnrollmentServiceImpl;;

@RestController
@RequestMapping("/student/enrollment")

public class StudentEnrollmentController {
     
	@Autowired
	private StudentEnrollmentServiceImpl enrollment;
	
	
	@RequestMapping(value="/by-id/{id}")
	public TblSection getSectionById(@PathVariable int id){
		
		return enrollment.findOne(id);
	}
	
	@RequestMapping(value="/getAllClasses")
	public  Map<String, Object> getAll(){
		return enrollment.findAllActiveSections() ;
	}
	@RequestMapping(value="/getAllClassesPage")
	public  ModelAndView getAllJSP(){
		ModelMap secModel1 = new ModelMap();
		secModel1.addAttribute("StudentEnroll", enrollment.findAllActiveSections());
		//secModel1.addAttribute("StudentEnroll", enrollment.findAllActiveSections());
		//return enrollment.findAll() ;
		return new ModelAndView("StudentEnroll", secModel1 );   
		
	}
		
}
   