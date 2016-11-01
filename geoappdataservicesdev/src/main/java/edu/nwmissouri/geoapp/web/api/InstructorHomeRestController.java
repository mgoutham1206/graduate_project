package edu.nwmissouri.geoapp.web.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.aspectj.weaver.bcel.UnwovenClassFileWithThirdPartyManagedBytecode.IByteCodeProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.JSONArray;
//import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonSerializer;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblAttributeevaluation;
import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblMineralsubmission;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.PhaseevaluationRepository;
import edu.nwmissouri.geoapp.service.AttributeEvaluationService;
import edu.nwmissouri.geoapp.service.SubmissionService;
import edu.nwmissouri.geoapp.serviceImpl.AsssignmentServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.AttributeEvaluationServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.InstructorEvaluationPhase1ServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.InstructorEvaluationPhase2ServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.InstructorEvaluationPhase3ServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.MineralSubmissionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.PhaseEvaluationServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.SectionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.StudentServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.SubmissionServiceImpl;
import edu.nwmissouri.geoapp.serviceImpl.UserServiceImpl;
import edu.nwmissouri.geoapp.web.api.util.PhaseOneForm;
import edu.nwmissouri.geoapp.web.api.util.PhaseOneUtil;

@RestController
@RequestMapping("/rest/instructor")
public class InstructorHomeRestController {
	@Autowired
	public InstructorEvaluationPhase1ServiceImpl instructorEvaluationPhase1impl;
	public InstructorEvaluationPhase2ServiceImpl instructorEvaluationPhase2impl;
	public InstructorEvaluationPhase3ServiceImpl instructorEvaluationPhase3impl;
	@Autowired
	public PhaseEvaluationServiceImpl phaseEvaluationServiceimpl;
	@Autowired
	public AttributeEvaluationServiceImpl attributeEvaluaionServiceimpl;
	
	
	@Autowired
	MineralSubmissionServiceImpl mineralService;	
	@Autowired
	SubmissionServiceImpl submissionService;
	@Autowired
	SectionServiceImpl sectionServiceImpl;
	@Autowired
	AsssignmentServiceImpl assignmentServiceImpl;
	@Autowired
	UserServiceImpl userimpl;
	@Autowired
	StudentServiceImpl studentServiceImpl;
	
	@Autowired
	AttributeEvaluationServiceImpl attributeEvalationServiceImpl;
	
	
	/** getting phase one details through submission id **/
	//@CrossOrigin(origins = "http://localhost:8080") // to enable cross ajax
	@RequestMapping(value = "/getPhase1")
	public List<PhaseOneForm> getPhase1(@RequestParam("submissionID") String submissionID) {
		// List<TblImagesubmission> filteredData = new ArrayList<>();
		List<PhaseOneForm> imagesFormList = new ArrayList<>();
		List<TblImagesubmission> alltblimageList = instructorEvaluationPhase1impl.imagesubmissionRepo.findImagesBySubmissionId(Integer.parseInt(submissionID));

		// TODO -- get tblimagesubmission's with submission avoid loading all
		// tblimagesubmission
//		 System.out.println("***************"+alltblimageList.get(0).getComments());
		
		for (TblImagesubmission tblimagesub : alltblimageList) {
			PhaseOneForm phaseOneForm = new PhaseOneForm();
			
			String imageString = PhaseOneUtil.encodeImageAsBytetoString(tblimagesub.getImage());
			System.out.println(imageString);
			List<String> images = new ArrayList<>();
			images.add(imageString);
			List<String> imageComment = new ArrayList<>();
			imageComment.add(tblimagesub.getComments());
			phaseOneForm.setComments(imageComment);
			phaseOneForm.setImageID(tblimagesub.getImageID());
			phaseOneForm.setImageList(images);
			phaseOneForm.setAssignmentId(0);
			phaseOneForm.setDescription("");
			phaseOneForm.setErrorMessage("");
			phaseOneForm.setLatitude(tblimagesub.getLatitude());
			phaseOneForm.setLongitude(tblimagesub.getLongitude());
			phaseOneForm.setStatusCode("");
			phaseOneForm.setIsAcceptedPhaseOne(tblimagesub.getIsAccepted());
			try{
			if(tblimagesub.getIsBest().equals("Y"))
			phaseOneForm.setIsEvaluated("checked");
			else 
				phaseOneForm.setIsEvaluated("");
			}catch(Exception e){
				phaseOneForm.setIsEvaluated("");				
			}
			imagesFormList.add(phaseOneForm);
		}
		return imagesFormList;
	}
	
