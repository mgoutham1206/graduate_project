package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nwmissouri.geoapp.model.TblAttributetype;


public interface AttributetypeRepository  extends JpaRepository<TblAttributetype, Integer> {

}
