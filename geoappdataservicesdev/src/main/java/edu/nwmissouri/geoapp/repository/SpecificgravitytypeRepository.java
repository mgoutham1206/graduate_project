package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblSpecificgravitytype;

public interface SpecificgravitytypeRepository  extends JpaRepository<TblSpecificgravitytype, Integer> {

}