	/** getting phase two details through submission ID **/
	//@CrossOrigin(origins = "http://localhost:8080") // to enable cross ajax
	@RequestMapping(value = "/getPhase2")
	public List<TblMineralsubmission> getPhase2(@RequestParam("submissionID") String submissionID) {


		return mineralService.findBySubmissionID(Integer.parseInt(submissionID));

	}
	
	//@CrossOrigin(origins = "http://localhost:8080") // to enable cross ajax
	@RequestMapping(value = "/getPhase02")
	public TblMineralsubmission getPhase2(@RequestParam("submissionID") String submissionID, @RequestParam("mineralID") String mineralID) {


		return mineralService.findBySubmissionIDAndMineralID(Integer.parseInt(submissionID), Integer.parseInt(mineralID)).get(0);

	}
	
	@RequestMapping(value = "/getPhase02Comments")
	public List<TblAttributeevaluation> getPhase02Comments(@RequestParam("submissionID") String submissionID, @RequestParam("mineralID") String mineralID) {

		List<TblAttributeevaluation> attreval=mineralService.findBySubmissionIDAndMineralID(Integer.parseInt(submissionID), Integer.parseInt(mineralID)).get(0).getTblAttributeevaluations();
		return attreval;

	}	

	//getting phase 3 details
	//@CrossOrigin(origins = "http://localhost:8080") // to enable cross ajax
	@RequestMapping(value = "/getPhase3")
	public List<TblSubmission> getPhase3(@RequestParam("submissionID") String submissionID) {


		return submissionService.findBySubmissionID(Integer.parseInt(submissionID));
	}
	
	
	@RequestMapping(value = "/getPossiblePointsByPhase/{submissionID}/{phase}")
	public BigDecimal getPossiblePointsByPhase(@PathVariable("submissionID") int submissionID,@PathVariable("phase") int phase) {

return assignmentServiceImpl.getPossiblePointsByPhase(submissionID,phase);
		
	}
	
	@RequestMapping(value = "/getAllInstructorPhases")
	public List<String> getAllPhases(@RequestParam("submissionID") String submissionID) {
		System.out.println("**************************************");
		return submissionService.findPhasesBySubmissionID(Integer.parseInt(submissionID));
	}
	
