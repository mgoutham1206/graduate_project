package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.nwmissouri.geoapp.model.TblGeometrytype;
import edu.nwmissouri.geoapp.repository.GeometrytypeRepository;
import edu.nwmissouri.geoapp.service.GeometryTypeService;

@Service
public class GeometryTypeServiceImpl implements GeometryTypeService{

	@Autowired
	private GeometrytypeRepository attributetyperepo; 
	
	
	@Override
	public List<TblGeometrytype> findAll() {
		// TODO Auto-generated method stub
		return attributetyperepo.findAll();
	}

}
