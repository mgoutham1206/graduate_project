package edu.nwmissouri.geoapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;


import edu.nwmissouri.geoapp.GeoAppApplication;
import edu.nwmissouri.geoapp.generalinfo.model.metallic;
import edu.nwmissouri.geoapp.generalinfo.model.mineralproperties;
import edu.nwmissouri.geoapp.generalinfo.model.minerals;
import edu.nwmissouri.geoapp.generalinfo.model.rocktypes;
import edu.nwmissouri.geoapp.generalinfo.repository.MineralTypesRepository;
//import edu.nwmissouri.geoapp.generalinfo.repository.MineralRepository;
import edu.nwmissouri.geoapp.generalinfo.repository.MineralpropertyRepo;
import edu.nwmissouri.geoapp.generalinfo.repository.RockTypesRepository;

@RestController
@RequestMapping("/client/generalinfo/")

public class mineralproperty {
	@Autowired
	private MineralpropertyRepo minrepo;
	
	
	 //viewing without model
    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public ModelAndView showGeneralInfoHome(){
        return new ModelAndView("GeneralInfoIdentification");    
    }
    
	@RequestMapping(method = RequestMethod.GET, value="/property/id")
	  public List<mineralproperties> getMineralsDetails(){
		//Query que = new Query();
		
	    return minrepo.findAll();
	    
	  }
	  
	  @RequestMapping(method = RequestMethod.GET, value="/properties")
	  public ModelAndView getAllProperties(){
		  return new ModelAndView("Generalinformationmineralproperty");
	  }
	  
	  @RequestMapping(method = RequestMethod.GET, value="/properties/inst")
	  public ModelAndView getAllPropertiesInst(){
		  return new ModelAndView("GeneralInfoMineralPropertyInst");
	  }
	  
	  @RequestMapping(value="/property/luster")
		public ModelAndView getMineralsByluster(){
			//String prop = "";
			List<String> prop = new ArrayList<>();
			ModelMap genModel = new ModelMap();
			
			System.out.println(genModel);
			Iterator<mineralproperties> iter = minrepo.findAll().iterator();
			while (iter.hasNext()) {
				prop.add(iter.next().getLuster());
			}
			
			
			genModel.addAttribute("luster", prop);		
			return new ModelAndView("Generalinformationmineralproperty", genModel);
		
	}
	  @RequestMapping(value="/property/Cleavage")
			public ModelAndView getMineralsByCleavage(){
				//String prop = "";
				List<String> prop = new ArrayList<>();
				ModelMap genModel = new ModelMap();
				
				System.out.println(genModel);
				Iterator<mineralproperties> iter = minrepo.findAll().iterator();
				while (iter.hasNext()) {
					prop.add(iter.next().getCleavage());
				}
				
				
				genModel.addAttribute("Cleavage", prop);		
				return new ModelAndView("Generalinformationmineralproperty", genModel);
			
		}
	  @RequestMapping(method = RequestMethod.GET, value="/property/Hardness")
		public ModelAndView getMineralsByHardness(){
			//String prop = "";
			List<String> prop = new ArrayList<>();
			ModelMap genModel = new ModelMap();
			
			System.out.println(genModel);
			Iterator<mineralproperties> iter = minrepo.findAll().iterator();
			while (iter.hasNext()) {
				prop.add(iter.next().getHardness());
			}
			
			
			genModel.addAttribute("Hardness", prop);		
			return new ModelAndView("Generalinformationmineralproperty", genModel);
		
	}
	  @RequestMapping(value="/property/Streak")
		public ModelAndView getMineralsByStreak(){
			//String prop = "";
			List<String> prop = new ArrayList<>();
			ModelMap genModel = new ModelMap();
			
			System.out.println(genModel);
			Iterator<mineralproperties> iter = minrepo.findAll().iterator();
			while (iter.hasNext()) {
				prop.add(iter.next().getHardness());
			}
			
			
			genModel.addAttribute("streak", prop);		
			return new ModelAndView("Generalinformationmineralproperty", genModel);
		
	}
	  @RequestMapping(value="/property/fracture")
		public ModelAndView getMineralsByfracture(){
			
			List<String> prop = new ArrayList<>();
			ModelMap genModel = new ModelMap();
			
			System.out.println(genModel);
			Iterator<mineralproperties> iter = minrepo.findAll().iterator();
			while (iter.hasNext()) {
				prop.add(iter.next().getFracture());
			}
			
			
			genModel.addAttribute("fracture", prop);		
			return new ModelAndView("Generalinformationmineralproperty", genModel);
		
	}
	  @RequestMapping(value="/property/Color")
		public ModelAndView getMineralsBycolor(){
			//String prop = "";
			List<String> prop = new ArrayList<>();
			ModelMap genModel = new ModelMap();
			
			System.out.println(genModel);
			Iterator<mineralproperties> iter = minrepo.findAll().iterator();
			while (iter.hasNext()) {
				prop.add(iter.next().getColor());
			}
			
			
			genModel.addAttribute("color", prop);		
			return new ModelAndView("Generalinformationmineralproperty", genModel);
		
	}
	  @RequestMapping(value="/property/diapheneity ")
		public ModelAndView getMineralsBydiapheneity (){
			//String prop = "";
			List<String> prop = new ArrayList<>();
			ModelMap genModel = new ModelMap();
			
			System.out.println(genModel);
			Iterator<mineralproperties> iter = minrepo.findAll().iterator();
			while (iter.hasNext()) {
				prop.add(iter.next().getDiapheneity());
			}
			
			
			genModel.addAttribute("diapheneity", prop.toString());		
			return new ModelAndView("Generalinformationmineralproperty", genModel);
		
	}
	  @RequestMapping(value="/property/CrystalForm")
		public ModelAndView getMineralsByCrystalForm(){
			//String prop = "";
			List<String> prop = new ArrayList<>();
			ModelMap genModel = new ModelMap();
			
			System.out.println(genModel);
			Iterator<mineralproperties> iter = minrepo.findAll().iterator();
			while (iter.hasNext()) {
				prop.add(iter.next().getCrystalForm());
			}
			
			
			genModel.addAttribute("CrystalForm", prop);		
			return new ModelAndView("Generalinformationmineralproperty", genModel);
		
	}
	  
