package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Repository("baseDAO")
public class BaseDAO<T> implements IBaseDAO<T> {
  private static Logger log = Logger.getLogger(BaseDAO.class);
  @Autowired
  protected SessionFactory sessionFactory;

  public BaseDAO() {
  }

  public Session getSession(){
    return sessionFactory.getCurrentSession();
  }

  public void saveOrUpdate(T client) throws DaoException {
    log.info("trying saveOrUpdate client:" + client);
    try {
      getSession().saveOrUpdate(client);
      log.info("saveOrUpdate(client): SUCCESS");
    } catch (HibernateException e) {
      log.error("Error saveOrUpdate client" + e);
      throw new DaoException(e);
    }

  }

  public void delete(T client) throws DaoException {
    log.info("trying delete client:" + client);
    try {
      getSession().delete(client);
      log.info("Delete client: SUCCESS");
    } catch (HibernateException e) {
      log.error("Error delete client" + e);
      throw new DaoException(e);
    }
  }

  public T get(Serializable id) throws DaoException {
    log.info("Trying get client by id:" +id+"...");
    T client;
    try {
      client = (T) getSession().get(getPersistentClass(), id);
      log.info("get client (clazz):" + client);
    } catch (HibernateException e) {
      log.error("Error get " + getPersistentClass() + e);
      throw new DaoException(e);
    }
    return client;
  }

  public Class getPersistentClass() {
    return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

}