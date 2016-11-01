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
@RequestMapping("/ds/geoapp/general-info/identification/non-metallic")
public class NonMetallicController {

	@Autowired
	private NonMetallicRepository nmRepo;
	
	
//	@RequestMapping(method = RequestMethod.GET, value="/all")
//	public List<nonmetallic> mineralsAll(){
//		return nmRepo.findAll();
//	}
	
	@RequestMapping(method = RequestMethod.GET, value="/all")
	public List<String> mineralsAll(){
		List<String> metals = new ArrayList<>();
		Iterator<nonmetallic> iter = nmRepo.findAll().iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			metals.add(iter.next().getMineral());
		}
		return metals;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/streaks")
	public Set<String> findAllSteak(){
		Set<String> streak = new HashSet<String>();
		Iterator<nonmetallic> iter = nmRepo.findAll().iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			streak.addAll(iter.next().getStreakcolor());
		}
		return streak;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/streak={streakcolor}")
	public Set<nonmetallic> findByNonMetallicStreak(@PathVariable List<String> streakcolor){
		Set<nonmetallic> nonMetalStreak = new HashSet<>();
		for(String s : streakcolor){
			System.out.println(s);
			
				Iterator<nonmetallic> iterator = nmRepo.findByStreakcolor(s).iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					nonMetalStreak.add(iterator.next());
				}			
		}
		return nonMetalStreak;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/colors")
	public Set<String> findAllColor(){
		Set<String> color = new HashSet<String>();
		Iterator<nonmetallic> iter = nmRepo.findAll().iterator();
		while (iter.hasNext()) {
			//System.out.println(iterator.next());
			color.addAll(iter.next().getColor());
		}
		return color;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/color={color}")
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/hardness={range}")
	public List<String> findByNonMetallicHardness(@PathVariable("range") String range){
		List<nonmetallic> hardness = new ArrayList<nonmetallic>();
		List<String> minerals = new ArrayList<>();
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
		return minerals;
		
	}
	
		
	@RequestMapping(method = RequestMethod.GET, value = "/sg={range}")
	public List<String> findBySg(@PathVariable("range") String range){
		List<nonmetallic> sg = new ArrayList<nonmetallic>();
		List<String> minerals = new ArrayList<>();
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
		return minerals;
	}
	

	
	@RequestMapping(method = RequestMethod.GET, value="/cleavage=no")
		public List<nonmetallic> findByCleavage(){
			return nmRepo.findByCleavage("no");
		}
		
	
		
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/geometric={sets},{angle}")
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
				}else if(nm.getSets()>=4 && (nm.getAngle()!=60 && nm.getAngle()!=120)){
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
				}else if(nm.getSets()==sets && (nm.getAngle()!=60 && nm.getAngle()!=120)){
					minerals.add(nm.getMineral());
				}
				
			}
			
		}
		return minerals;
	}
	
	
	public Set<nonmetallic> nonmetallicHardness(String range){
		List<nonmetallic> hardness = new ArrayList<nonmetallic>();
		Set<nonmetallic> minerals = new HashSet<>();
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
			minerals.add(iter.next());
		}
		return minerals;
		
	}
	
	public Set<nonmetallic> nonmetallicSg(String range){
		List<nonmetallic> sg = new ArrayList<nonmetallic>();
		Set<nonmetallic> minerals = new HashSet<>();
		
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
			minerals.add(iter.next());
		}
		return minerals;
	}
	
	public Set<nonmetallic> cleavage(String cleavage, int sets, int angle){
		Set<nonmetallic> minerals = new HashSet<>();
		
		if(cleavage.equalsIgnoreCase("no")){
			Iterator<nonmetallic> iterator = nmRepo.findByCleavage("no").iterator();
			while (iterator.hasNext()) {
				//System.out.println(iterator.next());
				minerals.add(iterator.next());
			}
		}else{
			Iterator<nonmetallic> iter = nmRepo.findByCleavage("yes").iterator();
			nonmetallic m = new nonmetallic();
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/final11={streak},{hmin},{hmax},{sgmin},{sgmax},{cleavage},{sets},{angle}")
	public Set<String> finalList(@PathVariable("streak") List<String> streakcolor, @PathVariable("hmin") double hmin, @PathVariable("hmax") double hmax, @PathVariable("sgmin") double sgmin, @PathVariable("sgmax") double sgmax, @PathVariable("cleavage") String cleavage, @PathVariable("sets") int sets, @PathVariable("angle") int angle){
		
		
		Set<nonmetallic> nonmetallicList = new HashSet<>();
		Set<String> finalList = new HashSet<>();
		nonmetallicList.addAll(findByNonMetallicStreak(streakcolor));
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
		nonmetallicList.addAll(nonmetallicHardness(range));
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
		nonmetallicList.addAll(nonmetallicSg(rangeSg));
		nonmetallicList.addAll(cleavage(cleavage, sets, angle));
		for(nonmetallic m : nonmetallicList){
			finalList.add(m.getMineral());
		}
		System.out.println(finalList.toString());
		return finalList;		
	}

	
@RequestMapping(method = RequestMethod.GET, value = "/final={streak},{hmin},{hmax},{sgmin},{sgmax},{cleavage},{sets},{angle}")
	
	public Set<nonmetallic> findFinalList(@PathVariable("streak") List<String> streakcolor, @PathVariable("hmin") double hmin, @PathVariable("hmax") double hmax, @PathVariable("sgmin") double sgmin, @PathVariable("sgmax") double sgmax, @PathVariable("cleavage") String cleavage, @PathVariable("sets") int sets, @PathVariable("angle") int angle){
		
		System.out.println(streakcolor);
		System.out.println(hmin);
		System.out.println(hmax);System.out.println(sgmin);System.out.println(sgmax);
		
		
		Set<nonmetallic> metallicList = new HashSet<>();
		Set<nonmetallic> metallicList2 = new HashSet<>();
		Set<nonmetallic> metallicList3 = new HashSet<>();
		for(String s : streakcolor){
			//System.out.println(s);
			
				Iterator<nonmetallic> iterator = nmRepo.findByStreakcolor(s).iterator();
				while (iterator.hasNext()) {
					//System.out.println(iterator.next());
					metallicList.add(iterator.next());
				}			
		}
		System.out.println("Streak" +metallicList);
		
		for(nonmetallic m : metallicList){
			if(m.getHardnessmin()>=hmin && m.getHardnessmax()<=hmax){
				metallicList2.add(m);
			}
			
		}
		System.out.println("hardness" +metallicList2);
		for(nonmetallic m : metallicList2){
			if(m.getSgmin()>=sgmin && m.getSgmax()<=sgmax){
				metallicList3.add(m);
			}
			
		}
		System.out.println("sg" +metallicList3);
		Set<nonmetallic> metallicList4 = new HashSet<>();
		System.out.println(cleavage);System.out.println(sets);System.out.println(angle);
		for(nonmetallic m : metallicList3){
			System.out.println("here1-");
			if(cleavage.equalsIgnoreCase("no")){
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
