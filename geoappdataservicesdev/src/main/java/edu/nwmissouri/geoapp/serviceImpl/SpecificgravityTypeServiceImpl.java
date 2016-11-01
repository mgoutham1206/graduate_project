package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblSpecificgravitytype;
import edu.nwmissouri.geoapp.repository.SpecificgravitytypeRepository;
import edu.nwmissouri.geoapp.service.SpecificgravityTypeService;

@Service
public class SpecificgravityTypeServiceImpl implements SpecificgravityTypeService{

	@Autowired
	SpecificgravitytypeRepository spgravityrepo;
	
	@Override
	public List<TblSpecificgravitytype> findAll() {
		// TODO Auto-generated method stub
		return spgravityrepo.findAll();
	}

}
