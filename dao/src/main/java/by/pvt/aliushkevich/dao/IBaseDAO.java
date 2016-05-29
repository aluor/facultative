package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import org.hibernate.Session;

import java.io.Serializable;

public interface IBaseDAO<T> {
  Session getSession();
	void saveOrUpdate(T person) throws DaoException;
	void delete(T person) throws DaoException;
	T get(Serializable id) throws DaoException;
  Class getPersistentClass();
}