	  // Controllers for Mineral Types //
	  
	  @RequestMapping(method = RequestMethod.GET, value="/types")
	  public ModelAndView MineralTypes(){
		  return new ModelAndView("GeneralInfoMineralTypes");
	  }
	  
	  @RequestMapping(method = RequestMethod.GET, value="/types/inst")
	  public ModelAndView MineralTypesInst(){
		  return new ModelAndView("GeneralInfoMineralTypesInst");
	  }
	  
	  @Autowired
	  private MineralTypesRepository typesRepo;
	  
	 
	  @RequestMapping(value="/types/all")
		public List<minerals> getAllTypes(){
		  return typesRepo.findAll();
	  }
	  @RequestMapping(value="/types/mineral-intro")
		public String getMineralIntro(){
		  String intro = "";
		  Iterator<minerals> iter = typesRepo.findAll().iterator();
			while (iter.hasNext()) {
				//System.out.println(iterator.next());
				intro = iter.next().getMineralintro();
			}
			return intro;
	  
	  }
	  
	  @RequestMapping(value="/types/types-intro")
		public String getMineralTypesIntro(){
		  String typesIntro = "";
		  Iterator<minerals> iter = typesRepo.findAll().iterator();
			while (iter.hasNext()) {
				//System.out.println(iterator.next());
				typesIntro = iter.next().getTypesintro();
			}
			return typesIntro;
	  
	  }
	  
	  @RequestMapping(value="/types/economy-mineral")
		public String getEconomyMineral(){
		  String mineral = "";
		  Iterator<minerals> iter = typesRepo.findAll().iterator();
			while (iter.hasNext()) {
				//System.out.println(iterator.next());
				mineral = iter.next().getEconomic();
			}
			return mineral;
	  
	  }
	  
	  @RequestMapping(value="/types/industrial-mineral")
		public String getIndustrialMineral(){
		  String mineral = "";
		  Iterator<minerals> iter = typesRepo.findAll().iterator();
			while (iter.hasNext()) {
				//System.out.println(iterator.next());
				mineral = iter.next().getIndustrial();
			}
			return mineral;
	  
	  }
	  @RequestMapping(method = RequestMethod.GET, value="/Rocktypes")
	  public ModelAndView RockTypes(){
		  return new ModelAndView("GeneralInfoRockTypes");
	  }
	  
	  @RequestMapping(method = RequestMethod.GET, value="/Rocktypes/inst")
	  public ModelAndView RockTypesInst(){
		  return new ModelAndView("GeneralInfoRockTypesInst");
	  }
	  
	  @Autowired
	  private RockTypesRepository rockrepo;
	  
	 
	  @RequestMapping(value="/Rocktypes/all")
		public List<rocktypes> getAllRockTypes(){
		  return rockrepo.findAll();
	  }
		 @RequestMapping(value="/Rocktypes/sedimentary")
			public String getSedimentary(){
			  String mineral = "";
			  Iterator<rocktypes> iter = rockrepo.findAll().iterator();
				while (iter.hasNext()) {
					//System.out.println(iterator.next());
					mineral = iter.next().getSedimentary();
				}
				return mineral;
		  
		  }
		 @RequestMapping(value="/Rocktypes/igneous")
			public String getIgneous(){
			  String mineral = "";
			  Iterator<rocktypes> iter = rockrepo.findAll().iterator();
				while (iter.hasNext()) {
					//System.out.println(iterator.next());
					mineral = iter.next().getIgneous();
				}
				return mineral;
		  
		  }
		  @RequestMapping(value="/Rocktypes/metamorphic")
			public String getMetamorphic(){
			  String mineral = "";
			  Iterator<rocktypes> iter = rockrepo.findAll().iterator();
				while (iter.hasNext()) {
					//System.out.println(iterator.next());
					mineral = iter.next().getMetamorphic();
				}
				return mineral;
		  
		  }
		// ------------------------------------------------------------------------------//

