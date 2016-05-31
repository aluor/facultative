package by.pvt.aliushkevich.daoservices;

import by.pvt.aliushkevich.dao.IBaseDAO;
import by.pvt.aliushkevich.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @param <T> classes of business entities
 * @see by.pvt.aliushkevich.daoservices.IBaseService
 */
@Service("baseService")
@Transactional
public class BaseService<T> implements IBaseService<T> {

  private static Logger log = Logger.getLogger(StudentService.class);
  @Autowired
  private IBaseDAO baseDAO;

  /**
   * Adds business entity in persistent hibernate context using DAO
   * @see by.pvt.aliushkevich.dao.BaseDAO
   */
  public void addClient(T client) throws DaoException {
    log.info("trying addClient:" + client);
    try {
      baseDAO.saveOrUpdate(client);
      log.info("addClient(commit): SUCCESS");
    } catch (HibernateException e) {
      log.error("Error addClient" + e);
      throw new DaoException(e);
    }
  }

  /**
   * Deletes business entity from persistent hibernate context using DAO
   * @see by.pvt.aliushkevich.dao.BaseDAO
   */
  public void deleteClient(T client) throws DaoException {
    log.info("trying deleteClient:" + client);
    try {
      baseDAO.delete(client);
      log.info("deleteClient(commit): SUCCESS");
    } catch (HibernateException e) {
      log.error("Error addClient" + e);
      throw new DaoException(e);
    }
  }
}
