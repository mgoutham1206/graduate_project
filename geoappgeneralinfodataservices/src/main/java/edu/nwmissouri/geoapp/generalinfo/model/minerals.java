package edu.nwmissouri.geoapp.generalinfo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class minerals {
	
	@Id
    @Field
    private String _id;
    @Field
    private String mineralintro;
    @Field
	private String typesintro;
    @Field
    private String economic;
    @Field
    private String industrial;
	
    public minerals(String _id, String mineralintro, String typesintro, String economic, String industrial) {
		//super();
		this._id = _id;
		this.mineralintro = mineralintro;
		this.typesintro = typesintro;
		this.economic = economic;
		this.industrial = industrial;
	}

	public minerals() {
		//super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getMineralintro() {
		return mineralintro;
	}

	public void setMineralintro(String mineralintro) {
		this.mineralintro = mineralintro;
	}

	public String getTypesintro() {
		return typesintro;
	}

	public void setTypesintro(String typesintro) {
		this.typesintro = typesintro;
	}

	public String getEconomic() {
		return economic;
	}

	public void setEconomic(String economic) {
		this.economic = economic;
	}

	public String getIndustrial() {
		return industrial;
	}

	public void setIndustrial(String industrial) {
		this.industrial = industrial;
	}

	@Override
	public String toString() {
		return "minerals [_id=" + _id + ", mineralintro=" + mineralintro + ", typesintro=" + typesintro + ", economic="
				+ economic + ", industrial=" + industrial + "]";
	}
	

    
}
