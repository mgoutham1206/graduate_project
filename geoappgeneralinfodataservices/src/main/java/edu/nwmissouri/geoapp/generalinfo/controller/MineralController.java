package edu.nwmissouri.geoapp.generalinfo.controller;

import edu.nwmissouri.geoapp.generalinfo.model.*;
import edu.nwmissouri.geoapp.generalinfo.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




@Controller
@RestController
@RequestMapping("/ds/geoapp/general-info/identification/metallic")

public class MineralController {

	@Autowired
	private MineralRepository repo;
	

//	@RequestMapping(method = RequestMethod.GET, value="/id={_id}")
//	  public metallic getMineralsDetails(@PathVariable("_id") String id){
//		//Query que = new Query();
//	    return repo.findOne(id);
//	    
//	  }
//	
	@RequestMapping(method = RequestMethod.GET, value="/all")
	public List<metallic> mineralsAll(){
		return repo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/streaks")
	public Set<String> findAllSteak(){
		Set<String> streak = new HashSet<String>();
		Iterator<metallic> iter = repo.findAll().iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			streak.addAll(iter.next().getStreakcolor());
		}
		return streak;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/streak={streakcolor}")
	public Set<metallic> findByMetallicStreak(@PathVariable List<String> streakcolor){
		Set<metallic> metalStreak = new HashSet<>();
		for(String s : streakcolor){
			System.out.println(s);
			
				Iterator<metallic> iterator = repo.findByStreakcolor(s).iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					metalStreak.add(iterator.next());
				}			
		}
		return metalStreak;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/colors")
	public Set<String> findAllColor(){
		Set<String> color = new HashSet<String>();
		Iterator<metallic> iter = repo.findAll().iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			color.addAll(iter.next().getColor());
		}
		return color;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/color={color}")
	public Set<metallic> findByMetallicColor(@PathVariable List<String> color){
		Set<metallic> metalColor = new HashSet<>();
		for(String s : color){
			System.out.println(s);
			Iterator<metallic> iterator = repo.findByColor(s).iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					metalColor.add(iterator.next());
				}			
		}
		return metalColor;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/hardness={range}")
	public List<String> findByMetallicHardness(@PathVariable("range") String range){
		List<metallic> hardness = new ArrayList<metallic>();
		List<String> minerals = new ArrayList<>();
		if(range.equalsIgnoreCase("range0")){
			hardness = repo.findByHardnessRange0();
		}else if(range.equalsIgnoreCase("range1")){
			hardness = repo.findByHardnessRange1();
		}else if(range.equalsIgnoreCase("range2")){
			hardness = repo.findByHardnessRange2();
		}else if(range.equalsIgnoreCase("range3")){
			hardness = repo.findByHardnessRange3();
		}else if(range.equalsIgnoreCase("range4")){
			hardness = repo.findByHardnessRange4();
		}else if(range.equalsIgnoreCase("range5")){
			hardness = repo.findByHardnessRange5();
		}
		Iterator<metallic> iter = hardness.iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next().getMineral());
		}
		return minerals;
		
	}
	
	
	public Set<metallic> metallicHardness(String range){
		List<metallic> hardness = new ArrayList<metallic>();
		Set<metallic> minerals = new HashSet<>();
		if(range.equalsIgnoreCase("range0")){
			hardness = repo.findByHardnessRange0();
		}else if(range.equalsIgnoreCase("range1")){
			hardness = repo.findByHardnessRange1();
		}else if(range.equalsIgnoreCase("range2")){
			hardness = repo.findByHardnessRange2();
		}else if(range.equalsIgnoreCase("range3")){
			hardness = repo.findByHardnessRange3();
		}else if(range.equalsIgnoreCase("range4")){
			hardness = repo.findByHardnessRange4();
		}else if(range.equalsIgnoreCase("range5")){
			hardness = repo.findByHardnessRange5();
		}
		Iterator<metallic> iter = hardness.iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next());
		}
		return minerals;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/sg={range}")
	public List<String> findByMetallicSg(@PathVariable("range") String range){
		List<metallic> sg = new ArrayList<metallic>();
		List<String> minerals = new ArrayList<>();
		
		if(range.equalsIgnoreCase("range0")){
			sg = repo.findBySgRange0();
		}else if(range.equalsIgnoreCase("range1")){
			sg = repo.findBySgRange1();
		}else if(range.equalsIgnoreCase("range2")){
			sg = repo.findBySgRange2();
		}else if(range.equalsIgnoreCase("range3")){
			sg = repo.findBySgRange3();
		}else if(range.equalsIgnoreCase("range4")){
			sg = repo.findBySgRange4();
		}else if(range.equalsIgnoreCase("range5")){
			sg = repo.findBySgRange5();
		}
		Iterator<metallic> iter = sg.iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next().getMineral());
		}
		return minerals;
	}
	
	
	public Set<metallic> metallicSg(String range){
		List<metallic> sg = new ArrayList<metallic>();
		Set<metallic> minerals = new HashSet<>();
		
		if(range.equalsIgnoreCase("range0")){
			sg = repo.findBySgRange0();
		}else if(range.equalsIgnoreCase("range1")){
			sg = repo.findBySgRange1();
		}else if(range.equalsIgnoreCase("range2")){
			sg = repo.findBySgRange2();
		}else if(range.equalsIgnoreCase("range3")){
			sg = repo.findBySgRange3();
		}else if(range.equalsIgnoreCase("range4")){
			sg = repo.findBySgRange4();
		}else if(range.equalsIgnoreCase("range5")){
			sg = repo.findBySgRange5();
		}
		Iterator<metallic> iter = sg.iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			minerals.add(iter.next());
		}
		return minerals;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/cleavage=no")
		public List<metallic> findByCleavage(){
			return repo.findByCleavage("no");
		}
		
		public Set<metallic> cleavage(String cleavage, int sets, int angle){
			Set<metallic> minerals = new HashSet<>();
			
			if(cleavage.equalsIgnoreCase("no")){
				Iterator<metallic> iterator = repo.findByCleavage("no").iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					minerals.add(iterator.next());
				}
			}else{
				Iterator<metallic> iter = repo.findByCleavage("yes").iterator();
				metallic m = new metallic();
				while (iter.hasNext()) {
					m= iter.next();
					if(sets>=4){
						if(angle==90){
							if(m.getSets()>=4 && (m.getAngle()==90 || m.getAngle()==180)){
								minerals.add(m);
							}
						}else if(angle==60){
							if(m.getSets()>=4 && (m.getAngle()==60 || m.getAngle()==120)){
								minerals.add(m);
							}
						}else if(m.getSets()>=4 && (m.getAngle()!=60 && m.getAngle()!=120)){
							minerals.add(m);
						}
						
					}else if(sets<4){
						if(angle==90){
							if(m.getSets()==sets && (m.getAngle()==90 || m.getAngle()==180)){
								minerals.add(m);
							}
						}else if(angle==60){
							if(m.getSets()==sets && (m.getAngle()==60 || m.getAngle()==120)){
								minerals.add(m);
							}
						}else if(m.getSets()==sets && (m.getAngle()!=60 && m.getAngle()!=120)){
							minerals.add(m);
						}
						
					}
					
				}
			}
			return minerals;
		}
		
	
	
