package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class BaseDAO<T> implements DAO<T> {
  private static Logger log = Logger.getLogger(BaseDAO.class);
  public static HibernateUtil util = null;

  public BaseDAO() {
  }

  public void saveOrUpdate(T client) throws DaoException {
    log.info("trying saveOrUpdate client:" + client);
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      session.saveOrUpdate(client);
      log.info("saveOrUpdate(client): SUCCESS");
    } catch (HibernateException e) {
      log.error("Error saveOrUpdate client" + e);
      throw new DaoException(e);
    }

  }

  public void delete(T client) throws DaoException {
    log.info("trying delete client:" + client);
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      session.delete(client);
      log.info("Delete client: SUCCESS");
    } catch (HibernateException e) {
      log.error("Error delete client" + e);
      throw new DaoException(e);
    }
  }

  public T get(Serializable id) throws DaoException {
    log.info("Trying get client by id:" +id+"...");
    T client = null;
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      client = (T) session.get(getPersistentClass(), id);
      log.info("get client (clazz):" + client);
    } catch (HibernateException e) {
      log.error("Error get " + getPersistentClass() + e);
      throw new DaoException(e);
    }
    return client;
  }

  protected Class getPersistentClass() {
    return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

}