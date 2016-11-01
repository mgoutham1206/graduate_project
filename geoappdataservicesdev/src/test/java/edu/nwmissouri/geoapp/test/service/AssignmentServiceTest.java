/*package edu.nwmissouri.geoapp.test.service;

import static org.junit.Assert.*;

//import static org.junit.Assert.*;
//
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.service.AssignmentService;
import edu.nwmissouri.geoapp.test.GeoAppUnitTesting;

@Transactional
public class AssignmentServiceTest extends GeoAppUnitTesting {

	@Autowired
	public AssignmentService assignmentservice;
	
	@Test
	public void tesFindSectionFromAssignment() {
		
		int sectionId=200002;
		TblAssignment tblassign=assignmentservice.findAssignmentById(400001);
		System.out.println("______________"+tblassign.getAssignID());
//		fail("Not yet implemented");
		assertEquals(sectionId, tblassign.getTblSection().getSectionID());
	}
	
	

}
*/