package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblAttributetype;
import edu.nwmissouri.geoapp.repository.AttributetypeRepository;
import edu.nwmissouri.geoapp.service.AttributeTypeService;

@Service
public class AttributeTypeServiceImpl implements AttributeTypeService{

	@Autowired
	private AttributetypeRepository attributetyperepo;
	
	@Override
	public List<TblAttributetype> findAll() {
		// TODO Auto-generated method stub
		return attributetyperepo.findAll();
	}

}
