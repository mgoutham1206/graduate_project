/**
 * 
 */
package edu.nwmissouri.geoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblAssignment;
import edu.nwmissouri.geoapp.model.TblStudent;
import edu.nwmissouri.geoapp.model.TblStudentquiztake;


public interface StudentquiztakeRepository extends JpaRepository<TblStudentquiztake, Integer> {
	
	public TblStudentquiztake findTblStudentquiztakeBystudentQuizTakeID(int studentQuizTakeID);

	@Query("select count(studentID) from TblStudentquiztake a where a.tblStudent.studentID=?1 and a.tblQuiz.quizID =?2")
	public long getCountofNumTakes(int studentID, int quizID);
	
	@Query("select a from TblStudentquiztake a where a.tblStudent.studentID=?1 and a.tblQuiz.quizID =?2 and a.takeNum=?3")
	public TblStudentquiztake findTblStudentquiztakeBymaxTakeNum(int studentID, int quizID, int takeNum);

	
}