			@RequestMapping(method = RequestMethod.GET, value = "/Insert")
			public ModelAndView mongoInsert() {
				return new ModelAndView("GeneralInfoInsert");
			}

			@RequestMapping(method = RequestMethod.GET, value = "/insert={collection}")
			public String insert(@PathVariable("collection") String collection) throws Exception {
				MongoClient mongoClient = new MongoClient("localhost");
				List<String> databases = mongoClient.getDatabaseNames();
				String db1 = "geninfo";
				String result = "";
				String command = "";
				String coll = collection;
				// String importuri =
				// "spring.data.mongodb.uri=mongodb://localhost:27017/geninfo";
				// String cmd = mongoimport --db users --collection contacts --type csv
				// --headerline --file /opt/backups/contacts.csv
				DB db = mongoClient.getDB(db1);

				DBCollection collect = db.getCollection(coll);
				collect.drop();
				Runtime r = Runtime.getRuntime();
				Process p = null;
				// String uri = "D:\GDP\GenInfo-FinalProjectDocs\metallic.csv";
				if (coll.equalsIgnoreCase("metallicluster")) {
					command = "mongoimport -d geninfo -c " + coll
							+ " --type csv --file C:/Users/gradstu/Desktop/mongojars/metallic.csv --headerline";
				} else if (coll.equalsIgnoreCase("nonmetallicluster")) {
					command = "mongoimport -d geninfo -c " + coll
							+ " --type csv --file C:/Users/gradstu/Desktop/mongojars/nonmetallic.csv --headerline";
				}

				try {
					p = r.exec(command);
					System.out.println("Reading csv into Database");
					result = "Reading csv into Database";

				} catch (Exception e) {
					System.out.println("Error executing " + command + e.toString());
					result = "Error executing " + command + e.toString();
				}
				return result;

			}

			@RequestMapping(method = RequestMethod.GET, value = "/upload")
			public ModelAndView provideUploadInfo(Model model) {
				File rootFolder = new File(GeoAppApplication.ROOT);
				List<String> fileNames = Arrays.stream(rootFolder.listFiles()).map(f -> f.getName())
						.collect(Collectors.toList());

				model.addAttribute("files",
						Arrays.stream(rootFolder.listFiles()).sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
								.map(f -> f.getName()).collect(Collectors.toList()));

				return new ModelAndView("GeneralInfoInsert");
			}
			
			
			@RequestMapping(method = RequestMethod.POST, value = "/upload")
			public ModelAndView handleFileUpload(@RequestParam("collections") String name, @RequestParam("file") MultipartFile file,
					RedirectAttributes redirectAttributes) {
//				if (name.contains("/")) {
//					redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
//					return "redirect:upload";
//				}
//				if (name.contains("/")) {
//					redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
//					return "redirect:upload";
//				}
				
				System.out.println("entered");
				
				boolean check = true;
				if (!file.isEmpty()) {
					try {
						String f = GeoAppApplication.ROOT+"/" + name;
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(f)));
						FileCopyUtils.copy(file.getInputStream(), stream);
						stream.close();
						
						
						
						//mongoinsert
						
						MongoClient mongoClient = new MongoClient("localhost");
						//List<String> databases = mongoClient.getDatabaseNames();
						String db1 = "geninfo";
						//String result = "";
						String command = "";
						String coll = name;
						System.out.println(coll);
						// String importuri =
						// "spring.data.mongodb.uri=mongodb://localhost:27017/geninfo";
						// String cmd = mongoimport --db users --collection contacts --type csv
						// --headerline --file /opt/backups/contacts.csv
						DB db = mongoClient.getDB(db1);

						DBCollection collect = db.getCollection(coll);
						collect.drop();
						Runtime r = Runtime.getRuntime();
						Process p = null;
						System.out.println("before insert cmd");
						// String uri = "D:\GDP\GenInfo-FinalProjectDocs\metallic.csv";
//						if (coll.equalsIgnoreCase("metallicluster")) {
//							command = "mongoimport -d geninfo -c " + coll
//									+ " --type csv --file " +f +" --headerline";
//						} else if (coll.equalsIgnoreCase("nonmetallicluster")) {
//							command = "mongoimport -d geninfo -c " + coll
//									+ " --type csv --file " +f +" --headerline";
//						}
						command = "mongoimport -d geninfo -c " + coll
								+ " --type csv --file " +f +" --headerline";
						System.out.println(command);
												
				   try {
							p = r.exec(command);
							System.out.println("Reading csv into Database");
							//result = "Reading csv into Database";
							check = true;

						} catch (Exception e) {
							System.out.println("Error executing " + command + e.toString());
							//result = "Error executing " + command + e.toString();
							check = false;
						}
					//redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + name + "!");
					} catch (Exception e) {
//						redirectAttributes.addFlashAttribute("message",
//								"You failed to upload " + name + " => " + e.getMessage());
						check = false;
						//return new ModelAndView("GeneralInfoUploadFailure");
					}
				} 
				if(check ==true){
					return new ModelAndView("GeneralInfoUploadSuccess");
				}
				else{
					return new ModelAndView("GeneralInfoUploadFailure");
				}

				
			}

}
