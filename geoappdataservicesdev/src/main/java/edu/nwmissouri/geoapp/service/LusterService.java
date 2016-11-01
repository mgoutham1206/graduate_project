package edu.nwmissouri.geoapp.service;

import java.util.List;

import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblStudent;

public interface LusterService {

	
	public List<TblLustertype> findAllLusterType();
	
	public void saveLusterType(TblLustertype tblLustertype);
	
	
	public List<TblStudent> findAllStudents();
}
