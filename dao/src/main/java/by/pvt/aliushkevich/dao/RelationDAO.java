package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Relation;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by Rabotnik on 06.05.2016.
 */
public class RelationDAO extends BaseDAO<Relation> {

  private static Logger log = Logger.getLogger(LecturerDAO.class);

  public int getMark(int studentId, int lecturerId) throws DaoException {
    log.info("Trying getMark...");
    Integer mark = null;
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      String hql = "SELECT R.mark FROM Relation as R WHERE R.student.id = "+studentId+" and R.lecturer.id = "+lecturerId;
      Query query = session.createQuery(hql);
      mark = (Integer) query.uniqueResult();
      log.info("mark:"+mark);
    } catch (HibernateException e) {
      log.error("Error getMark" + e);
      throw new DaoException(e);
    }
    return mark;
  }

  public String getFeedback(int studentId, int lecturerId) throws DaoException {
    log.info("Trying getFeedback...");
    String feedback = null;
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      String hql = "SELECT R.feedback FROM Relation as R WHERE R.student.id = "+studentId+" and R.lecturer.id = "+lecturerId;
      Query query = session.createQuery(hql);
      feedback = (String) query.uniqueResult();
      log.info("feedback:"+feedback);
    } catch (HibernateException e) {
      log.error("Error getFeedback" + e);
      throw new DaoException(e);
    }
    return feedback;
  }

}


