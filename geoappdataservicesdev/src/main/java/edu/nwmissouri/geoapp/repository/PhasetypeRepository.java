package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblPhasetype;

public interface PhasetypeRepository  extends JpaRepository<TblPhasetype, Integer> {

}
