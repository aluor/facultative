package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.StudentDAO;
import by.pvt.aliushkevich.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rabotnik on 10.04.2016.
 */
public class StudentService {
  private StudentDAO dao;

  public StudentService() {
    dao = new StudentDAO();
  }

  public void addStudent(Student student) throws SQLException {
    dao.addClient(student);
  }

  public void addLearningCourse(String login, int courseId) throws SQLException {
    dao.addLearningCourse(login, courseId);
  }

  public ArrayList<Student> getStudents() {
    return dao.getClients();
  }
}