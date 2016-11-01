package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nwmissouri.geoapp.model.TblGeometrytype;
import edu.nwmissouri.geoapp.model.TblLustertype;

public interface GeometrytypeRepository  extends JpaRepository<TblGeometrytype, Integer> {

}
