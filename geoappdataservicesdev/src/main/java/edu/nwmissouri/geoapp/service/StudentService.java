package edu.nwmissouri.geoapp.service;

import java.util.List;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblSubmission;

public interface StudentService {

	public void saveSubmition(TblSubmission tblSubmission);
	public TblSubmission getSubmission(int assignId, int studentId);
	public TblSubmission getSubmission(Integer submitId);
	public void saveMineralSubmission(List<TblMineralsubmission> tblMineralsubmissions);
	List<TblAssignment> getTblAssignments(Integer studentId);
	public void deleteImageSubmission(Integer imageID);
}
