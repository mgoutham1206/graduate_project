package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblRoletype;
import edu.nwmissouri.geoapp.model.TblUser;

public interface UserRepository  extends JpaRepository<TblUser, Integer> {
	
//	@Query("select u from u TblUser where u.emailAddress := ?1")
//	public TblUser findOne(){
//		return null;
//	}
//	

	public TblUser findTblUserByloginName(String loginName);
	
	public TblUser findTblUserByuserID(int userID);

	public List<TblUser> findByLoginName(String loginName);
	
	public TblUser findTblUserByemailAddress(String email); 
	
	@Query("SELECT p from TblRoletype p where p.roleTypeID=?1")
	public TblRoletype findByUserType(int roleid);
}
