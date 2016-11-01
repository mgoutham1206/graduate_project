package edu.nwmissouri.geoapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nwmissouri.geoapp.model.TblAttributeevaluation;
import edu.nwmissouri.geoapp.model.TblAttributeevaluationPK;
import edu.nwmissouri.geoapp.repository.AttributeevaluationRepository;
import edu.nwmissouri.geoapp.service.AttributeEvaluationService;


@Service
public class AttributeEvaluationServiceImpl  implements AttributeEvaluationService{

	@Autowired
	public AttributeevaluationRepository attributeEvaluationRepository;
	

	@Override
	public void insertData(int MineralID, int AttributeID,String IsAccepted, String Comments) {
		// TODO Auto-generated method stub
		attributeEvaluationRepository.insertData(MineralID, AttributeID, IsAccepted, Comments);
	}
	
	@Override
	public void updateData(int MineralID,int AttributeID,String IsAccepted, String Comments ) {
		// TODO Auto-generated method stub
		attributeEvaluationRepository.updatePhaseData( MineralID,AttributeID,IsAccepted, Comments );
	}


	@Override
	public void deleteAttributeComments(Integer mineralID) {
		// TODO Auto-generated method stub
		attributeEvaluationRepository.deleteAttributeEvaluationByMineralID(mineralID);
	}

	

	


	


	
	
}
