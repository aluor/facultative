package by.pvt.aliushkevich.daoservices;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.valueObjects.ClientVO;
import by.pvt.aliushkevich.pojos.Student;

import java.util.List;
import java.util.Set;

/**
 * Created by Igor Aliushkevich 05/2016
 * Provides services that uses StudentDAO in its turn
 * @see by.pvt.aliushkevich.dao.IStudentDAO
 */
public interface IStudentService extends IBaseService<Student>{

  void addLearningCourse(String login, int courseId) throws DaoException;

  Set<ClientVO> getLecturerValueStudents(String lecturerLogin) throws DaoException;

  List<Student> getStudents();

}
