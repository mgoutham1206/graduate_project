//package edu.nwmissouri.geoapp.test.service;
//
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import edu.nwmissouri.geoapp.model.TblUser;
//import edu.nwmissouri.geoapp.service.StudentEnrollmentConfirmationService;
//import edu.nwmissouri.geoapp.service.StudentEnrollmentService;
//import junit.framework.Assert;
//
//public class StudentEnrollConfirmServiceTest {  
//	
//	@Autowired
//	StudentEnrollmentConfirmationService studentEnrollConfirmService;
//	
//	@Autowired
//	StudentEnrollmentService studentEnrolService;
//	@Before
//    public void setUp() {
//		//studentEnrollConfirmService.evictCache();         
//	}   
//	
//	
//	@After
//    public void tearDown() {
//        // clean up after each test method
//   }  
//	
//	@Test
//    public void testFindOne() {
//     int userID = 100001;
//     TblUser entity1 = studentEnrollConfirmService.findOne(userID);   
//
//       Assert.assertNotNull("failure - expected not null", entity1);
//       Assert.assertEquals("failure - expected id attribute match", userID,
//           entity1.getUserID());       
//       
//      
//
//  }
//	
//	
//	
//	
//
//}
