package edu.nwmissouri.geoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.serviceImpl.AsssignmentServiceImpl;

@RestController
@RequestMapping("/Assignment")
public class AssignmentController {

	@Autowired
	private AsssignmentServiceImpl ar;

	@RequestMapping(method = RequestMethod.POST, value = "/creation", consumes = "application/json")
	@ResponseBody
	public TblAssignment save(@RequestBody TblAssignment assignment) {
		try {
			return ar.save(assignment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}
}
