package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblHardnesstype;
import edu.nwmissouri.geoapp.repository.HardnesstypeRepository;
import edu.nwmissouri.geoapp.service.HardnessTypeService;


@Service
public class HardnessTypeServiceImpl  implements HardnessTypeService{

	
	@Autowired
	HardnesstypeRepository hardrepo;
	
	@Override
	public List<TblHardnesstype> findAll() {
		// TODO Auto-generated method stub
		return hardrepo.findAll();
	}

	

}
