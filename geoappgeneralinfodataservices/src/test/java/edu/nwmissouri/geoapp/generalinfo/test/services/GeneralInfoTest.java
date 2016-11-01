//package edu.nwmissouri.geoapp.generalinfo.test.services;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import edu.nwmissouri.geoapp.generalinfo.GeoAppMongoApplicationTests;
//import edu.nwmissouri.geoapp.generalinfo.model.metallic;
//import edu.nwmissouri.geoapp.generalinfo.repository.MineralRepository;
//
//@Transactional
//public class GeneralInfoTest extends GeoAppMongoApplicationTests {
//	
//	@Autowired
//	private MineralRepository mineralRepo;
//	
//	@Before
//	public void setUp(){
//		//runs before any test case
//	}
//	
//	@After
//	public void testDone(){
//		// runs after any test case
//	}
//	
//	@Test
//	public void testFindAll(){
//		List<metallic> list = mineralRepo.findAll();
//		
//		Assert.assertNotNull("Failure - Expected not null", list);
//		Assert.assertEquals("Failure - Expected number of documents in metallic collection", 10, list.size());
//	}
//	
//	@Test
//	public void testFindOne(){
//		String id = new String("m4");
//		metallic metal = mineralRepo.findOne(id);
//		Assert.assertNotNull("Failure - Expected not null", metal);
//		Assert.assertEquals("Failure - Expected mineral details not present", id, metal.get_id());
//	}
//	
//	@Test
//	public void testFindAllMetallicStreakColors(){
//		Set<String> streak = new HashSet<String>();
//		Iterator<metallic> iter = mineralRepo.findAll().iterator();
//		while (iter.hasNext()) {
//			//System.out.println(iterator.next());
//			streak.addAll(iter.next().getStreakcolor());
//		}
//		Assert.assertNotNull("Failure - Expected not null", streak);
//		Assert.assertEquals("Failure - Expected number of streak colors in metallic collection", 7, streak.size());
//	
//	}
//	
//	@Test
//	public void testFindAllMetallicColors(){
//		Set<String> streak = new HashSet<String>();
//		Iterator<metallic> iter = mineralRepo.findAll().iterator();
//		while (iter.hasNext()) {
//			//System.out.println(iterator.next());
//			streak.addAll(iter.next().getColor());
//		}
//		Assert.assertNotNull("Failure - Expected not null", streak);
//		Assert.assertEquals("Failure - Expected number of streak colors in metallic collection", 13, streak.size());
//	
//	}
//	
//	@Test
//	public void testFindMetallicMineralsByStreak(){
//		Set<String> metalStreak = new HashSet<String>();
//		List<String> streakcolor= new ArrayList<>();
//		streakcolor.add("black"); streakcolor.add("brown"); streakcolor.add("white"); streakcolor.add("red"); streakcolor.add("colorless");
//		streakcolor.add("yellow"); streakcolor.add("greenish black");
//		for(String s : streakcolor){
//			//System.out.println(s);
//			Iterator<metallic> iterator = mineralRepo.findByStreakcolor(s).iterator();
//			while (iterator.hasNext()) {
//				metalStreak.add(iterator.next().getMineral());
//				}			
//		}
//		Assert.assertNotNull("Failure - Expected not null", metalStreak);
//		Assert.assertEquals("Failure - Expected number of minerals in metallic collection with all the available streak color", 10, metalStreak.size());
//	}
//	
//	@Test
//	public void testFindMetallicMineralsByColor(){
//		Set<String> metalStreak = new HashSet<String>();
//		List<String> color= new ArrayList<>();
//		color.add("green"); color.add("dark green"); color.add("brassy"); color.add("yellow"); color.add("black");
//		color.add("brown"); color.add("red"); color.add("steel gray"); color.add("red brown"); color.add("white");
//		color.add("brassy yellow"); color.add("dark brown"); color.add("silver");
//		for(String s : color){
//			//System.out.println(s);
//			Iterator<metallic> iterator = mineralRepo.findByColor(s).iterator();
//			while (iterator.hasNext()) {
//				metalStreak.add(iterator.next().getMineral());
//				}			
//		}
//		Assert.assertNotNull("Failure - Expected not null", metalStreak);
//		Assert.assertEquals("Failure - Expected number of minerals in metallic collection with all the available color", 10, metalStreak.size());
//	}
//}
