
package edu.nwmissouri.geoapp.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblInstructorsection;
import edu.nwmissouri.geoapp.model.TblPhaseevaluation;
import edu.nwmissouri.geoapp.model.TblPhasetype;
import edu.nwmissouri.geoapp.model.TblRocktype;
import edu.nwmissouri.geoapp.model.TblSection;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblSubmission;
import edu.nwmissouri.geoapp.model.TblUser;
import edu.nwmissouri.geoapp.repository.ImagesubmissionRepository;
import edu.nwmissouri.geoapp.repository.PhaseevaluationRepository;
import edu.nwmissouri.geoapp.repository.PhasetypeRepository;
import edu.nwmissouri.geoapp.repository.RockSubmissionRepository;
import edu.nwmissouri.geoapp.repository.RocktypeRepository;
import edu.nwmissouri.geoapp.repository.SectionRepository;
import edu.nwmissouri.geoapp.repository.UserRepository;
import edu.nwmissouri.geoapp.service.ImageSubmissionService;

@Service
public class ImageSubmissionServiceImpl implements ImageSubmissionService {

	@Autowired
	private ImagesubmissionRepository imagesubmissionRepository;
	@Autowired
	private RockSubmissionRepository submissionrepo;
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RocktypeRepository rock;

	@Autowired
	private SectionRepository sectionRepo;
	
	@Autowired
	private PhaseevaluationRepository phaseevaluationRepository;
	
	@Autowired
	private PhasetypeRepository phaseTypeRepository;
	
	@Autowired
	private PhaseevaluationRepository phaseEvaluationRepository;

	@Override
	public TblImagesubmission save(TblImagesubmission Image) throws Exception {
		return imagesubmissionRepository.save(Image);
	}

	@Override
	public TblImagesubmission findOne(int imageID) {
		TblImagesubmission tblImagesubmission = imagesubmissionRepository.findOne(imageID);
		return tblImagesubmission;
	}

	@Override
	public Map<String, Object> findAll() {
		Map<String, Object> imageMap = new HashMap<String, Object>();
		List<TblImagesubmission> tblimagesubmission = imagesubmissionRepository.findAll();
		imageMap.put("imagesubmission", tblimagesubmission);
		return imageMap;
	}

	@Override
	public List<TblImagesubmission> findAllImages() {

		return imagesubmissionRepository.findAll();
		
	}

	// Working - Fetch All Images by Instructor corresponding to his sections
		public List<TblImagesubmission> getAllImagesByInstructor(String userId) {
			int userIdInt = Integer.parseInt(userId);
			List<TblImagesubmission> imagesList = new ArrayList<>();
			TblUser userInfo = userRepo.findOne(userIdInt);

			List<TblInstructorsection> instructorSectionList = userInfo.getTblInstructorsections();
			List<TblSection> sectionList = new ArrayList<TblSection>();
			for (TblInstructorsection tempSection : instructorSectionList) {
				sectionList.add(tempSection.getTblSection());
			}
			int count = 0;
			for (TblSection sectionInfo : sectionList) {

				List<TblStudent> allStudentsInClass = sectionInfo.getTblStudents();
				
				if (!allStudentsInClass.isEmpty()) {
					// If the section have list of students in it.
					for (TblStudent student : allStudentsInClass) {
						List<TblSubmission> studentSubmissions = student.getTblUser().getTblSubmissions();
						if (!studentSubmissions.isEmpty()) {
							// If the existing record has list of submissions.
							for (TblSubmission submission : studentSubmissions) {
								if (count == 0) {
									imagesList = submission.getTblImagesubmissions();
									count++;
								} else {

									imagesList.addAll(submission.getTblImagesubmissions());
								}
							}
						}
					}

				}
			}
			return imagesList;
		}

		// End

		// Working Fetch ImageInfo of Instructor Sections based on rocktype

		public List<TblImagesubmission> getAllImagesByInstructorByRockType(String userId, String rockType) {

			int userIdInt = Integer.parseInt(userId);
			TblUser userInfo = userRepo.findOne(userIdInt);
			List<TblInstructorsection> instructorSectionList = userInfo.getTblInstructorsections();
			List<TblSection> sectionList = new ArrayList<TblSection>();
			List<TblImagesubmission> imagesList = new ArrayList<>();
			for (TblInstructorsection tempSection : instructorSectionList) {
				sectionList.add(tempSection.getTblSection());
			}
			int count = 0;
			for (TblSection sectionInfo : sectionList) {
				List<TblStudent> allStudentsInClass = sectionInfo.getTblStudents();
				System.out.println("AllStudentsInclass" + allStudentsInClass.size());
				
				if (!allStudentsInClass.isEmpty()) {
					// If the section have list of students in it.
					for (TblStudent student : allStudentsInClass) {
						List<TblSubmission> studentSubmissions = student.getTblUser().getTblSubmissions();
						System.out.println("StudentSubmissions" + studentSubmissions.size());
						if (!studentSubmissions.isEmpty()) {
							for (TblSubmission submission : studentSubmissions) {
								if (count == 0) {
									if (rockType.equals(submission.getRockType())) {
										imagesList = submission.getTblImagesubmissions();
										count++;
									}

								} else {
									if (rockType.equals(submission.getRockType())) {
										imagesList.addAll(submission.getTblImagesubmissions());
									}
								}
							}
						}
						System.out.println(imagesList.size());
					}

				}
			}
			return imagesList;
		}
		// End

