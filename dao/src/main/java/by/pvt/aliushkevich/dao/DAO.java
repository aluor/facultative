package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import java.io.Serializable;

public interface DAO<T> {
	void saveOrUpdate(T person) throws DaoException;
	void delete(T person) throws DaoException;
	T get(Serializable id) throws DaoException;
}