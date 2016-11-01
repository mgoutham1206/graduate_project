package edu.nwmissouri.geoapp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_streakcolortype database table.
 * 
 */
@Entity
@Table(name="tbl_streakcolortype")
@NamedQuery(name="TblStreakcolortype.findAll", query="SELECT t FROM TblStreakcolortype t")
public class TblStreakcolortype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int streakColorID;

	private String streakColorName;

	private int streakColorOrder;

	public TblStreakcolortype() {
	}

	public int getStreakColorID() {
		return this.streakColorID;
	}

	public void setStreakColorID(int streakColorID) {
		this.streakColorID = streakColorID;
	}

	public String getStreakColorName() {
		return this.streakColorName;
	}

	public void setStreakColorName(String streakColorName) {
		this.streakColorName = streakColorName;
	}

	public int getStreakColorOrder() {
		return this.streakColorOrder;
	}

	public void setStreakColorOrder(int streakColorOrder) {
		this.streakColorOrder = streakColorOrder;
	}

}