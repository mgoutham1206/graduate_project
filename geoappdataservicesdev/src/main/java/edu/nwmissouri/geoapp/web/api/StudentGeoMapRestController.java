package edu.nwmissouri.geoapp.web.api;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.nwmissouri.geoapp.serviceImpl.ImageSubmissionServiceImpl;
import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblSubmission;

@RestController
@RequestMapping("/ds")
public class StudentGeoMapRestController {

	@Autowired
	private ImageSubmissionServiceImpl imageSubmissionServiceImpl;

	// To fetch ImageInfo based on Instructor related sections--{InstructorID}
	@RequestMapping(value = "/geomap/allimagesinfo/instructor/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TblImagesubmission> getAllInstrutorMapPinInfo(@PathVariable("userId") String userId) {
		return imageSubmissionServiceImpl.getAllImagesByInstructor(userId);
	}

	//To fetch ImageInfo based on Instructor related sections based on rocktype--{userId}{rocktype}
	@RequestMapping(value = "/geomap/allimagesinfo/instructor/byrocktype/{userId}/{rockType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TblImagesubmission> getByRockTypeforInstructor(@PathVariable("userId") String userId,
			@PathVariable("rockType") String rockType) {
		return imageSubmissionServiceImpl.getAllImagesByInstructorByRockType(userId, rockType);
	}

	//To fetch ImageInfo based on Student own submissions--{userId}
	@RequestMapping(value = "/geomap/allimagesinfo/student/{studentID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TblImagesubmission> getImageInfosByStudent(@PathVariable("studentID") String studentID) {
		return imageSubmissionServiceImpl.getImagesByStudent(studentID);

	}

	// To fetch ImageInfo based on Student own submissions based on rocktype--{userId}{rocktype}
	@RequestMapping(value = "/geomap/allimagesinfo/student/byrocktype/{studentID}/{RockType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TblImagesubmission> getImageInfosByStudentandrock(@PathVariable("studentID") String studentID,
			@PathVariable("RockType") String RockType) {
		return imageSubmissionServiceImpl.getImagesByStudentandrock(studentID, RockType);

	}
	// To fetch Base Images which are stored at beginning of class creation.
	@RequestMapping(value = "/geomap/allimagesinfo/student/Base", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TblImagesubmission> getAllBaseImages() {
		return imageSubmissionServiceImpl.getAllBaseImages();

	}
	// To fetch ImageInfo based on Student related sections--{userId}
	@RequestMapping(value = "/geomap/allimagesinfo/student/class/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TblImagesubmission> getByClass(@PathVariable("userId") String userId) {
		return imageSubmissionServiceImpl.getImagesByClass(userId);

	}
	
	// To fetch ImageInfo based on Student related sections by Rocktype--{userId}/{RockType}
		@RequestMapping(value = "/geomap/allimagesinfo/student/class/byrocktype/{userId}/{RockType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<TblImagesubmission> getByClassByRockType(@PathVariable("userId") String userId,@PathVariable("RockType") String RockType) {
			return imageSubmissionServiceImpl.getImagesofClassByRockType(userId, RockType);

		}
	// To fetch Change class access
		@RequestMapping(value = "/geomap/changeclassAccess/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public Map<String, List<TblSubmission>> getClassChangeAccessData(@PathVariable("userId") String userId) {
			return imageSubmissionServiceImpl.getSubmisssionsBySection(userId);

		}
		@RequestMapping(value = "/geomap/changeclassAccess/save", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public boolean setIsShowForSectionImagesByInstructor(@RequestParam("userId") String insId,
				@RequestParam("imageIDData") String[] imageIDData){
			System.out.println(imageIDData);
			System.out.println(imageIDData.length);
			
			Map<String, List<TblSubmission>> imageListBySec = imageSubmissionServiceImpl.getSubmisssionsBySection(insId);
			try{
				//System.out.println("I'm in try");
				for(String sec : imageListBySec.keySet()){
					//System.out.println("I found section and in sec= "+sec);
					for(TblSubmission submission : imageListBySec.get(sec)){
						//System.out.println("I found submissions and in submission= "+submission.getSubmissionID());
						for(TblImagesubmission imageInfo : submission.getTblImagesubmissions()){
							//System.out.println("I found imageInfo and in image= "+imageInfo.getImageID());
							if(imageInfo.getIsBest().equals("Y")){
								imageInfo.setIsShow("N");
								imageSubmissionServiceImpl.save(imageInfo);
								//System.out.println(temp.getImageID()+"   "+temp.getIsShow()+" ");
							}
						}
					}
				}
				for(String strId : imageIDData){
					//System.out.println("In adding new Images List");
					int imageId = Integer.parseInt(strId);
					TblImagesubmission tempImage= imageSubmissionServiceImpl.findOne(imageId);
					tempImage.setIsShow("Y");
					imageSubmissionServiceImpl.save(tempImage);
					//System.out.println(temp.getImageID()+"   "+temp.getIsShow()+" ");
				}
			}
			catch(Exception e){
				//System.out.println("Not found any or error facing.");
				return false;
			}
			
			return true;
		}

}
