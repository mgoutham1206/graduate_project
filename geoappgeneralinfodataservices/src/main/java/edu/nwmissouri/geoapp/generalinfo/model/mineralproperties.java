package edu.nwmissouri.geoapp.generalinfo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class mineralproperties {
	
	@Id
    @Field
    private String _id;
    @Field
    private String luster;
    @Field
	private String hardness;
    @Field
    private String color;
    @Field
	private String streak;
	@Field
	private String cleavage;
	@Field
	private String fracture;
	@Field
	private String sg;
	@Field
	private String diapheneity;
	@Field
	private String magnetism;
	@Field
	private String effervescenceInAcid;
	@Field
	private String taste;
	@Field
	private String crystalForm;
	@Field
	private String feel;
	@Field
	private String smell;
	public mineralproperties(String _id, String luster, String hardness, String color, String streak, String cleavage,
			String fracture, String sg, String diapheneity, String magnetism, String effervescenceInAcid, String taste,
			String crystalForm, String feel, String smell) {
		//super();
		this._id = _id;
		this.luster = luster;
		this.hardness = hardness;
		this.color = color;
		this.streak = streak;
		this.cleavage = cleavage;
		this.fracture = fracture;
		this.sg = sg;
		this.diapheneity = diapheneity;
		this.magnetism = magnetism;
		this.effervescenceInAcid = effervescenceInAcid;
		this.taste = taste;
		this.crystalForm = crystalForm;
		this.feel = feel;
		this.smell = smell;
	}
	public mineralproperties() {
		//super();
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getLuster() {
		return luster;
	}
	public void setLuster(String luster) {
		this.luster = luster;
	}
	public String getHardness() {
		return hardness;
	}
	public void setHardness(String hardness) {
		this.hardness = hardness;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getStreak() {
		return streak;
	}
	public void setStreak(String streak) {
		this.streak = streak;
	}
	public String getCleavage() {
		return cleavage;
	}
	public void setCleavage(String cleavage) {
		this.cleavage = cleavage;
	}
	public String getFracture() {
		return fracture;
	}
	public void setFracture(String fracture) {
		this.fracture = fracture;
	}
	public String getSg() {
		return sg;
	}
	public void setSg(String sg) {
		this.sg = sg;
	}
	public String getDiapheneity() {
		return diapheneity;
	}
	public void setDiapheneity(String diapheneity) {
		this.diapheneity = diapheneity;
	}
	public String getMagnetism() {
		return magnetism;
	}
	public void setMagnetism(String magnetism) {
		this.magnetism = magnetism;
	}
	public String getEffervescenceInAcid() {
		return effervescenceInAcid;
	}
	public void setEffervescenceInAcid(String effervescenceInAcid) {
		this.effervescenceInAcid = effervescenceInAcid;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public String getCrystalForm() {
		return crystalForm;
	}
	public void setCrystalForm(String crystalForm) {
		this.crystalForm = crystalForm;
	}
	public String getFeel() {
		return feel;
	}
	public void setFeel(String feel) {
		this.feel = feel;
	}
	public String getSmell() {
		return smell;
	}
	public void setSmell(String smell) {
		this.smell = smell;
	}
	@Override
	public String toString() {
		return "mineralproperties [_id=" + _id + ", luster=" + luster + ", hardness=" + hardness + ", color=" + color
				+ ", streak=" + streak + ", cleavage=" + cleavage + ", fracture=" + fracture + ", sg=" + sg
				+ ", diapheneity=" + diapheneity + ", magnetism=" + magnetism + ", effervescenceInAcid="
				+ effervescenceInAcid + ", taste=" + taste + ", crystalForm=" + crystalForm + ", feel=" + feel
				+ ", smell=" + smell + "]";
	}
	
	
}
