package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class BaseDAO<T> implements DAO<T> {
  private static Logger log = Logger.getLogger(BaseDAO.class);
  public static HibernateUtil util = null;
  private Transaction transaction = null;

  public BaseDAO() {
  }

  public void saveOrUpdate(T person) throws DaoException {
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      transaction = session.beginTransaction();
      session.saveOrUpdate(person);
      log.info("saveOrUpdate(person):" + person);
      transaction.commit();
      log.info("Save or update (commit):" + person);
    } catch (HibernateException e) {
      log.error("Error save or update PERSON in DAO" + e);
      transaction.rollback();
      throw new DaoException(e);
    }

  }

  public void delete(T person) throws DaoException {
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      transaction = session.beginTransaction();
      session.delete(person);
      transaction.commit();
      log.info("Delete:" + person);
    } catch (HibernateException e) {
      log.error("Error delete PERSON in DAO" + e);
      transaction.rollback();
      throw new DaoException(e);
    }
  }

  public T get(Serializable id) throws DaoException {
    log.info("Get class by id:" + id);
    T person = null;
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      transaction = session.beginTransaction();
      person = (T) session.get(getPersistentClass(), id);
      transaction.commit();
      log.info("get clazz:" + person);
    } catch (HibernateException e) {
      transaction.rollback();
      log.error("Error get " + getPersistentClass() + " in Dao" + e);
      throw new DaoException(e);
    }
    return person;
  }

  protected Class getPersistentClass() {
    return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

}
