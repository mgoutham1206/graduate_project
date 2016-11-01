//package edu.nwmissouri.geoapp.test.service;
//
//import java.util.Map;
//
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import edu.nwmissouri.geoapp.model.TblImagesubmission;
//import edu.nwmissouri.geoapp.service.ImageSubmissionService;
//import edu.nwmissouri.geoapp.test.GeoAppUnitTesting;
//
//@Transactional
//public class ImageSubmissionServiceTest extends GeoAppUnitTesting {
//	
//	@Autowired 
//	private ImageSubmissionService imagesubserv;	
//
//	 @Before
//	 public void setUp(){		 
//		 imagesubserv.evictCache();
//	 }	 
//	 @After
//	 public void tearDown() {
//	        // clean up after each test method
//	    }
//	@Test
//	public void testFindAll(){
//		 Map<String, Object> imageallmap = imagesubserv.findAll();
//		 Assert.assertNotNull("failure - expected not null", imageallmap);
//		 Assert.assertEquals("failure - expected list size", 1, imageallmap.size());
//		
//	}
//	
//	 @Test
//	    public void testFindOne() {
//
//	        int imageID = 700001;
//
//	        TblImagesubmission entity = imagesubserv.findOne(imageID);
//
//	        Assert.assertNotNull("failure - expected not null", entity);
//	        Assert.assertEquals("failure - expected id attribute match", imageID,
//	                entity.getImageID());
//	       
//
//	    }
//	 
//}
