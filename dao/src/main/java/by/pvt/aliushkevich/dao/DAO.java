package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import java.io.Serializable;

public interface DAO<T> {
	void saveOrUpdate(T person) throws DaoException;
	void delete(T person) throws DaoException;
	T get(Serializable id) throws DaoException;
//T load(Serializable id) throws DaoException;
}

//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public interface DAO<T> {
//	void addClient(T client) throws SQLException;
//	ArrayList<T> getClients();
//}