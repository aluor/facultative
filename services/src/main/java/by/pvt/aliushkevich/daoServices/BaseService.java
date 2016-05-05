package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.BaseDAO;
import by.pvt.aliushkevich.exceptions.DaoException;

/**
 * Created by Rabotnik on 01.05.2016.
 */
public class BaseService<T> implements Service<T> {

  private BaseDAO baseDAO;
  private static BaseService baseService;

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
    baseDAO.saveOrUpdate(client);
  }

  public void deleteClient(T client) throws DaoException {
    baseDAO.delete(client);
  }
}
