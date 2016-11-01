package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblAttributeevaluation;
import edu.nwmissouri.geoapp.model.TblAttributeevaluationPK;
import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;

public interface AttributeevaluationRepository  extends JpaRepository<TblAttributeevaluation, Integer> {

	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query(value = "insert into tbl_attributeevaluation (MineralID, AttributeID, IsAccepted, Comments) values(?1, ?2, ?3, ?4)", nativeQuery = true)
	public void insertData(int MineralID, int AttributeID, String IsAccepted, String Comments);
	
	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query(value ="update  tbl_attributeevaluation  SET IsAccepted = ?3, Comments = ?4 where MineralID=?1 and AttributeID=?2 ", nativeQuery = true)
	public void updatePhaseData(int MineralID,int AttributeID,String IsAccepted, String Comments );

	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query(value="delete from tbl_attributeevaluation where MineralID=?1", nativeQuery = true)
	public void deleteAttributeEvaluationByMineralID(Integer mineralID);
}
