package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Relation;

/**
 * Created by Igor Aliushkevich 05/2016
 * Provides specific data manipulation operations with Relation entity (between Lecturer and Student entities)
 *@see Relation
 */
public interface IRelationDAO extends IBaseDAO<Relation> {

  int getRelationId(int studentId, int lecturerId) throws DaoException;

  int getMark(int studentId, int lecturerId) throws DaoException ;

  String getFeedback(int studentId, int lecturerId) throws DaoException;

}
