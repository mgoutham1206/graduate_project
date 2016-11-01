package edu.nwmissouri.geoapp.generalinfo.model;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class nonmetallic {

	@Id
    @Field
    private String _id;
    @Field
    private double hardnessmin;
    @Field
    private double hardnessmax;
    @Field
    private double sgmin;
    @Field
    private double sgmax;
    @Field
    private List<String> streakcolor;
    @Field
    private List<String> color;
    @Field
    private String cleavage;
    @Field
    private int sets;
    @Field
    private int angle;
    @Field
    private String mineral;
    
    public nonmetallic() {
		super();
	}

    public nonmetallic(String _id, double hardnessmin, double hardnessmax, double sgmin, double sgmax,
			List<String> streakcolor, List<String> color, String cleavage, int sets, int angle, String mineral) {
		this._id = _id;
		this.hardnessmin = hardnessmin;
		this.hardnessmax = hardnessmax;
		this.sgmin = sgmin;
		this.sgmax = sgmax;
		this.streakcolor = streakcolor;
		this.color = color;
		this.cleavage = cleavage;
		this.sets = sets;
		this.angle = angle;
		this.mineral = mineral;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public double getHardnessmin() {
		return hardnessmin;
	}

	public void setHardnessmin(double hardnessmin) {
		this.hardnessmin = hardnessmin;
	}

	public double getHardnessmax() {
		return hardnessmax;
	}

	public void setHardnessmax(double hardnessmax) {
		this.hardnessmax = hardnessmax;
	}

	public double getSgmin() {
		return sgmin;
	}

	public void setSgmin(double sgmin) {
		this.sgmin = sgmin;
	}

	public double getSgmax() {
		return sgmax;
	}

	public void setSgmax(double sgmax) {
		this.sgmax = sgmax;
	}

	public List<String> getStreakcolor() {
		return streakcolor;
	}

	public void setStreakcolor(List<String> streakcolor) {
		this.streakcolor = streakcolor;
	}

	public List<String> getColor() {
		return color;
	}

	public void setColor(List<String> color) {
		this.color = color;
	}

	public String getCleavage() {
		return cleavage;
	}

	public void setCleavage(String cleavage) {
		this.cleavage = cleavage;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public String getMineral() {
		return mineral;
	}

	public void setMineral(String mineral) {
		this.mineral = mineral;
	}

	@Override
	public String toString() {
		return "nonmetallic [_id=" + _id + ", hardnessmin=" + hardnessmin + ", hardnessmax=" + hardnessmax + ", sgmin="
				+ sgmin + ", sgmax=" + sgmax + ", streakcolor=" + streakcolor + ", color=" + color + ", cleavage="
				+ cleavage + ", sets=" + sets + ", angle=" + angle + ", mineral=" + mineral + "]";
	}
    
    
}
