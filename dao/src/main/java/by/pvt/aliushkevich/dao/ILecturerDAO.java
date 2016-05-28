package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;

import java.util.List;

/**
 * Created by Rabotnik on 28.05.2016.
 */
public interface ILecturerDAO extends IBaseDAO<Lecturer>{
  Lecturer getLecturerByLogin(String login) throws DaoException;

  int getCourseIdByLogin(String login) throws DaoException;

  Lecturer getLecturerByCourseId(int courseId) throws DaoException;

  List<Lecturer> getLecturers();

}
