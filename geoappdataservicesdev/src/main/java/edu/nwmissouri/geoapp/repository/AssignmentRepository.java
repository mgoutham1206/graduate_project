package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblSection;

public interface AssignmentRepository extends JpaRepository<TblAssignment, Integer> {

	
	public TblAssignment findTblAssignmentByassignID(int assignId);
	
//	get assignments for a given student based on section id
	@Query("select a from TblAssignment a where a.tblSection.sectionID in (select s.tblSection.sectionID from TblStudent s where s.studentID = ?1)")
	public List<TblAssignment> findTblAssignmentBystudentID(Integer studentId);
	
	//	get assignments for a given student based on section id
	@Query("select a from TblAssignment a where a.tblSection.sectionID=?1")
	public List<TblAssignment>findBySecID(int sectionId);
	
	//Get Section id for a given student
	@Query("select s from TblSection s where s.sectionID = (select st.tblSection.sectionID from TblStudent st where st.studentID = ?1)")
	public TblSection getSectionID(int studentID);
	
	//Changes by Krishna Reddy -- checking if an Assignment already Exists 
	
	@Query("select count(name) from TblAssignment a where a.name=?1")
	public long findIfAnAssignmentExists(String assignmentName);
}