//	
	
//	@RequestMapping(method = RequestMethod.GET, value = "/streak={streakcolor}")
//	public List<metallic> findByStreakColor(@PathVariable("streakcolor") String streakcolor){
//		return repo.findByStreakcolor(streakcolor);
//	}
	
	
	
	
//	@RequestMapping(method = RequestMethod.GET, value = "/hardness/in-range=2.2-3.1")
//	public List<metallic> findByHardnessRange1(){
//	
//		return repo.findByHardnessRange1();
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/hardness/in-range=3.2-4.9")
//	public List<metallic> findByHardnessRange2(){
//	
//		return repo.findByHardnessRange2();
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/hardness/in-range=5.0-7.5")
//	public List<metallic> findByHardnessRange3(){
//	
//		return repo.findByHardnessRange3();
//	}
	
	
	
//	@RequestMapping(method = RequestMethod.GET, value = "/sg=medium")
//	public List<metallic> findBySgRange1(){
//	
//		return repo.findBySgRange1();
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/sg=high")
//	public List<metallic> findBySgRange2(){
//	
//		return repo.findBySgRange2();
//	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/geometric={sets},{angle}")
	
		public List<String> findByMetallicGeometry(@PathVariable("sets") int sets, @PathVariable("angle") int angle){
			List<String> minerals = new ArrayList<>();
			Iterator<metallic> iter = repo.findByCleavage("yes").iterator();
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

	@RequestMapping(method = RequestMethod.GET, value = "/final11={streak},{hmin},{hmax},{sgmin},{sgmax},{cleavage},{sets},{angle}")
	public Set<String> finalList(@PathVariable("streak") List<String> streakcolor, @PathVariable("hmin") double hmin, @PathVariable("hmax") double hmax, @PathVariable("sgmin") double sgmin, @PathVariable("sgmax") double sgmax, @PathVariable("cleavage") String cleavage, @PathVariable("sets") int sets, @PathVariable("angle") int angle){
		
		Set<metallic> metallicList = new HashSet<>();
		Set<String> finalList = new HashSet<>();
		metallicList.addAll(findByMetallicStreak(streakcolor));
		//metallicList = findByMetallicStreak(streakcolor);
		String range = "";
		if(hmin>=0.0 && hmax <=2.5){
			range = "range0";
		}else if(hmin>=2.5 && hmax <=3.2){
			range = "range1";
		}else if(hmin>=3.2 && hmax <=5.0){
			range = "range2";
		}else if(hmin>=5.0 && hmax <=5.5){
			range = "range3";
		}else if(hmin>=5.5 && hmax <=7.0){
			range = "range4";
		}else if(hmin>=7.0 && hmax <=10.0){
			range = "range5";
		}
		System.out.println("hardness: "+range);
		metallicList.addAll(metallicHardness(range));
		String rangeSg="";
		if(sgmin>=0.0 && sgmax<=2.5){
			rangeSg = "range0";
		}else if(sgmin>=2.5 && sgmax<=3.5){
			rangeSg = "range1";
		}else if(sgmin>=3.5 && sgmax<=4.5){
			rangeSg = "range2";
		}else if(sgmin>=4.5 && sgmax<=7.0){
			rangeSg = "range3";
		}else if(sgmin>=7.0 && sgmax<=10.0){
			rangeSg = "range4";
		}else if(sgmin>=10.0 && sgmax<=20.0){
			rangeSg = "range5";
		}
		System.out.println("sg :"+rangeSg);
		metallicList.addAll(metallicSg(rangeSg));
		metallicList.addAll(cleavage(cleavage, sets, angle));
		for(metallic m : metallicList){
			finalList.add(m.getMineral());
		}
		System.out.println(finalList.toString());
		return finalList;		
	}


	@RequestMapping(method = RequestMethod.GET, value = "/final={streak},{hmin},{hmax},{sgmin},{sgmax},{cleavage},{sets},{angle}")
	
	public Set<metallic> findFinalList(@PathVariable("streak") List<String> streakcolor, @PathVariable("hmin") double hmin, @PathVariable("hmax") double hmax, @PathVariable("sgmin") double sgmin, @PathVariable("sgmax") double sgmax, @PathVariable("cleavage") String cleavage, @PathVariable("sets") int sets, @PathVariable("angle") int angle){
		
//		System.out.println(streakcolor);
//		System.out.println(hmin);
//		System.out.println(hmax);System.out.println(sgmin);System.out.println(sgmax);
		
		
		Set<metallic> metallicList = new HashSet<>();
		Set<metallic> metallicList2 = new HashSet<>();
		Set<metallic> metallicList3 = new HashSet<>();
		for(String s : streakcolor){
			//System.out.println(s);
			
				Iterator<metallic> iterator = repo.findByStreakcolor(s).iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					metallicList.add(iterator.next());
				}			
		}
		System.out.println("Streak : " +metallicList);
		
		for(metallic m : metallicList){
			if(m.getHardnessmin()>=hmin && m.getHardnessmax()<=hmax){
				metallicList2.add(m);
			}
		}
		System.out.println("hardness: " +metallicList2);
		
		for(metallic m : metallicList2){
			if(m.getSgmin()>=sgmin && m.getSgmax()<=sgmax){
				metallicList3.add(m);
			}
		}
		System.out.println("sg: " +metallicList3);
		
		Set<metallic> metallicList4 = new HashSet<>();
		System.out.println(cleavage);System.out.println(sets);System.out.println(angle);
		
		for(metallic m : metallicList3){
			System.out.println("here1-");
			if(cleavage.equalsIgnoreCase("no") || cleavage.equalsIgnoreCase("not present")){
				System.out.println("here0");
				if(m.getCleavage().equalsIgnoreCase("no")){
					metallicList4.add(m);
					System.out.println("here1");
					System.out.println("no" +metallicList4);
				}
			}else if(cleavage.equalsIgnoreCase("yes")){
				if(m.getCleavage().equalsIgnoreCase("yes")){
					if(m.getSets() == sets && m.getAngle() == angle){
						metallicList4.add(m);
					}
				}
			}
			
		}
		System.out.println(metallicList4);
		return metallicList4;
		
	}
}
