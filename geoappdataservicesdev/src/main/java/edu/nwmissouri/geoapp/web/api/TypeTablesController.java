package edu.nwmissouri.geoapp.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.model.TblAttributetype;
import edu.nwmissouri.geoapp.model.TblGeometrytype;
import edu.nwmissouri.geoapp.model.TblHardnesstype;
import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblRocktype;
import edu.nwmissouri.geoapp.model.TblSpecificgravitytype;
import edu.nwmissouri.geoapp.model.TblStreakcolortype;
import edu.nwmissouri.geoapp.serviceImpl.AttributeTypeServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.GeometryTypeServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.HardnessTypeServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.LusterServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.RockTypeServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.SpecificgravityTypeServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.StreakColorTypeServiceImpl;

@RestController
@RequestMapping("/ds/TypeTables")
public class TypeTablesController {
	
	@Autowired
	private LusterServiceImpl lusterServiceImpl;
	@Autowired
	private HardnessTypeServiceImpl hardnesstypeServiceImpl;
	
	@Autowired
	private AttributeTypeServiceImpl attributeTypeServiceImpl;
	
	@Autowired
	private GeometryTypeServiceImpl geometryTypeServiceImpl;
	
	@Autowired
	private RockTypeServiceImpl rockTypeServiceImpl;
	
	@Autowired
	private StreakColorTypeServiceImpl streakColorTypeServiceImpl;
	
	@Autowired
	private SpecificgravityTypeServiceImpl specificgravityTypeServiceImpl;
		
	@RequestMapping(value="/luster/getAll")
	public List<TblLustertype> getAllLusterType(){
		return lusterServiceImpl.findAllLusterType();
	}
	
	@RequestMapping(value="/luster/save/{metal}")
	public void saveLusterType(@PathVariable String metal){
		String luster = metal;
		TblLustertype lustertype = new TblLustertype();
		lustertype.setLusterName(luster);
		lusterServiceImpl.saveLusterType(lustertype);
	}
	
	
	@RequestMapping(value = "/hardness/getAll")
	public List<TblHardnesstype> getHardnessType() {		
		return hardnesstypeServiceImpl.findAll();
	}
	
	@RequestMapping(value = "/streakcolor/getAll")
	public List<TblStreakcolortype> getStreakType() {		
		return streakColorTypeServiceImpl.findAll();
	}
	
	@RequestMapping(value = "/attributetype/getAll")
	public List<TblAttributetype> getAttributeType() {		
		return attributeTypeServiceImpl.findAll();
	}
	
	@RequestMapping(value = "/geometrytype/getAll")
	public List<TblGeometrytype> getGeometryType() {		
		return geometryTypeServiceImpl.findAll();
	}
	
	@RequestMapping(value = "/rocktype/getAll")
	public List<TblRocktype> getrockType() {		
		return rockTypeServiceImpl.findAll();
	}
	
	@RequestMapping(value = "/specificgravity/getAll")
	public List<TblSpecificgravitytype> getspecificgravityType() {		
		return specificgravityTypeServiceImpl.findAll();
	}
	
	
	

}
