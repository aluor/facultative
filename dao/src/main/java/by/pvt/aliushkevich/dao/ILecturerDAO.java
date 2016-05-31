package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;

import java.util.List;

/**
 * Created by Igor Aliushkevich 05/2016
 * Provides specific data manipulation operations with Lecturer entity
 *@see Lecturer
 */
public interface ILecturerDAO extends IBaseDAO<Lecturer>{
  Lecturer getLecturerByLogin(String login) throws DaoException;

  int getCourseIdByLogin(String login) throws DaoException;

  Lecturer getLecturerByCourseId(int courseId) throws DaoException;

  List<Lecturer> getLecturers();

}
