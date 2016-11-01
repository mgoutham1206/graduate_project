package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblPool;
import edu.nwmissouri.geoapp.model.TblQuiz;


public interface PoolRepository extends JpaRepository<TblPool, Integer> {

	
	public TblPool findTblPooltBypoolID(int poolID);
	
	@Query("select poolID from TblPool a where a.poolName =?1 and a.tblAssignment.assignID =?2")
	public int findPoolID(String poolName, int assignID);

	@Query("select poolID from TblPool a where a.tblAssignment.assignID =?1")
    public int findpoolIDByassignID(int assignID);
	
	@Query("select a from TblPool a where a.tblAssignment.assignID =?1")
    public TblPool findTblPoolByassignID(int assignID);
	
	
	


}
