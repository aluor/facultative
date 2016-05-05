package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.LecturerDAO;
import by.pvt.aliushkevich.pojos.Lecturer;

/**
 * Created by Rabotnik on 10.04.2016.
 */
public class LecturerService extends BaseService<Lecturer>{
  private LecturerDAO lecturerDAO;
  private static LecturerService lecturerService;

  private LecturerService() {
    super();
    lecturerDAO = new LecturerDAO();
  }

  public static LecturerService getInstance() {
    if (lecturerService == null) {
      lecturerService = new LecturerService();
      return lecturerService;
    } else {
      return lecturerService;
    }
  }

//TODO
//  public void addMarkFeedback(int mark, String feedback, int studentId, int courseId) {
//    lecturerDAO.addMarkFeedback(mark, feedback, studentId, courseId);
//  }
//
//  public ArrayList<Lecturer> getLecturers() {
//    return lecturerDAO.getClients();
//  }
//
//  public boolean hasMarkFeedback(int mark, String feedback, int studentId, int courseId) {
//    return lecturerDAO.hasMarkFeedback(mark, feedback, studentId, courseId);
//  }

}
