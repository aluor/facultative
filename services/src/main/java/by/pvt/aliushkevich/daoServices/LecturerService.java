package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.LecturerDAO;
import by.pvt.aliushkevich.entity.Lecturer;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rabotnik on 10.04.2016.
 */
public class LecturerService {
  private LecturerDAO dao;
  private static LecturerService lecturerService;

  private LecturerService() {
    dao = new LecturerDAO();
  }

  public static LecturerService getInstance() {
    if (lecturerService == null) {
      lecturerService = new LecturerService();
      return lecturerService;
    } else {
      return lecturerService;
    }
  }

  public void addLecturer(Lecturer lecturer) throws SQLException {
    dao.addClient(lecturer);
  }

  public void addMarkFeedback(int mark, String feedback, int studentId, int courseId) {
    dao.addMarkFeedback(mark, feedback, studentId, courseId);
  }

  public ArrayList<Lecturer> getLecturers() {
    return dao.getClients();
  }

  public boolean hasMarkFeedback(int mark, String feedback, int studentId, int courseId) {
    return dao.hasMarkFeedback(mark, feedback, studentId, courseId);
  }

}
