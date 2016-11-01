package edu.nwmissouri.geoapp.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.repository.AssignmentRepository;
import edu.nwmissouri.geoapp.repository.ImagesubmissionRepository;
import edu.nwmissouri.geoapp.repository.MineralsubmissionRepository;
import edu.nwmissouri.geoapp.repository.RockSubmissionRepository;
import edu.nwmissouri.geoapp.repository.StudentRepository;
import edu.nwmissouri.geoapp.service.StudentService;



@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private ImagesubmissionRepository imagesubmissionRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private RockSubmissionRepository submissionRepository;
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private MineralsubmissionRepository mineralsubmissionRepository;
	
	public void saveSubmition(TblSubmission tblSubmission) {
		
		submissionRepository.save(tblSubmission);
	}

	
	public TblSubmission getSubmission(int assignId, int studentId) {
    	TblSubmission tbls= submissionRepository.findByAssignmentAndStudentId(assignId, studentId);
		return tbls;
	}


	@Override
	public TblSubmission getSubmission(Integer submitId) {
		return this.submissionRepository.findOne(submitId);
	}


	@Override
	public void saveMineralSubmission(List<TblMineralsubmission> tblMineralsubmissions) {
		// TODO Auto-generated method stub
		mineralsubmissionRepository.save(tblMineralsubmissions);
		
		
	}


	@Override
	public List<TblAssignment> getTblAssignments(Integer studentId) {
		// TODO Auto-generated method stub
		return assignmentRepository.findTblAssignmentBystudentID(studentId);
	}

	
	public List<TblStudent> getStudentsBySection(int sectionId){
		return studentRepository.findStudentsBySection(sectionId);
	}
	
	public TblStudent findStudentByStudentID(int studentId){
		return studentRepository.findOne(studentId);
	}
	
	public TblStudent save(TblStudent student){
		return studentRepository.save(student); 
		
	}
	
	public int getStudentCountBySection(int sectionId) {
		return studentRepository.findStudentsBySection(sectionId).size();
	}


	@Override
	public void deleteImageSubmission(Integer imageID) {
		// TODO Auto-generated method stub
			imagesubmissionRepository.delete(imageID);
	}
	
}
