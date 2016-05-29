package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.exceptions.DaoException;

/**
 * Created by Rabotnik on 28.05.2016.
 */
public interface IBaseService<T> {
  void addClient(T client) throws DaoException;
  void deleteClient(T client) throws DaoException;
}
