package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblStudent;

public interface StudentRepository extends JpaRepository<TblStudent, Integer> {

	TblStudent findTblStudentBystudentID(int studentID);
	
	@Query("SELECT p from TblStudent p where p.tblSection.sectionID=?1")
	public List<TblStudent> findStudentsBySection(int sectionID);

}