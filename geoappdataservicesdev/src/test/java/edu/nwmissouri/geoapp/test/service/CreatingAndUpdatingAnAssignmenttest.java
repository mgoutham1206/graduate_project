/*package edu.nwmissouri.geoapp.test.service;

import edu.nwmissouri.geoapp.test.GeoAppUnitTesting;
import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.repository.AssignmentRepository;
import edu.nwmissouri.geoapp.service.AssignmentService;
// edu.nwmissouri.geoapp.serviceImpl.AssignmentServiceImpl;

import java.util.List;
import java.util.Map;
	
	import javax.transaction.Transactional;
	
	import org.aspectj.lang.annotation.After;
	import org.junit.Assert;
	import org.junit.Before;
	import org.junit.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import edu.nwmissouri.geoapp.test.GeoAppUnitTesting;
	
	@Transactional
	public class CreatingAndUpdatingAnAssignmenttest extends GeoAppUnitTesting{
	
		@Autowired
	
	private AssignmentService assignID;
		
		@Before
		 public void setUp(){
		// CreatingAndUpdatingAnAssignmenttest assignID = null;
		assignID.findAssignment();
			
		}
	//	
		//private void evictCache() {
			
		//}
	
		@After(value = "")
		 public void tearDown() {
		        // clean up after each test method
		    }
	//	
	@Test
		public void findAssign(){
			 Map<String, Object> serv = assignID.findAssignment();
			 List<TblAssignment> tblAssign1 = (List<TblAssignment>) serv.get(1);
			 Assert.assertEquals(1,tblAssign1.get(0).getAssignID());
			 //Assert.assertNotEquals("failure - expected not null", serv);
			 //Assert.assertEquals("failure - expected list size", 1, serv.);
			 }
	
	// Map<String, Object> getAllSections() {
		//	return null;
		//}
	//	
		 @Test
		    public void testAssignID() {
	
	        Object entity = null;
			Assert.assertNotNull("failure - expected not null", entity);
             

	
	    }
	//	
	//
	//	
	}
	
*/