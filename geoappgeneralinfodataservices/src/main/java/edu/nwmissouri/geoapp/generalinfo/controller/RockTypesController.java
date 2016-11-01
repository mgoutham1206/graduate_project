package edu.nwmissouri.geoapp.generalinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.generalinfo.model.rocktypes;
import edu.nwmissouri.geoapp.generalinfo.repository.RockTypesRepository;

@Controller
@RestController
@RequestMapping("/ds/geoapp/general-info/rocktypes")
public class RockTypesController {

	@Autowired
	private RockTypesRepository rockRepo;
	
	@RequestMapping(method = RequestMethod.GET, value="/all")
	public List<rocktypes> findAllRockTypes(){
		return rockRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/rock={type}")
	public String findRockType(@PathVariable("type") String type){
		String output = "";
		if(type.equalsIgnoreCase("igneous")){
			output = rockRepo.findOne("m-rocks").getIgneous();
		}else if(type.equalsIgnoreCase("sedimentary")){
			output = rockRepo.findOne("m-rocks").getSedimentary();
		}else if(type.equalsIgnoreCase("metamorphic")){
			output = rockRepo.findOne("m-rocks").getMetamorphic();
		}else{
			output = "Please enter rock type as igneous / sedimentary / metamorphic";
		}
		
		return output;
		
	}
}
