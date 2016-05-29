package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.valueObjects.ClientVO;
import by.pvt.aliushkevich.pojos.Student;

import java.util.List;
import java.util.Set;

/**
 * Created by Rabotnik on 28.05.2016.
 */
public interface IStudentService extends IBaseService<Student>{

  void addLearningCourse(String login, int courseId) throws DaoException;

  Set<ClientVO> getLecturerValueStudents(String lecturerLogin) throws DaoException;

  List<Student> getStudents();

}
