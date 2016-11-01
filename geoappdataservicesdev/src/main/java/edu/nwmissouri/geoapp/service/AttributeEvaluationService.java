package edu.nwmissouri.geoapp.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblAttributeevaluation;
import edu.nwmissouri.geoapp.model.TblAttributeevaluationPK;
import edu.nwmissouri.geoapp.repository.AttributeevaluationRepository;

public interface AttributeEvaluationService {
	
	public void insertData(int MineralID, int AttributeID, String IsAccepted, String Comments);

	public void deleteAttributeComments(Integer mineralID);
	
	public void updateData(int MineralID,int AttributeID,String IsAccepted, String Comments);
	
	
}
