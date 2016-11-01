package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.nwmissouri.geoapp.model.TblLustertype;
import edu.nwmissouri.geoapp.model.TblStreakcolortype;

public interface StreakcolortypeRepository  extends JpaRepository<TblStreakcolortype, Integer> {

}
