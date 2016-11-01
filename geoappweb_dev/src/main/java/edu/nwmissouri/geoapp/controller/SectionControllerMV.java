package edu.nwmissouri.geoapp.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.SocketUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblInstructorsection;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.AssignmentRepository;
import edu.nwmissouri.geoapp.repository.InstructorsectionRepository;
import edu.nwmissouri.geoapp.repository.SectionRepository;
import edu.nwmissouri.geoapp.repository.StudentRepository;
import edu.nwmissouri.geoapp.serviceImpl.SectionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.UserServiceImpl;

@Controller
@RestController
@RequestMapping("/view")
public class SectionControllerMV {
	
	@Autowired
	private SectionServiceImpl sectionServiceImpl;
	
	@Autowired
	private UserServiceImpl service;
	
	 @Autowired
	 private SectionRepository sectionRepo;
	 
	
	@Autowired
	 private InstructorsectionRepository insSecRepo;
	 
	 @Autowired
	 private AssignmentRepository assRepo;
	 
	 @Autowired
	 private StudentRepository stuRepo;
	

	@RequestMapping("/getSections")
	public ModelAndView getSections(HttpServletRequest request) {	
		
		if(request.getSession().getAttribute("userdetailsinfo") != null){
		String loginName = ((TblUser)request.getSession().getAttribute("userdetailsinfo")).getLoginName();
		ModelMap secModel = new ModelMap();
		secModel.addAttribute("Section", sectionServiceImpl.getSecByInstructor(loginName));
		//secModel.addAttribute("Section", sectionServiceImpl.getSectionData())
		return new ModelAndView("Section", secModel );
		}
		
		request.getSession().setAttribute("userdetailsinfo", null);
		return new ModelAndView("redirect:/view/login");
	}
	
	/*@RequestMapping("/instructorAsd")
	public ModelAndView instructorHome() {	
		ModelMap secModel = new ModelMap();
		secModel.addAttribute("Section", sectionServiceImpl.getSectionData());
		ModelMap model = new ModelMap();
		//model.addAttribute("sections", service.getAllSections(instructor.getLoginName()));
        return new ModelAndView("instructorHome", model);
	}
	*/
	
	@RequestMapping(value="/instructor", method=RequestMethod.GET)
	public ModelAndView header(HttpServletRequest request) {		
		/*System.out.println(((TblUser)request.getSession().getAttribute("userdetailsinfo")).getLoginName()+"****************************");
		System.out.println("*******"+((TblUser)request.getSession().getAttribute("userdetailsinfo")).getUserID()+"****************************");
*/
		
		String loginName = ((TblUser)request.getSession().getAttribute("userdetailsinfo")).getLoginName();		
		TblUser userdetail = service.findUserByLoginName(loginName);
		
		ModelMap model = new ModelMap();
		model.addAttribute("userInfo",	(TblUser)request.getSession().getAttribute("userdetailsinfo"));
		request.getSession().setAttribute("userdetailsinfo", userdetail);
	
			
			
			//request.getSession().setAttribute("instructorSession", loginName);
	        model.addAttribute("sections", service.getAllSections(loginName));
	        return new ModelAndView("instructorHome", model);
		
	}
	
	
//	@RequestMapping("/sectionTemplate")
//	public ModelAndView sectionTemplate() {	
//		ModelMap secModel = new ModelMap();
//		secModel.addAttribute("Section", sectionServiceImpl.getSectionData());
//		return new ModelAndView("sectionTemplate", secModel );
//	}
	
	/*@RequestMapping("/getSections")
	public ModelAndView getSections(HttpServletRequest request) {	
		String loginName = ((TblUser)request.getSession().getAttribute("userdetailsinfo")).getLoginName();	
		ModelMap secModel = new ModelMap();
		secModel.addAttribute("Section", sectionServiceImpl.getSecByInstructor(loginName));
		return new ModelAndView("Sections", secModel );
	}*/
	
