package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nwmissouri.geoapp.model.TblInstructorsection;
import edu.nwmissouri.geoapp.model.TblLustertype;

public interface InstructorsectionRepository  extends JpaRepository<TblInstructorsection, Integer> {

}
