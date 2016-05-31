package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import org.hibernate.Session;

import java.io.Serializable;

/**
 * Created by Igor Aliushkevich 05/2016
 * Provides basic data manipulation operations with business entities
 * @param <T> classes of business entities
 */
public interface IBaseDAO<T> {
  Session getSession();
	void saveOrUpdate(T person) throws DaoException;
	void delete(T person) throws DaoException;
	T get(Serializable id) throws DaoException;
  Class getPersistentClass();
}