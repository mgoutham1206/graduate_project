package edu.nwmissouri.geoapp.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblSection;
public interface AssignmentService {
	
	Map<String,Object> findAssignment();
	public TblAssignment save(TblAssignment assignment) throws Exception; 
	public TblAssignment findAssignmentById(int assignmentId);
	
	public TblSection findSectionID(int studentId);
	
	public List<TblAssignment> getAssigments(int sectionId);
	public BigDecimal getPossiblePointsByPhase(int submissionID, int phase);
	
}
