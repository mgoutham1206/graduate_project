package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nwmissouri.geoapp.model.TblLustertype;

public interface LusterRepository extends JpaRepository<TblLustertype, Integer> {

}
