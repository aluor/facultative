package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.dataSources.DataSource;
import by.pvt.aliushkevich.entity.Lecturer;
import by.pvt.aliushkevich.enums.Courses;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class LecturerDAO implements DAO<Lecturer> {

  public static final String SQL_QUERY_ADD_LECTURER =
      "INSERT INTO lectures (first_name, last_name, login, password) VALUES (?,?,?,?)";
  public static final String SQL_QUERY_GET_LECTURERS =
      "SELECT lectures.id, lectures.first_name, lectures.last_name, " +
          "lectures.login, lectures.password, courses.course " +
          "FROM lectures LEFT JOIN courses ON lectures.id = courses.lectures_id";
  public static final String SQL_QUERY_DELETE_LECTURER = "DELETE FROM lectures WHERE login = ?";
  public static final String SQL_QUERY_ADD_MARK_FEEDBACK =
      "UPDATE results SET mark = ?, feedback = ?  WHERE students_id = ? and courses_id = ?";
  public static final String SQL_QUERY_GET_MARK_FEEDBACK =
      "SELECT students_id FROM results WHERE students_id = ? and courses_id = ? and mark = ? and feedback = ?";

  public void addClient(Lecturer lecturer) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = DataSource.getInstance().getConnection();
      preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_LECTURER);
      preparedStatement.setString(1, lecturer.getFirstName());
      preparedStatement.setString(2, lecturer.getLastName());
      preparedStatement.setString(3, lecturer.getLogin());
      preparedStatement.setString(4, lecturer.getPassword());
      preparedStatement.executeUpdate();
    } catch (SQLException | IOException | PropertyVetoException e) {
      System.out.println("SQL, IOE or PropertyVetoException occurred during adding lecturer");
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

  public void deleteLecturer(Lecturer lecturer) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = DataSource.getInstance().getConnection();
      preparedStatement = connection.prepareStatement(SQL_QUERY_DELETE_LECTURER);
      preparedStatement.setString(1, lecturer.getLogin());
      preparedStatement.executeUpdate();
    } catch (SQLException | IOException | PropertyVetoException e) {
      System.out.println("SQL, IOE or PropertyVetoException occurred during deleting lecturer");
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

  public void addMarkFeedback(int mark, String feedback, int studentId, int courseId) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = DataSource.getInstance().getConnection();
      preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_MARK_FEEDBACK);
      preparedStatement.setInt(1, mark);
      preparedStatement.setString(2, feedback);
      preparedStatement.setInt(3, studentId);
      preparedStatement.setInt(4, courseId);
      preparedStatement.executeUpdate();
    } catch (SQLException | IOException | PropertyVetoException e) {
      System.out.println("SQL, IOE or PropertyVetoException occurred during adding mark/feedback");
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

  public ArrayList<Lecturer> getClients() {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    ArrayList<Lecturer> lecturers = null;
    try {
      connection = DataSource.getInstance().getConnection();
      statement = connection.createStatement();
      resultSet = statement.executeQuery(SQL_QUERY_GET_LECTURERS);
      lecturers = initLecturers(resultSet);
    } catch (SQLException | IOException | PropertyVetoException e) {
      System.out.println("SQL, IOE or PropertyVetoException occurred during getting lecturers");
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
    return lecturers;
  }

  private ArrayList<Lecturer> initLecturers(ResultSet resultSet) throws SQLException {
    ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
    while (resultSet.next()) {
      Lecturer lecturer = new Lecturer();
      lecturer.setId(resultSet.getInt(1));
      lecturer.setFirstName(resultSet.getString(2));
      lecturer.setLastName(resultSet.getString(3));
      lecturer.setLogin(resultSet.getString(4));
      lecturer.setPassword(resultSet.getString(5));
      if (resultSet.getString(6) != null) {
        lecturer.setTeachesCourse(Courses.valueOf(resultSet.getString(6).toUpperCase()));
      }
      lecturers.add(lecturer);
    }
    return lecturers;
  }

  public boolean hasMarkFeedback(int mark, String feedback, int studentId, int courseId) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    boolean isRecordSuccess = false;
    try {
      connection = DataSource.getInstance().getConnection();
      preparedStatement = connection.prepareStatement(SQL_QUERY_GET_MARK_FEEDBACK);
      preparedStatement.setInt(1, studentId);
      preparedStatement.setInt(2, courseId);
      preparedStatement.setInt(3, mark);
      preparedStatement.setString(4, feedback);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        isRecordSuccess = true;
      }
    } catch (SQLException | IOException | PropertyVetoException e) {
      System.out.println("SQL, IOE or PropertyVetoException occurred during getting mark/feedback");
      e.printStackTrace();
    } finally {
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
    return isRecordSuccess;
  }
}