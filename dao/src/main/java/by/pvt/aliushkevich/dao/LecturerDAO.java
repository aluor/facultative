package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @see by.pvt.aliushkevich.dao.ILecturerDAO
 */
@Repository("lecturerDAO")
public class LecturerDAO extends BaseDAO<Lecturer> implements ILecturerDAO{

  private static Logger log = Logger.getLogger(LecturerDAO.class);

  /**
   * Gets Lecturer entity from persistent context by its login
   * @param login of Lecturer entity
   * @return Lecturer entity
   * @throws DaoException
   */
  public Lecturer getLecturerByLogin(String login) throws DaoException {
    Lecturer lecturer;
    log.info("Trying getLecturerByLogin "+login+"...");
    try {
      Session session = getSession();
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

  /**
   * Gets  id of teaching by certain lecturer course
   * @param login of Lecturer entity
   * @return int course id
   * @throws DaoException
   */
  public int getCourseIdByLogin(String login) throws DaoException {
    log.info("Trying getCourseIdByLogin "+login+":...");
    Integer courseId;
    try {
      Session session = getSession();
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

  /**
   * Gets  Lecturer entity by his teaching course
   * @param courseId int
   * @return Lecturer
   * @throws DaoException
   */
  public Lecturer getLecturerByCourseId(int courseId) throws DaoException {
    Lecturer lecturer = null;
    log.info("trying getLecturerByCourseId:" + courseId + "...");
    try {
      Session session = getSession();
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

  /**
   * Get List of Lecturer entities
   * @return List
   * @see Lecturer
   */
  public List<Lecturer> getLecturers() {
    List<Lecturer> lecturers = null;
    log.info("Trying getLecturers...");
    try {

      Session session = getSession();
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

