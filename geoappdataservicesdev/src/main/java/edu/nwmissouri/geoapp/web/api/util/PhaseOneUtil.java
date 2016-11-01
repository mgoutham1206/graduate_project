package edu.nwmissouri.geoapp.web.api.util;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import edu.nwmissouri.geoapp.model.TblImagesubmission;
import edu.nwmissouri.geoapp.model.TblSubmission;

public class PhaseOneUtil {

	public static byte[] encodeImageAsStringtoByte(String encodedImageStr){
		
		System.out.println("in encodeStringtoImage method");
		byte[] imageByteArray = Base64.getDecoder().decode(encodedImageStr.getBytes());
		System.out.println(imageByteArray.length);
		return imageByteArray;
	}
	
	
	public static List<TblImagesubmission> getImagesAsBytes(PhaseOneForm phaseOneForm,TblSubmission tblSubmission){
		System.out.println("in getImagesAsBytes mehod");
		List<TblImagesubmission> listImages = new ArrayList<TblImagesubmission>();
		System.out.println("phaseoneform size"+phaseOneForm.getImageList().size());
		for(String encodedImageString : phaseOneForm.getImageList()){
			byte[] image = encodeImageAsStringtoByte(encodedImageString);
			System.out.println("encodedImageString"+image);
			TblImagesubmission tblImagesubmissions = new TblImagesubmission();
			tblImagesubmissions.setTblSubmission(tblSubmission);
			tblImagesubmissions.setImage(image);
			tblImagesubmissions.setImageLocation("");
			tblImagesubmissions.setIsBest("");
			tblImagesubmissions.setImageOrder(1);
			tblImagesubmissions.setLatitude(phaseOneForm.getLatitude());
			tblImagesubmissions.setLongitude(phaseOneForm.getLongitude());
			tblImagesubmissions.setDescription(phaseOneForm.getDescription());
			tblImagesubmissions.setIsShow(null);
			listImages.add(tblImagesubmissions);
			
		}
		
		return listImages;
	}
	
	
	public static String encodeImageAsBytetoString(byte[] imageasbyte){
		
		System.out.println("in encodeImageAsBytetoString method");
		String imageasString = Base64.getEncoder().encodeToString(imageasbyte);
		return imageasString;
	}
		
	public static List<String> getImagesAsString(List<TblImagesubmission> imageList){
		System.out.println("in getImagesAsString mehod");
		List<String> imageasStringList = new ArrayList<>();
		for(TblImagesubmission imageItem : imageList){
			String imageString = encodeImageAsBytetoString(imageItem.getImage());
			imageasStringList.add(imageString);
		}
		System.out.println("image strings"+imageasStringList.size());
		return imageasStringList;
	}
}
