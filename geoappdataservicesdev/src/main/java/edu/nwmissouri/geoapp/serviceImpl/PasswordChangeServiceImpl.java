package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblPasswordChange;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.PasswordChangeRepository;
import edu.nwmissouri.geoapp.repository.UserRepository;

@Service
public class PasswordChangeServiceImpl {
	
	@Autowired
	private PasswordChangeRepository passChangeRepo;

	public TblPasswordChange findByID(int passwordChangeID) {
		// TODO Auto-generated method stub
		return passChangeRepo.findOne(passwordChangeID);
	}

	public List<TblPasswordChange> findByUserInfo(int userID){
		return passChangeRepo.findByUserInfo(userID);
	}
	

	public TblPasswordChange save(TblPasswordChange tpassChangeRec) throws Exception{
		
		return passChangeRepo.save(tpassChangeRec);

	}
	
	
	public void delete(TblPasswordChange passwordChange) {
		passChangeRepo.delete(passwordChange.getID());
	}
}	