	//getting All sections 
	//@CrossOrigin(origins = "http://localhost:8080") // to enable cross ajax
	@RequestMapping(value="/getAllSections",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<TblSection> getAssignment(){			
		 return sectionServiceImpl.getInstSectionData();
	}
	
	
	//Getting Assignments by sectionID
	@RequestMapping(value="/getAllAssign/{sectionId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<TblAssignment> getAssigmentsBySectionID(@PathVariable("sectionId") int sectionId){
		return assignmentServiceImpl.getAssigments(sectionId);
	}
	
	
	//getting submissions by assignment ID
		@RequestMapping(value="/getSubmissionByAssignmentId/{assignmentId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public Map<Integer,TblUser> getAllSubmissionByAssigmentID(@PathVariable("assignmentId") int assignmentId){
			// List<TblSubmission> allsubmission=submissionService.findByAssignmentID(assignmentId);
			 
			Map<Integer,TblUser> allstudentsWithSubmissions=new HashMap<>();
			List<TblSubmission> allsubmissions=submissionService.findByAssignmentID(assignmentId);
			TblUser user=new TblUser();
//			List<TblUser> submissionusers=new ArrayList<>();
			for(int i=0;i<allsubmissions.size();i++){
				user=userimpl.findbyuserID(allsubmissions.get(i).getTblUser().getUserID());
//				submissionusers.add(user);
				allstudentsWithSubmissions.put(allsubmissions.get(i).getSubmissionID(),user);
			}
			return allstudentsWithSubmissions;
		}
	
//	//getting submissions by assignment ID
//	@RequestMapping(value="/getSubmissionByAssignmentId/{assignmentId}/{studentId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public Map<Integer,TblSubmission> getAllSubmissionByAssigmentID(@PathVariable("assignmentId") int assignmentId, @PathVariable("studentId") int studentId){
//		// List<TblSubmission> allsubmission=submissionService.findByAssignmentID(assignmentId);
//		 
//		Map<Integer,TblSubmission> allstudentsWithSubmissions=new HashMap<>();
//		List<TblSubmission> allsubmissions=submissionService.findByAssignmentID(assignmentId);
//		//TblUser user=new TblUser();
//		TblSubmission student = new TblSubmission();
////		List<TblUser> submissionusers=new ArrayList<>();
//		for(int i=0;i<allsubmissions.size();i++){
//			student=studentServiceImpl.getSubmission(assignmentId, studentId);
////			submissionusers.add(user);
//			allstudentsWithSubmissions.put(allsubmissions.get(i).getSubmissionID(),student);
//		}
//		return allstudentsWithSubmissions;
//	}
	
	
	//Updating all phasees tables
	@RequestMapping(value = "/updateImageSubmission", method = RequestMethod.POST)
	public boolean updateImageSubmission(@RequestBody TblImagesubmission imagesubmission) {

		instructorEvaluationPhase1impl.updateImageSubmission(imagesubmission);
		return true;

	}
	
	
	@RequestMapping(value="/findSubmissionsByAssignmentID", method= RequestMethod.GET)
	
	public List<String> findSubmissionsByAssignmentID(@RequestParam("assignmentId") int assignmentId){
		
		return submissionService.findStudentsByAssignmentID(assignmentId);
		
	}
	
	@RequestMapping(value = "/updateMineralSubmission", method = RequestMethod.POST)
	public boolean updateMineralSubmission(@RequestBody TblMineralsubmission mineralsubmission) {

		instructorEvaluationPhase2impl.updateMineralSubmission(mineralsubmission);
		return true;

	}
	
	@RequestMapping(value = "/updateSubmission", method = RequestMethod.POST)
	public boolean updateSubmission(@RequestBody TblSubmission submission) {

		instructorEvaluationPhase3impl.updateSubmission(submission);
		return true;

	}
//
//	@CrossOrigin(origins = "http://localhost:8080") // to enable cross ajax
//	@RequestMapping(value = "/saveCommentsForPhase2", method = RequestMethod.GET)
//	public boolean saveComments(@RequestParam("comments") String comments,
//			@RequestParam("submissionID") String submissionID){
//		
//		phaseEvaluationServiceimpl.phaseevaluationRepository.insertPhaseData(Integer.parseInt(submissionID), 2, null,
//		comments, 0);
//		return true;
//	}
	
	//@CrossOrigin(origins = "http://localhost:8080") // to enable cross ajax
	
	@RequestMapping(value = "/saveCommentsForPhaseAtt2", method = RequestMethod.GET)
	public boolean saveCommentsForPhaseAtt2(@RequestParam("mineralData") String mineralData) throws JSONException{
		   
		JSONObject jsonObj = new JSONObject(mineralData);
		   
		//String overall = jsonObj.getString("overall");
		//String points = jsonObj.getString("Points");
		
		   String mineralID = jsonObj.getString("MineralID");
		   
		   
		   JSONArray jsonArray = jsonObj.getJSONArray("Attributes");
		   
		   
		   for(int i=0;i<jsonArray.length();i++){
			   
			   int attributeID = jsonArray.getJSONObject(i).getInt("AttributeID");
			   
			   String mineralComments = jsonArray.getJSONObject(i).getString("Comments");
			   
			   
			   String IsAccepted = jsonArray.getJSONObject(i).getString("IsAccepted");
			   System.out.println(IsAccepted+"****************");
			
			   List<TblAttributeevaluation> attEvaluation=attributeEvalationServiceImpl.attributeEvaluationRepository.findAll();
			   int flagattr=0;
			   for(TblAttributeevaluation tblatteval:attEvaluation){
				   if(tblatteval.getId().getMineralID()==Integer.parseInt(mineralID) && tblatteval.getId().getAttributeID()==attributeID )
					   {flagattr=1;break;}  
			   }
			   
			   if(flagattr==0)
				   attributeEvalationServiceImpl.insertData(Integer.parseInt(mineralID), attributeID,IsAccepted, mineralComments);
			   else
				   attributeEvalationServiceImpl.updateData(Integer.parseInt(mineralID),attributeID,IsAccepted, mineralComments);
			  
			   
		   }		   
		
		return true;
	}
	@RequestMapping(value = "/saveCommentsForPhase2", method = RequestMethod.GET)
	public boolean saveCommentsForPhase2(@RequestParam("comments") String comments,
			@RequestParam("submissionID") String submissionID, @RequestParam("mineralData1") String mineralData1) throws JSONException{
		   
		JSONObject jsonObj = new JSONObject(mineralData1);
		   
		String overall = jsonObj.getString("overall");
		String points = jsonObj.getString("Points");
		
		  // String mineralID = jsonObj.getString("MineralID");
		   
		   
		   //JSONArray jsonArray = jsonObj.getJSONArray("Attributes");
		   
		   
		   //for(int i=0;i<jsonArray.length();i++){
			   
			  // int attributeID = jsonArray.getJSONObject(i).getInt("AttributeID");
			   
			   //String mineralComments = jsonArray.getJSONObject(i).getString("Comments");
			   
			   
			   //String IsAccepted = jsonArray.getJSONObject(i).getString("IsAccepted");
			
			  //attributeEvalationServiceImpl.insertData(Integer.parseInt(mineralID), attributeID,IsAccepted, mineralComments);
			   
		  // }		   
		//phaseEvaluationServiceimpl.phaseevaluationRepository.insertPhaseData(Integer.parseInt(submissionID), 2,overall,
		//comments, Float.parseFloat(points));
		List<TblPhaseevaluation> tblevaluationlist=phaseEvaluationServiceimpl.phaseevaluationRepository.findAll();
		boolean flag=true;
		for(TblPhaseevaluation tblphaseeval:tblevaluationlist){
			if(tblphaseeval.getTblSubmission().getSubmissionID()==Integer.parseInt(submissionID) && tblphaseeval.getTblPhasetype().getPhaseID()==2){
					phaseEvaluationServiceimpl.phaseevaluationRepository.updatePhaseData(Integer.parseInt(submissionID), 2, overall,
							comments, Float.parseFloat(points));
					flag=false;
					break;
				}	
			}
		if(flag==true)
			{
				phaseEvaluationServiceimpl.phaseevaluationRepository.insertPhaseData(Integer.parseInt(submissionID), 2, overall,
						comments, Float.parseFloat(points));
			}
		
		
//		phaseEvaluationServiceimpl.phaseevaluationRepository.save(tblPhaseevaluation);
//		phaseEvaluationServiceimpl.phaseevaluationRepository.updatePhaseData(Integer.parseInt(submissionID), 1, isacceptedimages,
//				phase1Comment, Float.parseFloat(points));

		
		
		return true;
	}
	
	
	// TODO validation on constraints and return standard response format.
	/** update comments for the submission ID 
	 * @throws JSONException **/
	//@CrossOrigin(origins = "http://localhost:8080") // to enable cross ajax
	@RequestMapping(value = "/saveComments", method = RequestMethod.GET)
	public boolean saveComments(@RequestParam("comments") String phase1Comment,
			@RequestParam("submissionID") String submissionID, @RequestParam("imageIDData") String imageId,@RequestParam("points") String points,@RequestParam("overallAccept") String overallAccept) throws JSONException {
		String isacceptedimages="Y";
		
		System.out.println("updating overall ******* " + overallAccept);
		System.out.println("updating comments " + phase1Comment);

		System.out.println("imageiddata " + imageId);
		
		/** string to json array **/
		JSONArray jArrObj = new JSONArray(imageId);

		
		/** using native SQL query, i have updating the tbl_phaseevaluation **/
		// TODO try using ORM, avoid native SQL
		
	
		/** iterating all imagesubmission **/
		// TODO get imagesubmission with submission id
		for (TblImagesubmission tblimagesub : instructorEvaluationPhase1impl.imagesubmissionRepo.findAll()) {
			/** iterating json array **/
			for (int i = 0; i < jArrObj.length(); i++) {
				/** extracting values from json array **/
				int imageID1 = jArrObj.getJSONObject(i).getInt("imageID");
				String isAccepted = jArrObj.getJSONObject(i).getString("imageIsAccept");
				String isBest = jArrObj.getJSONObject(i).getString("imageIsBest");
				String comment = jArrObj.getJSONObject(i).getString("imageComment");

				if (tblimagesub.getTblSubmission().getSubmissionID() == Integer.parseInt(submissionID)
						&& tblimagesub.getImageID() == imageID1) {

					/** update tblimagesubmissiin ***/
					tblimagesub.setComments(comment);
					tblimagesub.setIsAccepted(isAccepted);
					tblimagesub.setIsBest(isBest);
					if(isAccepted.equals("N"))
					{
						System.out.println(isAccepted+"*******************");
						isacceptedimages="N";
						
					}
					System.out.println(isAccepted+"**************************************************************");
					
					instructorEvaluationPhase1impl.imagesubmissionRepo.save(tblimagesub);	

				}
			}
		}
		
		//if(phaseEvaluationServiceimpl.phaseevaluationRepository.)
		
		//phaseEvaluationServiceimpl.phaseevaluationRepository.insertPhaseData(Integer.parseInt(submissionID), 1, overallAccept,
			//	phase1Comment, Float.parseFloat(points));
		List<TblPhaseevaluation> tblevaluationlist=phaseEvaluationServiceimpl.phaseevaluationRepository.findAll();
		boolean flag=true;
		for(TblPhaseevaluation tblphaseeval:tblevaluationlist){
			if(tblphaseeval.getTblSubmission().getSubmissionID()==Integer.parseInt(submissionID) && tblphaseeval.getTblPhasetype().getPhaseID()==1){
					phaseEvaluationServiceimpl.phaseevaluationRepository.updatePhaseData(Integer.parseInt(submissionID), 1, overallAccept,
							phase1Comment, Float.parseFloat(points));
					flag=false;
					break;
				}	
			}
		if(flag==true)
			{
				phaseEvaluationServiceimpl.phaseevaluationRepository.insertPhaseData(Integer.parseInt(submissionID), 1, overallAccept,
						phase1Comment, Float.parseFloat(points));
			}
		
        return true;
    }
    
	//Saving comments for Phase3
    @RequestMapping(value = "/saveCommentsForPhase3", method = RequestMethod.GET)
    public boolean saveCommentsForPhase3(@RequestParam("comments") String comments,
            @RequestParam("submissionID") String submissionID, @RequestParam("rocknameData") String rocknameData) throws JSONException{
           
        JSONObject jsonObj = new JSONObject(rocknameData);
        String overall = jsonObj.getString("overall");
        
        String points = jsonObj.getString("Points");
        
        List<TblPhaseevaluation> tblevaluationlist=phaseEvaluationServiceimpl.phaseevaluationRepository.findAll();
		boolean flag=true;
		for(TblPhaseevaluation tblphaseeval:tblevaluationlist){
			if(tblphaseeval.getTblSubmission().getSubmissionID()==Integer.parseInt(submissionID) && tblphaseeval.getTblPhasetype().getPhaseID()==3){
				 phaseEvaluationServiceimpl.phaseevaluationRepository.updatePhaseData(Integer.parseInt(submissionID), 3, overall,
					        comments, Float.parseFloat(points));
					flag=false;
					break;
				}	
			}
		if(flag==true)
			{
			 phaseEvaluationServiceimpl.phaseevaluationRepository.insertPhaseData(Integer.parseInt(submissionID), 3, overall,
				        comments, Float.parseFloat(points));
			}
     
        return true;
    }
    
    @RequestMapping(value = "/getFeedbackbyPhase/{submissionId}/{phaseId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<String> getFeedbackbyPhase(@PathVariable("submissionId") String submissionId,@PathVariable("phaseId") String phaseId) {
    	List<String> feedback=new ArrayList<>();
    	List<TblPhaseevaluation> phaseeval=phaseEvaluationServiceimpl.phaseevaluationRepository.findFeedbackBySubmissionId(Integer.parseInt(submissionId));
        for(TblPhaseevaluation phaseEvaluation:phaseeval){
        	if(phaseEvaluation.getId().getPhase()==Integer.parseInt(phaseId)){
        		feedback.add(phaseEvaluation.getComments());
        		feedback.add(phaseEvaluation.getPoints()+"");
        		feedback.add(phaseEvaluation.getIsAccepted()+"");
        	}        	
        }
        return feedback;
    }
    @RequestMapping(value = "/getTotalPoints/{submissionId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TblPhaseevaluation> getTotalPoints(@PathVariable("submissionId") String submissionId) {
    	 List<TblPhaseevaluation> totalPhasespoints=phaseEvaluationServiceimpl.phaseevaluationRepository.findTotalPoints(Integer.parseInt(submissionId));
    	
    	
    System.out.println(totalPhasespoints);
    	//int totalpoints;
    	
    
    
    return totalPhasespoints;
    }
    
}
