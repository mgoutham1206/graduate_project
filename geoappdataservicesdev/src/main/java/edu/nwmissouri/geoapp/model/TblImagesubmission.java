package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;


/**
 * The persistent class for the tbl_imagesubmission database table.
 * 
 */
@Entity
@Table(name="tbl_imagesubmission")
@NamedQuery(name="TblImagesubmission.findAll", query="SELECT t FROM TblImagesubmission t")

public class TblImagesubmission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int imageID;

	private String createdBy;

	private Timestamp createdDate;

	@Lob
	private byte[] image;

	private String imageLocation;

	private Integer imageOrder;

	private String isBest;

	private String isShow;

	private String latitude;

	private String longitude;

	private String updatedBy;
	
	private String description;

	private Timestamp updatedDate;
	
	private String IsAccepted;
	
	
	private String comments;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	//bi-directional many-to-one association to TblSubmission
	@ManyToOne
	@JoinColumn(name="SubmissionID")
	@JsonIgnore
	private TblSubmission tblSubmission;

	public TblImagesubmission() {
	}

	public int getImageID() {
		return this.imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
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

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageLocation() {
		return this.imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public int getImageOrder() {
		return this.imageOrder;
	}

	public void setImageOrder(int imageOrder) {
		this.imageOrder = imageOrder;
	}

	public String getIsBest() {
		return this.isBest;
	}

	public void setIsBest(String isBest) {
		this.isBest = isBest;
	}

	public String getIsShow() {
		return this.isShow;
	}
	
	

	public String getIsAccepted() {
		return IsAccepted;
	}

	public void setIsAccepted(String isAccepted) {
		IsAccepted = isAccepted;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
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

	public TblSubmission getTblSubmission() {
		return this.tblSubmission;
	}

	public void setTblSubmission(TblSubmission tblSubmission) {
		this.tblSubmission = tblSubmission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}