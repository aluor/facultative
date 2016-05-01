package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.StudentDAO;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Student;

/**
 * Created by Rabotnik on 10.04.2016.
 */
public class StudentService {
  private StudentDAO studentDao;
  private static StudentService studentService;

  private StudentService() {
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

  public void addStudent(Student student) throws DaoException {
    studentDao.saveOrUpdate(student);
  }

  public void deleteStudent(Student student) throws DaoException {
    studentDao.delete(student);
  }

//TODO
//  public void addLearningCourse(String login, int courseId) throws SQLException {
//    studentDao.addLearningCourse(login, courseId);
//  }
//
//  public ArrayList<Student> getStudents() {
//    return studentDao.getClients();
//  }

}