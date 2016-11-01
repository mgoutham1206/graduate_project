package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblStreakcolortype;
import edu.nwmissouri.geoapp.repository.StreakcolortypeRepository;
import edu.nwmissouri.geoapp.service.StreakColorTypeService;

@Service
public class StreakColorTypeServiceImpl implements StreakColorTypeService{

	@Autowired
	StreakcolortypeRepository streakcolorrepo;

	@Override
	public List<TblStreakcolortype> findAll() {
		// TODO Auto-generated method stub
		return streakcolorrepo.findAll();
	}
	
	
	
}
