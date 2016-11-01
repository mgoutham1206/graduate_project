package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblRocktype;
import edu.nwmissouri.geoapp.repository.HardnesstypeRepository;
import edu.nwmissouri.geoapp.repository.RocktypeRepository;
import edu.nwmissouri.geoapp.service.RockTypeService;

@Service
public class RockTypeServiceImpl implements RockTypeService{

	
	@Autowired
	RocktypeRepository rocktyperepo;
	
	
	@Override
	public List<TblRocktype> findAll() {
		// TODO Auto-generated method stub
		return rocktyperepo.findAll();
	}

}
