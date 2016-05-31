package by.pvt.aliushkevich.daoservices;

import by.pvt.aliushkevich.exceptions.DaoException;

/**
 * Created by Igor Aliushkevich 05/2016
 * Provides basic services that uses BaseDAO in its turn
 * @param <T> classes of business entities
 * @see by.pvt.aliushkevich.dao.IBaseDAO
 */
public interface IBaseService<T> {
  void addClient(T client) throws DaoException;
  void deleteClient(T client) throws DaoException;
}
