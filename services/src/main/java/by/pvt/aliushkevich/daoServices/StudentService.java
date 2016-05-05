package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.StudentDAO;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Student;

import java.util.List;

/**
 * Created by Rabotnik on 10.04.2016.
 */
public class StudentService extends BaseService<Student>{
  private StudentDAO studentDao;
  private static StudentService studentService;

  private StudentService() {
    super();
    studentDao = new StudentDAO();
  }

  public static StudentService getInstance() {
    if (studentService == null) {
      studentService = new StudentService();
      return studentService;
    } else {
      return studentService;
    }
  }

  public void addLearningCourse(String login, int courseId) throws DaoException {
    studentDao.addLearningCourse(login, courseId);
  }


  public List<Student> getStudents() {
    return studentDao.getStudents();
  }

}