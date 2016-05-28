package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Student;

import java.util.List;
import java.util.Set;

/**
 * Created by Rabotnik on 28.05.2016.
 */
public interface IStudentDAO extends IBaseDAO<Student>{

  Student getStudentByLogin(String login) throws DaoException;

  List<Student> getStudents();

  Set<Student> getLecturerStudents(String lecturerLogin);

}
