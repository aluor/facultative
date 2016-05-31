package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Student;

import java.util.List;
import java.util.Set;

/**
 * Created by Igor Aliushkevich 05/2016
 * Provides specific data manipulation operations with Student entity
 * @see Student
 */
public interface IStudentDAO extends IBaseDAO<Student>{

  Student getStudentByLogin(String login) throws DaoException;

  List<Student> getStudents();

  Set<Student> getLecturerStudents(String lecturerLogin);

}
