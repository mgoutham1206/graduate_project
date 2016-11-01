package edu.nwmissouri.geoapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

//Created by sirish

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.nwmissouri.geoapp.generalinfo.controller.MineralController;
import edu.nwmissouri.geoapp.generalinfo.model.metallic;
import edu.nwmissouri.geoapp.generalinfo.model.nonmetallic;
import edu.nwmissouri.geoapp.generalinfo.repository.MineralRepository;
import edu.nwmissouri.geoapp.generalinfo.repository.NonMetallicRepository;

@RestController
@RequestMapping("/GeoApp/client/generalinfo/identification")
public class GeneralInfoController {
	
	
	//viewing without model
//	@RequestMapping(method = RequestMethod.GET, value = "/")
//	public ModelAndView showGeneralInfoIdentification(){
//		return new ModelAndView("GeoAppStudentHome1");
//	}
	
	
	
    //viewing without model
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView showGeneralInfoHome(){
        return new ModelAndView("GeneralInfoIdentification");    
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/inst")
    public ModelAndView showGeneralInfoHomeInst(){
        return new ModelAndView("GeneralInfoIdentificationInst");    
    }
	
	@Autowired
	private MineralRepository metallicRepo;
	
	 //Updated by sirish
	@RequestMapping(value="/metallic/id")
		public ModelAndView getMetallicById(){
			List<String> minerals = new ArrayList<>();
			ModelMap metals = new ModelMap();
			Iterator<metallic> iter = metallicRepo.findAll().iterator();
			while (iter.hasNext()) {
				//System.out.println(iterator.next());
				minerals.add(iter.next().getMineral());
			}
			metals.addAttribute("metals", minerals);		
			return new ModelAndView("GeneralInfoIdentification", metals);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/metallic/table")
	public ModelAndView metallicTableView(){
		
		return  new ModelAndView("GeneralInfoMetallicTable");
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/metallic/table/all")
	public List<metallic> metallicTable(){
		
		return metallicRepo.findAll();
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/nonmetallic/table/all")
	public List<nonmetallic> nonmetallicTable(){
		
		return nmRepo.findAll();
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/metallic/allstreak")
	public Set<String> findAllMetallicSteak(){
		Set<String> streak = new HashSet<String>();
		Iterator<metallic> iter = metallicRepo.findAll().iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			streak.addAll(iter.next().getStreakcolor());
		}
		return streak;
	}
	
		
	@RequestMapping(method = RequestMethod.GET, value = "/metallic/allcolor")
	public Set<String> findAllMetallicColor(){
		Set<String> color = new HashSet<String>();
		Iterator<metallic> iter = metallicRepo.findAll().iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			color.addAll(iter.next().getColor());
		}
		return color;
	}
	
	//Updated by Vinay
	@RequestMapping(method = RequestMethod.GET, value="/metallic/nocleavage")
	public List<String> findByMetallicCleavage(){
		List<String> minerals = new ArrayList<>();
		ModelMap mc = new ModelMap();
		Iterator<metallic> iter = metallicRepo.findByCleavage("no").iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next().getMineral());
		}
		mc.addAttribute("metalCleavage", minerals);
		return minerals;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/metallic/sets={sets},{angle}")
	public List<String> findByMetallicGeometry(@PathVariable("sets") int sets, @PathVariable("angle") int angle){
		List<String> minerals = new ArrayList<>();
		Iterator<metallic> iter = metallicRepo.findByCleavage("yes").iterator();
		metallic m = new metallic();
		while (iter.hasNext()) {
			m= iter.next();
			if(sets>=4){
				if(angle==90){
					if(m.getSets()>=4 && (m.getAngle()==90 || m.getAngle()==180)){
						minerals.add(m.getMineral());
					}
				}else if(angle==60){
					if(m.getSets()>=4 && (m.getAngle()==60 || m.getAngle()==120)){
						minerals.add(m.getMineral());
					}
				}else if(m.getSets()>=4 && (m.getAngle()!=60 && m.getAngle()!=120)){
					minerals.add(m.getMineral());
				}
				
			}else if(sets<4){
				if(angle==90){
					if(m.getSets()==sets && (m.getAngle()==90 || m.getAngle()==180)){
						minerals.add(m.getMineral());
					}
				}else if(angle==60){
					if(m.getSets()==sets && (m.getAngle()==60 || m.getAngle()==120)){
						minerals.add(m.getMineral());
					}
				}else if(m.getSets()==sets && (m.getAngle()!=60 && m.getAngle()!=120)){
					minerals.add(m.getMineral());
				}
				
			}
			
		}
		return minerals;
	}
	
	//Implemented by sirish
	//Updated by Vinay
	//updated by sirish on 02/16/2016
	@RequestMapping(method = RequestMethod.GET, value = "/metallic/streak={streakcolor}")
	public Set<String> findByMetallicStreak(@PathVariable List<String> streakcolor){
		Set<String> metalStreak = new HashSet<String>();
		for(String s : streakcolor){
			System.out.println(s);
			
				Iterator<metallic> iterator = metallicRepo.findByStreakcolor(s).iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					metalStreak.add(iterator.next().getMineral());
				}			
		}
		return metalStreak;
	}
	
	//Implemented by sirish on 2/16/2016
	@RequestMapping(method = RequestMethod.GET, value = "/metallic/color={color}")
	public Set<String> findByMetallicColor(@PathVariable List<String> color){
		Set<String> metalColor = new HashSet<String>();
		for(String s : color){
			System.out.println(s);
			Iterator<metallic> iterator = metallicRepo.findByColor(s).iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					metalColor.add(iterator.next().getMineral());
				}			
		}
		return metalColor;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/metallic/hardness={range}")
	public List<String> findByMetallicHardness(@PathVariable("range") String range){
		List<metallic> hardness = new ArrayList<metallic>();
		List<String> minerals = new ArrayList<>();
		List<String> check = new ArrayList<>();
		if(range.equalsIgnoreCase("range0")){
			hardness = metallicRepo.findByHardnessRange0();
		}else if(range.equalsIgnoreCase("range1")){
			hardness = metallicRepo.findByHardnessRange1();
		}else if(range.equalsIgnoreCase("range2")){
			hardness = metallicRepo.findByHardnessRange2();
		}else if(range.equalsIgnoreCase("range3")){
			hardness = metallicRepo.findByHardnessRange3();
		}else if(range.equalsIgnoreCase("range4")){
			hardness = metallicRepo.findByHardnessRange4();
		}else if(range.equalsIgnoreCase("range5")){
			hardness = metallicRepo.findByHardnessRange5();
		}
		Iterator<metallic> iter = hardness.iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next().getMineral());
		}
		if(minerals.isEmpty()){
			check.add("No minerals available in this range");
			return check;
		}else{
			return minerals;
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/metallic/sg={range}")
	public List<String> findByMetallicSg(@PathVariable("range") String range){
		List<metallic> sg = new ArrayList<metallic>();
		List<String> minerals = new ArrayList<>();
		List<String> check = new ArrayList<>();
		ModelMap metalSg = new ModelMap();
		if(range.equalsIgnoreCase("range0")){
			sg = metallicRepo.findBySgRange0();
		}else if(range.equalsIgnoreCase("range1")){
			sg = metallicRepo.findBySgRange1();
		}else if(range.equalsIgnoreCase("range2")){
			sg = metallicRepo.findBySgRange2();
		}else if(range.equalsIgnoreCase("range3")){
			sg = metallicRepo.findBySgRange3();
		}else if(range.equalsIgnoreCase("range4")){
			sg = metallicRepo.findBySgRange4();
		}else if(range.equalsIgnoreCase("range5")){
			sg = metallicRepo.findBySgRange5();
		}		
		Iterator<metallic> iter = sg.iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next().getMineral());
		}
		if(minerals.isEmpty()){
			check.add("No minerals available in this range");
			return check;
		}else{
			return minerals;
		}
	}
	
	//----------------------------------non-metallic---------------------------------
	
	@Autowired
	private NonMetallicRepository nmRepo;
	
	 //Updated by sirish
	@RequestMapping(value="/nonmetallic/id")
		public ModelAndView getNonMetallicById(){
			List<String> minerals = new ArrayList<>();
			ModelMap nonMetals = new ModelMap();
			Iterator<nonmetallic> iter = nmRepo.findAll().iterator();
			while (iter.hasNext()) {
				//System.out.println(iterator.next());
				minerals.add(iter.next().getMineral());
			}
			nonMetals.addAttribute("nonmetals", minerals);		
			return new ModelAndView("GeneralInfoIdentification", nonMetals);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/nonmetallic/allstreak")
	public Set<String> findAllNonMetallicSteak(){
		Set<String> streak = new HashSet<String>();
		Iterator<nonmetallic> iter = nmRepo.findAll().iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			streak.addAll(iter.next().getStreakcolor());
		}
		return streak;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/nonmetallic/allcolor")
	public Set<String> findAllNonMetallicColor(){
		Set<String> color = new HashSet<String>();
		Iterator<nonmetallic> iter = nmRepo.findAll().iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			color.addAll(iter.next().getColor());
		}
		return color;
	}
	//Updated by Vinay
	@RequestMapping(method = RequestMethod.GET, value="/nonmetallic/nocleavage")
	public List<String> findByNonMetallicCleavage(){
		List<String> minerals = new ArrayList<>();
		ModelMap nmc = new ModelMap();
		Iterator<nonmetallic> iter = nmRepo.findByCleavage("no").iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next().getMineral());
		}
		nmc.addAttribute("nonMetallicCleavage", minerals);
		return minerals;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/nonmetallic/sets={sets},{angle}")
	public List<String> findByNonMetallicGeometry(@PathVariable("sets") int sets, @PathVariable("angle") int angle){
	
		List<String> minerals = new ArrayList<>();
		Iterator<nonmetallic> iter = nmRepo.findByCleavage("yes").iterator();
		nonmetallic nm = new nonmetallic();
		while (iter.hasNext()) {
			nm= iter.next();
			if(sets>=4){
				if(angle==90){
					if(nm.getSets()>=4 && (nm.getAngle()==90 || nm.getAngle()==180)){
						minerals.add(nm.getMineral());
					}
				}else if(angle==60){
					if(nm.getSets()>=4 && (nm.getAngle()==60 || nm.getAngle()==120)){
						minerals.add(nm.getMineral());
					}
				}else if(nm.getSets()>=4 && (nm.getAngle()!=60 && nm.getAngle()!=120)&&(nm.getAngle()!=90 && nm.getAngle()!=180)){
					minerals.add(nm.getMineral());
				}
				
			}else if(sets<4){
				if(angle==90){
					if(nm.getSets()==sets && (nm.getAngle()==90 || nm.getAngle()==180)){
						minerals.add(nm.getMineral());
					}
				}else if(angle==60){
					if(nm.getSets()==sets && (nm.getAngle()==60 || nm.getAngle()==120)){
						minerals.add(nm.getMineral());
					}
				}else if(nm.getSets()==sets && (nm.getAngle()!=60 && nm.getAngle()!=120)&&(nm.getAngle()!=90 && nm.getAngle()!=180)){
					minerals.add(nm.getMineral());
				}
				
			}
			
		}
		return minerals;
	}
	
	//Implemented by sirish
	//Updated by Vinay
	//updated by sirish on 02/16/2016
	@RequestMapping(method = RequestMethod.GET, value = "/nonmetallic/streak={streakcolor}")
	public Set<String> findByNonMetallicStreak(@PathVariable List<String> streakcolor){
		Set<String> nonMetalStreak = new HashSet<String>();
		for(String s : streakcolor){
			System.out.println(s);
			
				Iterator<nonmetallic> iterator = nmRepo.findByStreakcolor(s).iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					nonMetalStreak.add(iterator.next().getMineral());
				}			
		}
		return nonMetalStreak;
	}
	
	//Implemented by sirish on 2/16/2016
	@RequestMapping(method = RequestMethod.GET, value = "/nonmetallic/color={color}")
	public Set<String> findByNonMetallicColor(@PathVariable List<String> color){
		Set<String> nonMetalColor = new HashSet<String>();
		for(String s : color){
			System.out.println(s);
			
				Iterator<nonmetallic> iterator = nmRepo.findByColor(s).iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					nonMetalColor.add(iterator.next().getMineral());
				}			
		}
		return nonMetalColor;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/nonmetallic/hardness={range}")
	public List<String> findByNonMetallicHardness(@PathVariable("range") String range){
		List<nonmetallic> hardness = new ArrayList<nonmetallic>();
		List<String> minerals = new ArrayList<>();
		List<String> check = new ArrayList<>();
		if(range.equalsIgnoreCase("range0")){
			hardness = nmRepo.findByHardnessRange0();
		}else if(range.equalsIgnoreCase("range1")){
			hardness = nmRepo.findByHardnessRange1();
		}else if(range.equalsIgnoreCase("range2")){
			hardness = nmRepo.findByHardnessRange2();
		}else if(range.equalsIgnoreCase("range3")){
			hardness = nmRepo.findByHardnessRange3();
		}else if(range.equalsIgnoreCase("range4")){
			hardness = nmRepo.findByHardnessRange4();
		}else if(range.equalsIgnoreCase("range5")){
			hardness = nmRepo.findByHardnessRange5();
		}
		
		Iterator<nonmetallic> iter = hardness.iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next().getMineral());
		}
		if(minerals.isEmpty()){
			check.add("No minerals available in this range");
			return check;
		}else{
			return minerals;
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/nonmetallic/sg={range}")
	public List<String> findBySg(@PathVariable("range") String range){
		List<nonmetallic> sg = new ArrayList<nonmetallic>();
		List<String> minerals = new ArrayList<>();
		List<String> check = new ArrayList<>();
		ModelMap nonMetalSg = new ModelMap();
		if(range.equalsIgnoreCase("range0")){
			sg = nmRepo.findBySgRange0();
		}else if(range.equalsIgnoreCase("range1")){
			sg = nmRepo.findBySgRange1();
		}else if(range.equalsIgnoreCase("range2")){
			sg = nmRepo.findBySgRange2();
		}else if(range.equalsIgnoreCase("range3")){
			sg = nmRepo.findBySgRange3();
		}else if(range.equalsIgnoreCase("range4")){
			sg = nmRepo.findBySgRange4();
		}else if(range.equalsIgnoreCase("range5")){
			sg = nmRepo.findBySgRange5();
		}
		Iterator<nonmetallic> iter = sg.iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next().getMineral());
		}
		if(minerals.isEmpty()){
			check.add("No minerals available in this range");
			return check;
		}else{
			return minerals;
		}
	}
	
}
