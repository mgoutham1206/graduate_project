package edu.nwmissouri.geoapp.generalinfo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class rocktypes {
	
	@Id
    @Field
    private String _id;
    @Field
    private String sedimentary;
    @Field
	private String igneous;
    @Field
    private String metamorphic;
	
    public rocktypes(String _id, String sedimentary, String igneous, String metamorphic) {
		//super();
		this._id = _id;
		this.sedimentary = sedimentary;
		this.igneous = igneous;
		this.metamorphic = metamorphic;
	}

	public rocktypes() {
		//super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getSedimentary() {
		return sedimentary;
	}

	public void setSedimentary(String sedimentary) {
		this.sedimentary = sedimentary;
	}

	public String getIgneous() {
		return igneous;
	}

	public void setIgneous(String igneous) {
		this.igneous = igneous;
	}

	public String getMetamorphic() {
		return metamorphic;
	}

	public void setMetamorphic(String metamorphic) {
		this.metamorphic = metamorphic;
	}

	@Override
	public String toString() {
		return "rocktypes [_id=" + _id + ", sedimentary=" + sedimentary + ", igneous=" + igneous + ", metamorphic="
				+ metamorphic + "]";
	}
    
    
}
