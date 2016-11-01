package edu.nwmissouri.geoapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import edu.nwmissouri.geoapp.model.TblSection;

public interface SectionService {
	public void saveSection(TblSection tblSection);
	public List<TblSection> getSectionData();
	public TblSection getSectionByID(int id);
	public void updateSection(TblSection tblSection);
	public List<TblSection> getSecByInstructor(String loginName);
//	public TblTermtype getSectionByIDTT(int id);
	public void deleteSection(int secId);
}
