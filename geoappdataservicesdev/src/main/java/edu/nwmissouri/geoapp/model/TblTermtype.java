package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_termtype database table.
 * 
 */
@Entity
@Table(name="tbl_termtype")
@NamedQuery(name="TblTermtype.findAll", query="SELECT t FROM TblTermtype t")
public class TblTermtype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int termID;

	private int dispayOrder;

	private String termName;

	//bi-directional many-to-one association to TblSection
	@OneToMany(mappedBy="tblTermtype")
	private List<TblSection> tblSections;

	public TblTermtype() {
	}

	public int getTermID() {
		return this.termID;
	}

	public void setTermID(int termID) {
		this.termID = termID;
	}

	public int getDispayOrder() {
		return this.dispayOrder;
	}

	public void setDispayOrder(int dispayOrder) {
		this.dispayOrder = dispayOrder;
	}

	public String getTermName() {
		return this.termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public List<TblSection> getTblSections() {
		return this.tblSections;
	}

	public void setTblSections(List<TblSection> tblSections) {
		this.tblSections = tblSections;
	}

	public TblSection addTblSection(TblSection tblSection) {
		getTblSections().add(tblSection);
		tblSection.setTblTermtype(this);

		return tblSection;
	}

	public TblSection removeTblSection(TblSection tblSection) {
		getTblSections().remove(tblSection);
		tblSection.setTblTermtype(null);

		return tblSection;
	}

}