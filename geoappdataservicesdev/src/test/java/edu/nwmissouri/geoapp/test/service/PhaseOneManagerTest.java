//
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito.*;
//
//import edu.nwmissouri.geoapp.model.TblImagesubmission;
//import edu.nwmissouri.geoapp.model.TblSubmission;
//import edu.nwmissouri.geoapp.service.StudentService;
//import edu.nwmissouri.geoapp.web.api.util.PhaseOneForm;
//
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.mockito.Matchers.anyString;
//
//
//
//public class PhaseOneManagerTest {
//
//	 @Mock
//	 private StudentService studentService;
//
//	 @Test
//	 public void testGetSubmission() {
//	 PhaseOneManager phaseOneManager=new PhaseOneManager();
//	 TblSubmission tbs = mock(TblSubmission.class);
//	 tbs = new TblSubmission();
//	 tbs.setSubmissionID(1234);
//	 tbs.setRockType("hematite");
//	 TblImagesubmission tblImagesubmission = new TblImagesubmission();
//	 tblImagesubmission.setLatitude("2.345");
//	 when(studentService.getSubmission(400001, 100001)).thenReturn(tbs);
//	 PhaseOneForm phaseOneForm= phaseOneManager.getSubmission(400001, 100001);
//	 assertEquals("3.291",phaseOneForm.getLatitude());
//	 
//	 }
//	 
//	 
//	
//}