	// Working --Fetch All Images by Student corresponding to his submissions
	public List<TblImagesubmission> getImagesByStudent(String userId) {

		int userIdInt = Integer.parseInt(userId);
		int count = 0;
		TblUser userInfo = userRepo.findOne(userIdInt);

		List<TblSubmission> tblSubmissions = userInfo.getTblSubmissions();
		List<TblImagesubmission> imagesList = new ArrayList<>();

		for (TblSubmission submission : tblSubmissions) {
			if (count == 0) {
				imagesList = submission.getTblImagesubmissions();
				count++;
			} else {
				imagesList.addAll(submission.getTblImagesubmissions());
			}

		}
		return imagesList;

	}

	// End

	// Working --Fetch All Images by Student corresponding to his submissions of
	// specific rocktype

	public List<TblImagesubmission> getImagesByStudentandrock(String userId, String rocktype) {
		int userIdInt = Integer.parseInt(userId);
		int count = 0;
		TblUser userInfo = userRepo.findOne(userIdInt);
		List<TblSubmission> tblSubmissions = userInfo.getTblSubmissions();
		List<TblImagesubmission> imagesList = new ArrayList<>();
		for (TblSubmission s : tblSubmissions) {
			if (count == 0) {
				if (rocktype.equals(s.getRockType())) {
					imagesList = s.getTblImagesubmissions();
					count++;
				}

			} else {
				if (rocktype.equals(s.getRockType())) {
					imagesList.addAll(s.getTblImagesubmissions());
				}
			}

		}
		return imagesList;
	}

	//End

	// Working --Fetch All Images by Student related to his section.

	public List<TblImagesubmission> getImagesByClass(String userId) {

		int userIdInt = Integer.parseInt(userId);

		TblUser userInfo = userRepo.findOne(userIdInt);

		TblStudent studentInfo = userInfo.getTblStudent();

		List<TblImagesubmission> imagesList = new ArrayList<>();
		List<TblStudent> allStudentsInClass = studentInfo.getTblSection().getTblStudents();
		int count = 0;
		if (!allStudentsInClass.isEmpty()) {
			// If the section have list of students in it.
			for (TblStudent student : allStudentsInClass) {
				List<TblSubmission> studentSubmissions = student.getTblUser().getTblSubmissions();
				if (!studentSubmissions.isEmpty()) {
					// If the existing record has list of submissions.
					for (TblSubmission submission : studentSubmissions) {
						if (count == 0) {
							imagesList = submission.getTblImagesubmissions();
							count++;
						} else {
							imagesList.addAll(submission.getTblImagesubmissions());
						}
					}
				}
			}

		}

		return imagesList;
	}

	// End 
	//Working --Fetch All Images by Student related to his section based on Rocktype
	public List<TblImagesubmission> getImagesofClassByRockType(String userId,String rockType) {

		int userIdInt = Integer.parseInt(userId);

		TblUser userInfo = userRepo.findOne(userIdInt);

		TblStudent studentInfo = userInfo.getTblStudent();

		List<TblImagesubmission> imagesList = new ArrayList<>();
		List<TblStudent> allStudentsInClass = studentInfo.getTblSection().getTblStudents();
		int count = 0;
		if (!allStudentsInClass.isEmpty()) {
			// If the section have list of students in it.
			for (TblStudent student : allStudentsInClass) {
				List<TblSubmission> studentSubmissions = student.getTblUser().getTblSubmissions();
				if (!studentSubmissions.isEmpty()) {
					// If the existing record has list of submissions.
					for (TblSubmission submission : studentSubmissions) {
						if (count == 0) {
							if (rockType.equals(submission.getRockType())) {
							imagesList = submission.getTblImagesubmissions();
							count++;
							}
						} else {
							if (rockType.equals(submission.getRockType())) {
							imagesList.addAll(submission.getTblImagesubmissions());
							}
						}
					}
				}
			}

		}

		return imagesList;
	}

	//End
	

