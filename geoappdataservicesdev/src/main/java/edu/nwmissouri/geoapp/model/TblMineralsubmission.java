package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the tbl_mineralsubmission database table.
 * 
 */
@Entity
@Table(name="tbl_mineralsubmission")
@NamedQuery(name="TblMineralsubmission.findAll", query="SELECT t FROM TblMineralsubmission t")
public class TblMineralsubmission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer mineralID;

	private String color;
	

	private String createdBy;

	private Timestamp createdDate;

	private Integer displayOrder;

	private String geometry;

	private String hardness;

	private String luster;

	private String name;

	private String specificGravity;

	private String streakColor;

	private String updatedBy;

	private Timestamp updatedDate;

	//bi-directional many-to-one association to TblAttributeevaluation
	@OneToMany(mappedBy="tblMineralsubmission", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<TblAttributeevaluation> tblAttributeevaluations = new ArrayList<>();

	//bi-directional many-to-one association to TblSubmission
	@ManyToOne
	@JoinColumn(name="SubmissionID")
	@JsonIgnore
	private TblSubmission tblSubmission;

	
	/*Get and setters for the class variables */
	public TblMineralsubmission() {
	}

	public Integer getMineralID() {
		return this.mineralID;
	}

	public void setMineralID(Integer mineralID) {
		this.mineralID = mineralID;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public int getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getGeometry() {
		return this.geometry;
	}

	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}

	public String getHardness() {
		return this.hardness;
	}

	public void setHardness(String hardness) {
		this.hardness = hardness;
	}

	public String getLuster() {
		return this.luster;
	}

	public void setLuster(String luster) {
		this.luster = luster;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecificGravity() {
		return this.specificGravity;
	}

	public void setSpecificGravity(String specificGravity) {
		this.specificGravity = specificGravity;
	}

	public String getStreakColor() {
		return this.streakColor;
	}

	public void setStreakColor(String streakColor) {
		this.streakColor = streakColor;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	/*Returns attribute evaluations list of objects  */
	public List<TblAttributeevaluation> getTblAttributeevaluations() {
		return this.tblAttributeevaluations;
	}

	public void setTblAttributeevaluations(List<TblAttributeevaluation> tblAttributeevaluations) {
		this.tblAttributeevaluations = tblAttributeevaluations;
	}

	public TblAttributeevaluation addTblAttributeevaluation(TblAttributeevaluation tblAttributeevaluation) {
		getTblAttributeevaluations().add(tblAttributeevaluation);
		tblAttributeevaluation.setTblMineralsubmission(this);

		return tblAttributeevaluation;
	}

	public TblAttributeevaluation removeTblAttributeevaluation(TblAttributeevaluation tblAttributeevaluation) {
		getTblAttributeevaluations().remove(tblAttributeevaluation);
		tblAttributeevaluation.setTblMineralsubmission(null);

		return tblAttributeevaluation;
	}

	public TblSubmission getTblSubmission() {
		return this.tblSubmission;
	}

	public void setTblSubmission(TblSubmission tblSubmission) {
		this.tblSubmission = tblSubmission;
	}

}