	@RequestMapping("/createSection")
	public ModelAndView TestSections(HttpServletRequest request) {	
		if(request.getSession().getAttribute("userdetailsinfo") != null){
		ModelMap secModel = new ModelMap();
		System.out.println(((TblUser)request.getSession().getAttribute("userdetailsinfo")).getLoginName()+"****************************");
		System.out.println("*******"+((TblUser)request.getSession().getAttribute("userdetailsinfo")).getUserID()+"****************************");
		secModel.addAttribute("Section", sectionServiceImpl.getSectionData());
		secModel.addAttribute("instructorID",((TblUser)request.getSession().getAttribute("userdetailsinfo")).getUserID());
		return new ModelAndView("createSection", secModel );
		}
		
		request.getSession().setAttribute("userdetailsinfo", null);
		return new ModelAndView("redirect:/view/login");
	}
	
	@RequestMapping("/success")
	public ModelAndView success() {	
		ModelMap secModel = new ModelMap();
		secModel.addAttribute("Section", sectionServiceImpl.getSectionData());
		return new ModelAndView("ClassSuccess", secModel );
	}
	
	@RequestMapping("/updated")
	public ModelAndView updated() {	
		ModelMap secModel = new ModelMap();		
		secModel.addAttribute("Section", sectionServiceImpl.getSectionData());
		return new ModelAndView("updated", secModel );
	}
	
	
	@RequestMapping("/updateSection/{id}")
	public ModelAndView UpdateSections(@PathVariable int id) {	
		
		ModelMap secModel = new ModelMap();
		secModel.addAttribute("Section", sectionServiceImpl.getSectionByID(id));
//		System.out.println("@@@@@@@@@@@@@"+sectionServiceImpl.getSectionData().get(0).getSectionID());
		return new ModelAndView("updateSection", secModel );
	}
	
	@RequestMapping(value="/deleteSectionByID/{id}")
	@ResponseBody public ModelAndView Delete(@PathVariable String id,HttpServletRequest request){
		
		int secId = Integer.parseInt(id);
		
		try{
			
			
		TblSection sec = sectionRepo.getOne(secId);
//		System.out.println(sec.getSectionID()+"vipin***********");
//		System.out.println(sec.getTblAssignments().get(0).getAssignID()+"vipin***********");
//		
//		
//		System.out.println("id"+sec.getTblInstructorsections().get(0).getTblSection().getSectionID());
		List<TblInstructorsection> insList = sec.getTblInstructorsections();
		
		for (int i = 0; i < insList.size(); i++) {
			if(secId == insList.get(i).getTblSection().getSectionID()){
				//System.out.println(secId == insList.get(i).getTblSection().getSectionID());
				//insList.remove();
				insSecRepo.delete(insList.get(i));
			}
		}
		
		//sec.setTblInstructorsections(insList);		
		//System.out.println("null"+sec.getTblInstructorsections().get(0).getTblSection().getSectionID());
		
		List<TblAssignment> assListt  = sec.getTblAssignments();	
		for (int i = 0; i < assListt.size(); i++) {
			if(secId==assListt.get(i).getTblSection().getSectionID()){
				assRepo.delete(assListt.get(i));
			
		}
		}
		
//		for (TblAssignment tblAssignment : assListt) {
//			//System.out.println(tblAssignment.getTblSection().getSectionID());
//			if(secId==tblAssignment.getTblSection().getSectionID()){
//				//assListt.remove(tblAssignment);
//				assRepo.delete(tblAssignment);
//			}			
//		}	
		//sec.setTblAssignments(assListt);
		
		List<TblStudent> stuList = sec.getTblStudents();
		for (TblStudent tblStudent : stuList) {
			if(secId==tblStudent.getTblSection().getSectionID()){
				stuRepo.delete(tblStudent);
			}
		}
		//sec.setTblStudents(stuList);
		
		sectionRepo.delete(sec);
		}catch(Exception ex){
			System.out.println(ex);
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
		
		ModelMap secModel = new ModelMap();
		
		return new ModelAndView("deleteSection", secModel );
		
	}
}
