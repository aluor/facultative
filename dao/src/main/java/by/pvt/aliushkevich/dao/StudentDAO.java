package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.dataSources.DataSource;
import by.pvt.aliushkevich.entity.Student;
import by.pvt.aliushkevich.enums.Courses;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO implements DAO<Student> {

  public static final String SQL_QUERY_ADD_STUDENT =
      "INSERT INTO students (first_name, last_name, login, password) VALUES (?,?,?,?)";
  public static final String SQL_QUERY_GET_STUDENTS =
      "SELECT students.id, students.first_name, students.last_name, " +
          "students.login, students.password, courses.course, results.mark, results.feedback " +
          "FROM (students LEFT JOIN results ON students.id = results.students_id) " +
          "LEFT JOIN courses ON courses.id = results.courses_id";
  public static final String SQL_QUERY_DELETE_STUDENT = "DELETE FROM students WHERE login = ?";
  public static final String SQL_QUERY_ADD_LEARNING_COURSE =
      "INSERT INTO results (students_id, courses_id) VALUES (?,?)";

  public void addClient(Student student) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = DataSource.getInstance().getConnection();
      preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_STUDENT);
      preparedStatement.setString(1, student.getFirstName());
      preparedStatement.setString(2, student.getLastName());
      preparedStatement.setString(3, student.getLogin());
      preparedStatement.setString(4, student.getPassword());
      preparedStatement.executeUpdate();
    } catch (IOException | PropertyVetoException e) {
      System.out.println("SQL, IOE or PropertyVetoException occurred during adding student");
      e.printStackTrace();
    } finally {
      if (preparedStatement != null) try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if (connection != null) try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void deleteStudent(Student student) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = DataSource.getInstance().getConnection();
      preparedStatement = connection.prepareStatement(SQL_QUERY_DELETE_STUDENT);
      preparedStatement.setString(1, student.getLogin());
      preparedStatement.executeUpdate();
    } catch (SQLException | IOException | PropertyVetoException e) {
      System.out.println("SQL, IOE or PropertyVetoException occurred during deleting student");
      e.printStackTrace();
    } finally {
      if (preparedStatement != null) try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if (connection != null) try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void addLearningCourse(String login, int courseId) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
      connection = DataSource.getInstance().getConnection();
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT id FROM students WHERE login = \"" + login + "\"");
      int studentId = 0;
      while (resultSet.next()) {
        studentId = (resultSet.getInt(1));
      }
      preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_LEARNING_COURSE);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, courseId);
      preparedStatement.executeUpdate();
    } catch (IOException | PropertyVetoException e) {
      System.out.println("SQL, IOE or PropertyVetoException occurred during adding learning course");
      e.printStackTrace();
    } finally {
      if (statement != null) try {
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if (resultSet != null) try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if (preparedStatement != null) try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if (connection != null) try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public ArrayList<Student> getClients() {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    ArrayList<Student> students = null;
    try {
      connection = DataSource.getInstance().getConnection();
      statement = connection.createStatement();
      resultSet = statement.executeQuery(SQL_QUERY_GET_STUDENTS);
      students = initStudents(resultSet);
    } catch (SQLException | IOException | PropertyVetoException e) {
      System.out.println("SQL, IOE or PropertyVetoException occurred during getting students");
      e.printStackTrace();
    } finally {
      if (statement != null) try {
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if (resultSet != null) try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if (connection != null) try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return students;
  }

  private ArrayList<Student> initStudents(ResultSet resultSet) throws SQLException {
    ArrayList<Student> students = new ArrayList<Student>();
    while (resultSet.next()) {
      Student student = new Student();
      student.setId(resultSet.getInt(1));
      student.setFirstName(resultSet.getString(2));
      student.setLastName(resultSet.getString(3));
      student.setLogin(resultSet.getString(4));
      student.setPassword(resultSet.getString(5));
      if (resultSet.getString(6) != null) {
        student.setLearningCourse(Courses.valueOf(resultSet.getString(6).toUpperCase()));
      }
      if (resultSet.getString(6) != null && resultSet.getInt(7) != 0) {
        student.setMark(resultSet.getInt(7));
      }
      if (resultSet.getString(6) != null && resultSet.getString(8) != null) {
        student.setFeedback(resultSet.getString(8));
      }
      students.add(student);
    }
    return students;
  }
}
