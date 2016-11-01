//package edu.nwmissouri.geoapp.test.service;
//
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import edu.nwmissouri.geoapp.model.TblAttributetype;
//import edu.nwmissouri.geoapp.model.TblGeometrytype;
//import edu.nwmissouri.geoapp.model.TblHardnesstype;
//import edu.nwmissouri.geoapp.model.TblLustertype;
//import edu.nwmissouri.geoapp.model.TblRocktype;
//import edu.nwmissouri.geoapp.model.TblSpecificgravitytype;
//import edu.nwmissouri.geoapp.model.TblStreakcolortype;
//import edu.nwmissouri.geoapp.service.AttributeTypeService;
//import edu.nwmissouri.geoapp.service.GeometryTypeService;
//import edu.nwmissouri.geoapp.service.HardnessTypeService;
//import edu.nwmissouri.geoapp.service.LusterService;
//import edu.nwmissouri.geoapp.service.RockTypeService;
//import edu.nwmissouri.geoapp.service.SpecificgravityTypeService;
//import edu.nwmissouri.geoapp.service.StreakColorTypeService;
//import edu.nwmissouri.geoapp.test.GeoAppUnitTesting;
//
////Unit testing for all type tables
//public class TypeTablesTest extends GeoAppUnitTesting{
//
//	@Autowired
//	public AttributeTypeService attributes;
//	
//	
//	//Test case for attribute types service
//	@Test
//	 public void testFindAllAttributes(){
//		List<TblAttributetype> attr=attributes.findAll();
//		
////		Assert.assertNotNull("Failure- Not expected null",attr);
////		Assert.assertEquals("Failure - Does not fetch all records", 7, attr.size());
//	}
//	
//	@Autowired
//	public GeometryTypeService geometry;
//	
//	//Test case for geometry types service
//	@Test
//	 public void testFindAllGeometry(){
//		List<TblGeometrytype> GeoTypes=geometry.findAll();
//		
////		Assert.assertNotNull("Failure- Not expected null",GeoTypes);
////		Assert.assertEquals("Failure - Does not fetch all records", 4, GeoTypes.size());
//	}
//	
//	@Autowired
//	public HardnessTypeService hardness;
//	
//	//Test case for hardness types service
//	@Test
//	public void testFindAllHardness(){
//		List<TblHardnesstype> hardnessTypes=hardness.findAll();
//		
////		Assert.assertNotNull("Failure- Not expected null",hardnessTypes);
////		Assert.assertEquals("Failure - Does not fetch all records", 5, hardnessTypes.size());
//	}
//	
//	@Autowired
//	public LusterService luster;
//	
//	//Test case for Luster types service
//	@Test
//	public void testFindAllLusters(){
//		List<TblLustertype> lusterTypes=luster.findAllLusterType();
//		
////		Assert.assertNotNull("Failure- Not expected null",lusterTypes);
////		Assert.assertEquals("Failure - Does not fetch all records", 2, lusterTypes.size());
//	}
//	
//	@Autowired
//	public RockTypeService rock;
//	
//	//Test case for rock types service
//	@Test
//	public void testFindAllRocks(){
//		List<TblRocktype> rockTypes=rock.findAll();
//		
////		Assert.assertNotNull("Failure- Not expected null",rockTypes);
////		Assert.assertEquals("Failure - Does not fetch all records", 4, rockTypes.size());
//	}
//	
//	@Autowired
//	public SpecificgravityTypeService sgType;
//	
//	//Test case for Specific gravity type service
//	@Test
//	public void testFindAllGravity(){
//		List<TblSpecificgravitytype> sGravityTypes=sgType.findAll();
//		
////		Assert.assertNotNull("Failure- Not expected null",sGravityTypes);
////		Assert.assertEquals("Failure - Does not fetch all records", 3, sGravityTypes.size());
//	}
//
//	@Autowired
//		public StreakColorTypeService scType;
//		
//		//Test case for Streak color type service
//		@Test
//		public void testFindAllColor(){
//			List<TblStreakcolortype> sColorTypes=scType.findAll();
//			
//	//		Assert.assertNotNull("Failure- Not expected null",sColorTypes);
//	//		Assert.assertEquals("Failure - Does not fetch all records", 3, sColorTypes.size());
//		}
//	
//}
