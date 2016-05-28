package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Relation;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by Rabotnik on 06.05.2016.
 */
@Repository("relationDAO")
public class RelationDAO extends BaseDAO<Relation> implements IRelationDAO{

  private static Logger log = Logger.getLogger(RelationDAO.class);

  public int getRelationId(int studentId, int lecturerId) throws DaoException {
    log.info("Trying getRelation...");
    Integer relationId;
    try {
      Session session = getSession();
      String hql = "SELECT R.id FROM Relation as R WHERE R.student.id = "+ studentId+" and R.lecturer.id = "+lecturerId;
      Query query = session.createQuery(hql);
      relationId = (Integer) query.uniqueResult();
      log.info("relationId:"+relationId);
    } catch (HibernateException e) {
      log.error("Error getRelationId" + e);
      throw new DaoException(e);
    }
    return relationId;
  }

  public int getMark(int studentId, int lecturerId) throws DaoException {
    log.info("Trying getMark...");
    Integer mark;
    try {
      Session session = getSession();
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
    String feedback;
    try {
      Session session = getSession();
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


