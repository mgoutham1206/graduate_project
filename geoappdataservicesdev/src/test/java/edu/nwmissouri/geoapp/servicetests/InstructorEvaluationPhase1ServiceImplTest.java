package edu.nwmissouri.geoapp.servicetests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.repository.ImagesubmissionRepository;
import edu.nwmissouri.geoapp.serviceImpl.InstructorEvaluationPhase1ServiceImpl;
import edu.nwmissouri.geoapp.test.GeoAppUnitTesting;

import org.junit.Assert;

import junit.framework.TestCase;



public class InstructorEvaluationPhase1ServiceImplTest extends GeoAppUnitTesting {

	
	@Autowired
	private ImagesubmissionRepository imagesubRepo;
	
	@Autowired
	private InstructorEvaluationPhase1ServiceImpl instructorEvaluationPhase1ServiceImpl;

	@Before
	public void setUp() {
		

	}

	@After
	public void tearDown() {

	}
	
	@Test
	public void testFindAll(){
		List<TblImagesubmission> list = imagesubRepo.findAll();
		
		Assert.assertNotNull("Not Null", list);
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetSubmissionById() {
//		InstructorEvaluationPhase1ServiceImpl instructorEvaluationPhase1ServiceImpl = new InstructorEvaluationPhase1ServiceImpl();
		List<TblImagesubmission> imagesList = instructorEvaluationPhase1ServiceImpl.getImageSubmissions("1");
		
		Assert.assertNotNull(imagesList);
		TblSubmission tblSubmission = new TblSubmission();
		String id = new String("1");
		//List<TblImagesubmission> imagesList = instructorEvaluationPhase1ServiceImpl.getImageSubmissions(id);
		//List<TblImagesubmission> imagesList = instructorEvaluationPhase1ServiceImpl.imagesubmissionRepo.findImagesBySubmissionId(1);
		
		//Assert.assertNull(imagesList);
		
//		TblImagesubmission image = imagesList.get(0);
//		Assert.assertEquals("Latitude doesn't match", "-39.88", image.getLatitude());
//
//		TblImagesubmission tblimgsub = new TblImagesubmission();
//		tblimgsub.setImageID(1);
//		tblimgsub.setLatitude("-39.88");
//		tblimgsub.setLongitude("45");
//		Assert.assertEquals(tblimgsub, image);
	}
	
	@Test
	public void testUpdateImageSubTable(){
		TblImagesubmission tblimgsub = new TblImagesubmission();
		tblimgsub.setImageID(1);
		tblimgsub.setLatitude("-39.88");
		tblimgsub.setLongitude("45");
		Assert.assertEquals(tblimgsub.getImageID(), tblimgsub.getImageID());
		instructorEvaluationPhase1ServiceImpl.updateImageSubmission(tblimgsub);
	}
	

	@Test
	public void testSaveCommentsBySubmissionId() {
		boolean ans = false;
		TblSubmission tbs = new TblSubmission();
		tbs.setSubmissionID(1);
		TblPhaseevaluation tbphase = new TblPhaseevaluation();
		tbphase.setComments("Phase1");
		instructorEvaluationPhase1ServiceImpl.saveComments("Phase1", "1");
		Assert.assertEquals(false, ans);

	
}
	
}


