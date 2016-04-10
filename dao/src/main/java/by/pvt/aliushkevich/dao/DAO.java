package by.pvt.aliushkevich.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {
	void addClient(T client) throws SQLException;
	ArrayList<T> getClients();
}