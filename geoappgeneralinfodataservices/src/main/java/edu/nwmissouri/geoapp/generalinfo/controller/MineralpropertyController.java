package edu.nwmissouri.geoapp.generalinfo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.nwmissouri.geoapp.generalinfo.model.mineralproperties;
import edu.nwmissouri.geoapp.generalinfo.repository.MineralpropertyRepo;
//import io.undertow.attribute.RequestMethodAttribute;

@Controller
@RestController
@RequestMapping("/ds/geoapp/general-info/mineralproperty")

public class MineralpropertyController {
	@Autowired
	private MineralpropertyRepo minrepo;
	
	
	@RequestMapping(method = RequestMethod.GET, value="/all")
	public List<mineralproperties> findAllMinerals(){
		return minrepo.findAll();
	}
	
		
}