	// Start Vijay - Class Image Access Visibilty Display
	public Map<String, Map<TblSubmission, List<TblImagesubmission>>> getImagesInfoToDisplayClassPinsVisibilty(
			String userId) {

		int userIdInt = Integer.parseInt(userId);

		Map<String, Map<TblSubmission, List<TblImagesubmission>>> resultMap = new TreeMap<String, Map<TblSubmission, List<TblImagesubmission>>>();
		Map<TblSubmission, List<TblImagesubmission>> submissionTree = new TreeMap<TblSubmission, List<TblImagesubmission>>();

		TblUser userInfo = userRepo.findOne(userIdInt);

		List<TblInstructorsection> instructorSectionList = userInfo.getTblInstructorsections();
		List<TblSection> sectionList = new ArrayList<TblSection>();

		for (TblInstructorsection tempSection : instructorSectionList) {
			sectionList.add(tempSection.getTblSection());
		}
		int count = 0;
		for (TblSection sectionInfo : sectionList) {
			List<TblStudent> allStudentsInClass = sectionInfo.getTblStudents();
			
			List<TblImagesubmission> imagesList = new ArrayList<>();
			if (!allStudentsInClass.isEmpty()) {
				// If the section have list of students in it.
				for (TblStudent student : allStudentsInClass) {
					List<TblSubmission> studentSubmissions = student.getTblUser().getTblSubmissions();
					if (!studentSubmissions.isEmpty()) {
						// If the existing record has list of submissions.
						String rockName, rockType;
						for (TblSubmission submission : studentSubmissions) {

							if (count == 0) {
								rockName = submission.getRockName();
								rockType = submission.getRockType();
								imagesList = submission.getTblImagesubmissions();
								submissionTree.put(submission, imagesList);
								count++;
							} else {
								rockName = submission.getRockName();
								rockType = submission.getRockType();
								imagesList.addAll(submission.getTblImagesubmissions());
								submissionTree.put(submission, imagesList);
							}
						}
					}
				}

				resultMap.put(sectionInfo.getSectionID() + "", submissionTree);
			}
		}
		return resultMap;
	}

	// End Vijay - Class Image Access Visibilty Display

	
	// Working --- Section Submissions by Instructor User Id for Change CLass Access
	public Map<String, List<TblSubmission>> getSubmisssionsBySection(String userId) {
		Map<String, List<TblSubmission>> submissionListBySection = new TreeMap<>();

		int userIdInt = Integer.parseInt(userId);

		TblUser userInfo = userRepo.findOne(userIdInt);

		List<TblInstructorsection> instructorSectionList = userInfo.getTblInstructorsections();

		List<TblSection> sectionList = new ArrayList<TblSection>();

		for (TblInstructorsection tempSection : instructorSectionList) {
			sectionList.add(tempSection.getTblSection());
		}

		for (TblSection sectionInfo : sectionList) {
			List<TblStudent> allStudentsInClass = sectionInfo.getTblStudents();

			for (TblStudent student : allStudentsInClass) {
				List<TblSubmission> tempSubmission;
				if (submissionListBySection.containsKey(sectionInfo.getSectionID() + "")) {
					tempSubmission = submissionListBySection.get(sectionInfo.getSectionID() + "");
					tempSubmission.addAll(student.getTblUser().getTblSubmissions());
					submissionListBySection.put(sectionInfo.getSectionID() + "", tempSubmission);
				} else {
					submissionListBySection.put(sectionInfo.getSectionID() + "",
							student.getTblUser().getTblSubmissions());
					
					//System.out.println("@@@@@@@@@@@@@2"+student.getTblUser().getTblSubmissions().get(0).getTblImagesubmissions().get(0).getImage());
				}
			}
		}
		
		return submissionListBySection;
	}

	// End Vijay --- Section Submissions by Instructor User Id
	
	
	// Working --To Fetch Base Images for Student 

	public List<TblImagesubmission> getAllBaseImages(){
		return imagesubmissionRepository.findBaseImages();
	}
	
	
	
	/** Start of phaseoneservices by Android team **/
	@Override
	public TblSubmission submitPhaseOne(TblSubmission phaseonesubmission) {
		return submissionrepo.save(phaseonesubmission);

	}

	public List<TblImagesubmission> fetchImagesforPhaseOne(int submissionId) {
		return imagesubmissionRepository.findImagesBySubmissionId(submissionId);
	}
	
	@Override
	public void deleteImagesforResubmission(int submissionId) {
		 imagesubmissionRepository.deleteImagesBySubmissionId(submissionId);
		
	}
	
	public void savenewSubmission(List<TblImagesubmission> newImageList){
		imagesubmissionRepository.save(newImageList);
	}
	
	public List<TblPhaseevaluation> getPhaseOneStatus(int submissionId){
		return phaseevaluationRepository.findFeedbackBySubmissionId(submissionId);
	}
	
	public TblPhasetype getPhaseType(int phaseId){
		return phaseTypeRepository.getOne(phaseId);
	}
	
	public void deletePhaseOneEvaluation(int submissionId,int phase){
		  phaseevaluationRepository.deletePhaseOneEvalForSubmission(submissionId,phase);
	}
	/** End of phase one services by Android team **/

	@Override
	public void evictCache() {
		// TODO Auto-generated method stub

	}

	

	
}
