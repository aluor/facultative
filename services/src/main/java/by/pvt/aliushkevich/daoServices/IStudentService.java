package by.pvt.aliushkevich.daoservices;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.ClientVO;
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
