package edu.nwmissouri.geoapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.nwmissouri.geoapp.model.TblImagesubmission;



@Repository
public interface ImagesubmissionRepository  extends JpaRepository<TblImagesubmission, Integer> {
	
//	@Query("Select t from tbl_imagesubmission t where t.IsBest=:isBest")
//	Map<String,Object> findByIsBest(@Param("isBest") String isBest);
	
	@Query("select i from TblImagesubmission i where i.isBest IN ('I' , 'S' , 'M') ")
	public List<TblImagesubmission> findBaseImages();

	@Query("select i from TblImagesubmission i where i.tblSubmission.submissionID= ?1")
	public List<TblImagesubmission> findImagesBySubmissionId(int submissionId);
	
	
	@Query("delete  from TblImagesubmission i where i.tblSubmission.submissionID= ?1")
	@Modifying
	@Transactional
	public void deleteImagesBySubmissionId(int submissionId);

}