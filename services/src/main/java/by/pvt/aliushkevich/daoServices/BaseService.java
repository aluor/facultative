package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.BaseDAO;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Rabotnik on 01.05.2016.
 */
public class BaseService<T> implements Service<T> {
  public static HibernateUtil util = null;
  private Transaction transaction;
  private BaseDAO baseDAO;
  private static BaseService baseService;
  private static Logger log = Logger.getLogger(StudentService.class);

  protected BaseService() {
    baseDAO = new BaseDAO();
  }

  public static BaseService getInstance() {
    if (baseService == null) {
      baseService = new BaseService();
      return baseService;
    } else {
      return baseService;
    }
  }

  public void addClient(T client) throws DaoException {
    log.info("trying addClient:" + client);
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      transaction = session.beginTransaction();
      baseDAO.saveOrUpdate(client);
      log.info("addClient (saveOrUpdate): SUCCESS");
      transaction.commit();
      log.info("addClient(commit): SUCCESS");
    } catch (HibernateException e) {
      log.error("Error addClient" + e);
      transaction.rollback();
      throw new DaoException(e);
    }
  }

  public void deleteClient(T client) throws DaoException {
    log.info("trying deleteClient:" + client);
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      transaction = session.beginTransaction();
      baseDAO.delete(client);
      log.info("deleteClient: SUCCESS");
      transaction.commit();
      log.info("deleteClient(commit): SUCCESS");
    } catch (HibernateException e) {
      log.error("Error addClient" + e);
      transaction.rollback();
      throw new DaoException(e);
    }
  }
}
