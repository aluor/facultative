package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.LecturerDAO;
import by.pvt.aliushkevich.dao.RelationDAO;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Relation;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Rabotnik on 10.04.2016.
 */
public class LecturerService extends BaseService<Lecturer> {

  private LecturerDAO lecturerDAO;
  private RelationDAO relationDAO;
  private static LecturerService lecturerService;
  private Transaction transaction;
  private static Logger log = Logger.getLogger(LecturerService.class);

  private LecturerService() {
    super();
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
    log.info("Trying addMarkFeedback to student...");
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      transaction = session.beginTransaction();
      Lecturer lecturer = lecturerDAO.getLecturerByCourseId(courseId);
      Integer relationId = relationDAO.getRelationId(studentId, lecturer.getId());
      Relation relation = relationDAO.get(relationId);
      relation.setMark(mark);
      relation.setFeedback(feedback);
      relationDAO.saveOrUpdate(relation);
      log.info("addMarkFeedback(saveOrUpdate relation):" + relation);
      transaction.commit();
      log.info("addMarkFeedback(commit): SUCCESS");
    } catch (NullPointerException | HibernateException e) {
      log.error("Error addMarkFeedback to student" + e);
      transaction.rollback();
      throw new DaoException(e);
    }
  }

  public List<Lecturer> getLecturers() {
    log.info("Trying getLecturers...");
    return lecturerDAO.getLecturers();
  }

  public int getCourseIdByLogin(String login) throws DaoException {
    log.info("Trying getCourseIdByLogin...");
    return lecturerDAO.getCourseIdByLogin(login);
  }

}
