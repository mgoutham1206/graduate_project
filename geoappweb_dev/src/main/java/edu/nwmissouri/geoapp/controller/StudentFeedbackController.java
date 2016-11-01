package edu.nwmissouri.geoapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.controller.form.PhaseOneForm;
import edu.nwmissouri.geoapp.service.FeedbackService;
@Controller
@RequestMapping("/student")
public class StudentFeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	@RequestMapping(value = "/getPhaseevaluationByAssignmentId/{assignId}", method = RequestMethod.POST)
	public ModelAndView getPhaseevaluation(@RequestParam String assignId) {
		System.out.println("************" + assignId);
		long assignmentId=(int)Integer.parseInt(assignId);
		Map<String, Object> model=new HashMap();
		model.put("assignID",assignmentId );
		return new ModelAndView("phaseone","model",model);
}
	
}
