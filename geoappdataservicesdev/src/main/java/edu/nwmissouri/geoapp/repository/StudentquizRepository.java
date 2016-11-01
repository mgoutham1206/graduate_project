/**
 * 
 */
package edu.nwmissouri.geoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.nwmissouri.geoapp.model.TblStudentquiz;

/**
 * @author S522572
 *
 */
public interface StudentquizRepository extends JpaRepository<TblStudentquiz, Integer> {
	
	@Query("select a from TblStudentquiz a where a.tblStudent.studentID=?1 and a.tblQuiz.quizID =?2")
	public TblStudentquiz findTblStudentquizbystuIDnquizID(int studentID, int quizID);

}
