package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Relation;
import by.pvt.aliushkevich.pojos.Student;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Rabotnik on 20.04.2016.
 */
public class StudentDAO extends BaseDAO<Student> {
  private static Logger log = Logger.getLogger(StudentDAO.class);
  private Transaction transaction = null;

  public void addLearningCourse(String login, int courseId) throws DaoException {
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      transaction = session.beginTransaction();

      String studentHql = "SELECT S.id FROM Student as S WHERE S.login =\'"+login+"\'";
      Query studentQuery = session.createQuery(studentHql);
      Integer studentId = (Integer) studentQuery.uniqueResult();
      Student student = (Student) session.get(Student.class, studentId);

      System.out.print("\n----------\n"+ student+"\n----------\n");

      String LecturerHql = "SELECT L.id FROM Lecturer as L WHERE L.courseId = " + courseId;
      Query lecturerQuery = session.createQuery(LecturerHql);
      Integer lecturerId = (Integer) lecturerQuery.uniqueResult();
      Lecturer lecturer = (Lecturer) session.get(Lecturer.class, lecturerId);

      System.out.print("\n----------\n"+ lecturer+"\n----------\n");

      Relation relation = new Relation();
      relation.setStudent(student);
      relation.setLecturer(lecturer);
      student.getRelations().add(relation);
      lecturer.getRelations().add(relation);

      session.saveOrUpdate(student);
      log.info("saveOrUpdate(student):" + student);
      transaction.commit();
      log.info("Save or update (commit):" + student);
    } catch (HibernateException e) {
      log.error("Error save or update student in DAO" + e);
      transaction.rollback();
      throw new DaoException(e);
    }
  }


}


//import by.pvt.aliushkevich.dataSources.DataSource;
//import by.pvt.aliushkevich.pojos.Student;
//import by.pvt.aliushkevich.enums.Courses;
//
//import java.beans.PropertyVetoException;
//import java.io.IOException;
//import java.sql.*;
//import java.util.ArrayList;
//
//public class StudentDAO implements DAO<Student> {
//
//  public static final String SQL_QUERY_ADD_STUDENT =
//      "INSERT INTO students (first_name, last_name, login, password) VALUES (?,?,?,?)";
//  public static final String SQL_QUERY_GET_STUDENTS =
//      "SELECT students.id, students.first_name, students.last_name, " +
//          "students.login, students.password, courses.course, results.mark, results.feedback " +
//          "FROM (students LEFT JOIN results ON students.id = results.students_id) " +
//          "LEFT JOIN courses ON courses.id = results.courses_id";
//  public static final String SQL_QUERY_DELETE_STUDENT = "DELETE FROM students WHERE login = ?";
//  public static final String SQL_QUERY_ADD_LEARNING_COURSE =
//      "INSERT INTO results (students_id, courses_id) VALUES (?,?)";
//
//  public void addClient(Student student) throws SQLException {
//    Connection connection = null;
//    PreparedStatement preparedStatement = null;
//    try {
//      connection = DataSource.getInstance().getConnection();
//      preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_STUDENT);
//      preparedStatement.setString(1, student.getFirstName());
//      preparedStatement.setString(2, student.getLastName());
//      preparedStatement.setString(3, student.getLogin());
//      preparedStatement.setString(4, student.getPassword());
//      preparedStatement.executeUpdate();
//    } catch (IOException | PropertyVetoException e) {
//      System.out.println("SQL, IOE or PropertyVetoException occurred during adding student");
//      e.printStackTrace();
//    } finally {
//      if (preparedStatement != null) try {
//        preparedStatement.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//      if (connection != null) try {
//        connection.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//  public void deleteStudent(Student student) {
//    Connection connection = null;
//    PreparedStatement preparedStatement = null;
//    try {
//      connection = DataSource.getInstance().getConnection();
//      preparedStatement = connection.prepareStatement(SQL_QUERY_DELETE_STUDENT);
//      preparedStatement.setString(1, student.getLogin());
//      preparedStatement.executeUpdate();
//    } catch (SQLException | IOException | PropertyVetoException e) {
//      System.out.println("SQL, IOE or PropertyVetoException occurred during deleting student");
//      e.printStackTrace();
//    } finally {
//      if (preparedStatement != null) try {
//        preparedStatement.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//      if (connection != null) try {
//        connection.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//  public void addLearningCourse(String login, int courseId) throws SQLException {
//    Connection connection = null;
//    PreparedStatement preparedStatement = null;
//    Statement statement = null;
//    ResultSet resultSet = null;
//    try {
//      connection = DataSource.getInstance().getConnection();
//      statement = connection.createStatement();
//      resultSet = statement.executeQuery("SELECT id FROM students WHERE login = \"" + login + "\"");
//      int studentId = 0;
//      while (resultSet.next()) {
//        studentId = (resultSet.getInt(1));
//      }
//      preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_LEARNING_COURSE);
//      preparedStatement.setInt(1, studentId);
//      preparedStatement.setInt(2, courseId);
//      preparedStatement.executeUpdate();
//    } catch (IOException | PropertyVetoException e) {
//      System.out.println("SQL, IOE or PropertyVetoException occurred during adding learning course");
//      e.printStackTrace();
//    } finally {
//      if (statement != null) try {
//        statement.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//      if (resultSet != null) try {
//        resultSet.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//      if (preparedStatement != null) try {
//        preparedStatement.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//      if (connection != null) try {
//        connection.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//  public ArrayList<Student> getClients() {
//    Connection connection = null;
//    Statement statement = null;
//    ResultSet resultSet = null;
//    ArrayList<Student> students = null;
//    try {
//      connection = DataSource.getInstance().getConnection();
//      statement = connection.createStatement();
//      resultSet = statement.executeQuery(SQL_QUERY_GET_STUDENTS);
//      students = initStudents(resultSet);
//    } catch (SQLException | IOException | PropertyVetoException e) {
//      System.out.println("SQL, IOE or PropertyVetoException occurred during getting students");
//      e.printStackTrace();
//    } finally {
//      if (statement != null) try {
//        statement.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//      if (resultSet != null) try {
//        resultSet.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//      if (connection != null) try {
//        connection.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//    }
//    return students;
//  }
//
//  private ArrayList<Student> initStudents(ResultSet resultSet) throws SQLException {
//    ArrayList<Student> students = new ArrayList<Student>();
//    while (resultSet.next()) {
//      Student student = new Student();
//      student.setId(resultSet.getInt(1));
//      student.setFirstName(resultSet.getString(2));
//      student.setLastName(resultSet.getString(3));
//      student.setLogin(resultSet.getString(4));
//      student.setPassword(resultSet.getString(5));
//      if (resultSet.getString(6) != null) {
//        student.setLearningCourse(Courses.valueOf(resultSet.getString(6).toUpperCase()));
//      }
//      if (resultSet.getString(6) != null && resultSet.getInt(7) != 0) {
//        student.setMark(resultSet.getInt(7));
//      }
//      if (resultSet.getString(6) != null && resultSet.getString(8) != null) {
//        student.setFeedback(resultSet.getString(8));
//      }
//      students.add(student);
//    }
//    return students;
//  }
//}
