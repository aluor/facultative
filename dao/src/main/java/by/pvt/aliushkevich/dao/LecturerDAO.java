package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

public class LecturerDAO extends BaseDAO<Lecturer> {

  private static Logger log = Logger.getLogger(LecturerDAO.class);

  public Lecturer getLecturerByLogin(String login) throws DaoException {
    Lecturer lecturer;
    log.info("Trying getLecturerByLogin "+login+"...");
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      String hql = "SELECT L.id FROM Lecturer as L WHERE L.login =\'" + login + "\'";
      Query query = session.createQuery(hql);
      Integer lecturerId = (Integer) query.uniqueResult();
      lecturer = (Lecturer) session.get(Lecturer.class, lecturerId);
      log.info("\n----------\n" + lecturer + "\n----------\n");
    } catch (HibernateException e) {
      log.error("Error getLecturerByLogin" + e);
      throw new DaoException(e);
    }
    return lecturer;
  }

  public int getCourseIdByLogin(String login) throws DaoException {
    log.info("Trying getCourseIdByLogin "+login+":...");
    Integer courseId;
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      String hql = "SELECT L.courseId FROM Lecturer as L WHERE L.login =\'" + login + "\'";
      Query query = session.createQuery(hql);
      courseId = (Integer) query.uniqueResult();
      log.info("courseId: "+courseId);
    } catch (HibernateException e) {
      log.error("Error getCourseIdByLogin" + e);
      throw new DaoException(e);
    }
    return courseId;
  }

  public Lecturer getLecturerByCourseId(int courseId) throws DaoException {
    Lecturer lecturer = null;
    log.info("trying getLecturerByCourseId:" + courseId + "...");
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      String hql = "SELECT L.id FROM Lecturer as L WHERE L.courseId = " + courseId;
      Query query = session.createQuery(hql);
      Integer lecturerId = (Integer) query.uniqueResult();
      if (lecturerId != null) {
        lecturer = (Lecturer) session.get(Lecturer.class, lecturerId);
        log.info("\n----------\n" + lecturer + "\n----------\n");
      }
    } catch (HibernateException e) {
      log.error("Error getLecturerByCourseId" + e);
      throw new DaoException(e);
    }
    return lecturer;
  }

  public List<Lecturer> getLecturers() {
    List<Lecturer> lecturers = null;
    log.info("Trying getLecturers...");
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      String hql = "FROM Lecturer";
      Query query = session.createQuery(hql);
      lecturers = query.list();
      log.info("\n----------\n" + lecturers + "\n----------\n");
    } catch (HibernateException e) {
      log.error("Error getLecturers" + e);
    }
    return lecturers;
  }
}

