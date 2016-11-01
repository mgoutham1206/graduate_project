package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.repository.LusterRepository;
import edu.nwmissouri.geoapp.repository.StudentRepository;
import edu.nwmissouri.geoapp.service.LusterService;

@Service
public class LusterServiceImpl implements LusterService {
	
	@Autowired
	private LusterRepository lusterRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<TblLustertype> findAllLusterType() {
		return lusterRepository.findAll();		
	}

	@Override
	public void saveLusterType(TblLustertype tblLustertype) {
		lusterRepository.save(tblLustertype);
		
	}
	
	@Override
	public List<TblStudent> findAllStudents() {
		return studentRepository.findAll();
		
	}

}
