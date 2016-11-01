//package edu.nwmissouri.geoapp.test.service;
//
//import static org.junit.Assert.*;
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
//import edu.nwmissouri.geoapp.repository.PhaseevaluationRepository;
//import edu.nwmissouri.geoapp.service.FeedbackService;
//import edu.nwmissouri.geoapp.serviceImpl.FeedbackServiceImpl;
//import edu.nwmissouri.geoapp.test.GeoAppUnitTesting;
//
//
//
//public class FeedbackServiceTest extends GeoAppUnitTesting{
//	
//	@Autowired
//	FeedbackService feedbackserv;
//	
//	
//	@Before
//	public void beforeTest(){
//			
//	}
//
//	@Test
//	public void findFeedbacktest() {
////		FeedbackServiceImpl feedserv = new FeedbackServiceImpl();
//		Map<String, Object> feed = feedbackserv.findFeedback(400001);
//		List<TblPhaseevaluation> tblPhaseevaluation1=(List<TblPhaseevaluation>) feed.get("feedback");
//		assertEquals(500001, tblPhaseevaluation1.get(0).getTblSubmission().getSubmissionID());
//		
//	}
//
//}
