package by.pvt.aliushkevich.daoservices;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;

import java.util.List;

/**
 * Created by Igor Aliushkevich 05/2016
 * Provides services that uses LecturerDAO in its turn
 * @see by.pvt.aliushkevich.dao.ILecturerDAO
 */
public interface ILecturerService extends IBaseService<Lecturer> {

  void addMarkFeedback(int mark, String feedback, int studentId, int courseId) throws DaoException;

  List<Lecturer> getLecturers();

  int getCourseIdByLogin(String login) throws DaoException;

}
