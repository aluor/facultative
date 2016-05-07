package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.LecturerDAO;
import by.pvt.aliushkevich.dao.RelationDAO;
import by.pvt.aliushkevich.dao.StudentDAO;
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

import java.util.List;

/**
 * Created by Rabotnik on 10.04.2016.
 */
public class LecturerService extends BaseService<Lecturer> {

  private StudentDAO studentDAO;
  private LecturerDAO lecturerDAO;
  private RelationDAO relationDAO;
  private static LecturerService lecturerService;
  private Transaction transaction;
  private static Logger log = Logger.getLogger(LecturerService.class);

  private LecturerService() {
    super();
    studentDAO = new StudentDAO();
    lecturerDAO = new LecturerDAO();
    relationDAO = new RelationDAO();
  }

  public static LecturerService getInstance() {
    if (lecturerService == null) {
      lecturerService = new LecturerService();
      return lecturerService;
    } else {
      return lecturerService;
    }
  }

  public void addMarkFeedback(int mark, String feedback, int studentId, int courseId) throws DaoException {
    log.info("trying addMarkFeedback to student...");
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      transaction = session.beginTransaction();
      Student student = studentDAO.get(studentId);
      Lecturer lecturer = lecturerDAO.getLecturerByCourseId(courseId);
      String hql = "SELECT R.id FROM Relation as R WHERE R.student = \'"+ student.getId()+"\' and R.lecturer = \'"+lecturer.getId()+"\'";
      Query query = session.createQuery(hql);
      Integer relationId = (Integer) query.uniqueResult();
      Relation relation = relationDAO.get(relationId);
      relation.setMark(mark);
      relation.setFeedback(feedback);
      relationDAO.saveOrUpdate(relation);
      log.info("addMarkFeedback(saveOrUpdate relation):" + relation);
      transaction.commit();
      log.info("addMarkFeedback(commit): SUCCESS");
    } catch (HibernateException e) {
      log.error("Error addMarkFeedback to student" + e);
      transaction.rollback();
      throw new DaoException(e);
    }
  }

  public List<Lecturer> getLecturers() {
    return lecturerDAO.getLecturers();
  }

//TODO
//  public boolean hasMarkFeedback(int mark, String feedback, int studentId, int courseId) {
//    return lecturerDAO.hasMarkFeedback(mark, feedback, studentId, courseId);
//  }

}
