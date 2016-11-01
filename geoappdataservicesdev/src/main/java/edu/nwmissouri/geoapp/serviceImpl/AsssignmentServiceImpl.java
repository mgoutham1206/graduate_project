package edu.nwmissouri.geoapp.serviceImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.repository.AssignmentRepository;
import edu.nwmissouri.geoapp.repository.SubmissionRepository;
import edu.nwmissouri.geoapp.service.AssignmentService;
@Service
public class AsssignmentServiceImpl implements AssignmentService {
	
	@Autowired
	AssignmentRepository assignmentRepository;	
	@Autowired
	SubmissionRepository submissionRepo;

	@Override
	public Map<String,Object> findAssignment() {
		Map<String,Object> map = new HashMap<String,Object>();
		// TODO Auto-generated method stub
		List<TblAssignment> tblAssignment=assignmentRepository.findAll();
		System.out.println(tblAssignment);
		System.out.println(tblAssignment.get(0).getTblQuiz().getQuizID()+"*******");
		map.put("assignment",tblAssignment);
		map.put("quiz",tblAssignment.get(0).getTblQuiz());
		return map;
	}
	
	@Override
	public TblAssignment save(TblAssignment assignment) throws Exception {
		return assignmentRepository.save(assignment);
	}
	
	@Override
	public TblAssignment findAssignmentById(int assignmentId) {
		return assignmentRepository.findTblAssignmentByassignID(assignmentId);
		
	}
	
	@Override
	public TblSection findSectionID(int studentId){
		return assignmentRepository.getSectionID(studentId);
	}

	@Override
	public List<TblAssignment> getAssigments(int sectionId) {
		return assignmentRepository.findBySecID(sectionId);
	}
	@Override
	public BigDecimal getPossiblePointsByPhase(int submissionId,int phase) {
		TblSubmission tblsub=submissionRepo.getOne(submissionId);
		BigDecimal points=null;
		if(phase==1)
			points=tblsub.getTblAssignment().getPossiblepointsphase1();
		if(phase==2)
			points=tblsub.getTblAssignment().getPossiblepointsphase2();
		if(phase==3)
			points=tblsub.getTblAssignment().getPossiblepointsphase3();
		return points;
				
			}
}
