package edu.nwmissouri.geoapp.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.serviceImpl.LusterServiceImpl;

@RestController
@RequestMapping(value="/luster")
public class LusterController {
	
	@Autowired
	private LusterServiceImpl lusterServiceImpl;
	
	
	@RequestMapping(value="/getAll")
	public List<TblLustertype> getAllLusterType(){
		return lusterServiceImpl.findAllLusterType();
	}
	
	@RequestMapping(value="/save/{metal}")
	public void saveLusterType(@PathVariable String metal){
		String luster = metal;
		TblLustertype lustertype = new TblLustertype();
		lustertype.setLusterName(luster);
		 lusterServiceImpl.saveLusterType(lustertype);
	}
	
	@RequestMapping(value="/students")
	public List<TblStudent> getAllStudents(){
		return lusterServiceImpl.findAllStudents();
	}
	
}
