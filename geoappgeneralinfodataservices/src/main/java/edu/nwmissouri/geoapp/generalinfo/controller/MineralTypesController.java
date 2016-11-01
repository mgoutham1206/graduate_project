package edu.nwmissouri.geoapp.generalinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SocketUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.generalinfo.model.minerals;
import edu.nwmissouri.geoapp.generalinfo.repository.MineralTypesRepository;
//import edu.nwmissouri.geoapp.generalinfo.repository.MineralpropertyRepo;

@Controller
@RestController
@RequestMapping("/ds/geoapp/general-info/mineraltypes")

public class MineralTypesController {
	
	@Autowired
	private MineralTypesRepository typesRepo;
	
	
	@RequestMapping(method = RequestMethod.GET, value="/all")
	public List<minerals> findAllMinerals(){
		return typesRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/mineral-types = {types}")
	public minerals findByMineralTypes(String types){
		minerals type = new minerals();
		
		if(types.equalsIgnoreCase("economic")){
			type = typesRepo.findByEconomic(types);
		}else if(types.equalsIgnoreCase("industrial")){
			type = typesRepo.findByIndustrial(types);
		}else{
			System.out.println("invalid input");
		}
		
		
		return type;
	}

}
