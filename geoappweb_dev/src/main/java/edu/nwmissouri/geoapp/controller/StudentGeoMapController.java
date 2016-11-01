package edu.nwmissouri.geoapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.serviceImpl.ImageSubmissionServiceImpl;

@Controller
@RestController
@RequestMapping("/client/geomap")
public class StudentGeoMapController {

	@Autowired
	private ImageSubmissionServiceImpl imageSubmissionServiceImpl;

	@Autowired
	private UserRepository userRepo;

	@RequestMapping(method = RequestMethod.GET, value = "/instructor")
	public ModelAndView showInstructorMapHome() {
		return new ModelAndView("geomaphome");
	}

	// To fetch ImageInfo based on Instructor related sections
	@RequestMapping(method = RequestMethod.GET, value = "/instructor/{id}")
	public ModelAndView showInstructorGeoMap(@PathVariable("id") String id, @RequestParam("rockType") String rockType) {
		ModelMap model = new ModelMap();
		// System.out.println(userRepo.findTblUserByuserID(Integer.parseInt(id)).getUserID());
		model.addAttribute("userInfo", userRepo.findTblUserByuserID(Integer.parseInt(id)));
		model.addAttribute("rockType", rockType);
		return new ModelAndView("geomaphome", model);
	}

	// To fetch ImageInfo based on Student related sections
	@RequestMapping(method = RequestMethod.GET, value = "/student/{id}")
	public ModelAndView showStudentGeoMap(@PathVariable("id") String id) {
		ModelMap model = new ModelMap();
		// System.out.println(userRepo.findTblUserByuserID(Integer.parseInt(id)).getUserID());
		model.addAttribute("studentuserInfo", userRepo.findTblUserByuserID(Integer.parseInt(id)));
		return new ModelAndView("studentgeomaphome", model);
	}

	// // request mapping to get details by student id
	// @RequestMapping("/geomap/allimagesinfo/student/{studentID}")
	// public ModelAndView getByStudent(@PathVariable("studentID") String
	// studentID){
	// ModelMap model = new ModelMap();
	// model.addAttribute("mapspins",imageSubmissionServiceImpl.getImagesByStudent(studentID));
	// return new ModelAndView("bystudentpins",model);
	// // return imageSubmissionServiceImpl.getImagesByStudent(studentID);
	// }

	// Vijay Request Mapping for Class
	@RequestMapping(value = "geomap/student/class/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TblImagesubmission> getByClass(@PathVariable("userId") String userId) {
		return imageSubmissionServiceImpl.getImagesByClass(userId);
	}

	// Working -- Request Map for JSP pages Change access Table.
	@RequestMapping(value = "/instructor/changeClassAccess/{userId}", method = RequestMethod.GET)
	public ModelAndView getChangeClassPinsVisibilityDisplayList(@PathVariable("userId") String userId) {
		
		System.out.println("I'm here");
		ModelMap model = new ModelMap();
		model.addAttribute("userInfo", userRepo.findTblUserByuserID(Integer.parseInt(userId)));
		System.out.println("\n\n\n\n\n\n\n\n\n ======================="+userId+"\n=================\n\n\n\n\n\n\n\n");
		model.addAttribute("sectionKeyValueForSubmission", imageSubmissionServiceImpl.getSubmisssionsBySection(userId));
		// model.addAttribute("sectionKeyValues",imageSubmissionServiceImpl.getImagesInfoToDisplayClassPinsVisibilty(userId));
		// System.out.println("***********************"+PhaseOneUtil.encodeImageAsBytetoString(imageSubmissionServiceImpl.getSubmisssionsBySection(userId).get("200002").get(0).getTblImagesubmissions().get(0).getImage()));

		return new ModelAndView("geomapchangeclassAccess", model);
	}

}
