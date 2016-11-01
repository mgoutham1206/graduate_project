package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblPasswordChange;

public interface PasswordChangeRepository extends JpaRepository<TblPasswordChange, Integer>{
	
//	@Query("select a from TblPasswordChange a where a.userInfo := ?1")
	public List<TblPasswordChange> findByUserInfo(int userID);
}
