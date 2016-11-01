package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblSection;

public interface SectionRepository  extends JpaRepository<TblSection, Integer> {

	public TblSection findBysectionID(int sectionId);
	
	@Query("SELECT s from TblSection s where s.allowEnrollement=?1")
	public List<TblSection> getAllActiveSections(String active);

}
