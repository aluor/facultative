package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Relation;
import by.pvt.aliushkevich.pojos.Student;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @see by.pvt.aliushkevich.dao.IStudentDAO
 */
@Repository("studentDAO")
public class StudentDAO extends BaseDAO<Student> implements IStudentDAO{
  private static Logger log = Logger.getLogger(StudentDAO.class);

  /**
   * Gets Student entity from persistent context by its login
   * @param login of Student entity
   * @return Lecturer entity
   * @throws DaoException
   */
  public Student getStudentByLogin(String login) throws DaoException {
    Student student;
    log.info("Trying getStudentByLogin " + login + "...");
    try {
      Session session = getSession();
      String hql = "SELECT S.id FROM Student as S WHERE S.login =\'" + login + "\'";
      Query query = session.createQuery(hql);
      Integer studentId = (Integer) query.uniqueResult();
      student = (Student) session.get(Student.class, studentId);
      log.info("\n----------\n" + student + "\n----------\n");
    } catch (HibernateException e) {
      log.error("Error getStudentByLogin" + e);
      throw new DaoException(e);
    }
    return student;
  }

  /**
   * Gets List of Student entities
   * @return List
   * @see Student
   */
  public List<Student> getStudents() {
    List<Student> students = null;
    log.info("Trying getStudents...");
    try {
      Session session = getSession();
      String hql = "FROM Student";
      Query query = session.createQuery(hql);
      students = query.list();
      log.info("\n----------\n" + students + "\n----------\n");
    } catch (HibernateException e) {
      log.error("Error getStudents" + e);
    }
    return students;
  }

  /**
   * Get Student entities, that are signed to this Lecturer's course
   * @param lecturerLogin
   * @return Set of Student entities
   * @see Lecturer
   * @see Student
   */
  public Set<Student> getLecturerStudents(String lecturerLogin) {
    log.info("trying getLecturerStudents...");
    Set<Student> lecturerStudents = new HashSet<>();
    try {
      Session session = getSession();
      String hql = "SELECT L.id FROM Lecturer as L WHERE L.login =\'" + lecturerLogin + "\'";
      Query query = session.createQuery(hql);
      Integer lecturerId = (Integer) query.uniqueResult();
      Lecturer lecturer = (Lecturer) session.get(Lecturer.class, lecturerId);
      log.info("\n----------\n" + lecturer + "\n----------\n");
      Set<Relation> relations = lecturer.getRelations();
      for (Relation relation : relations) {
        log.info("relation.getStudent(): " + relation.getStudent());
        lecturerStudents.add(relation.getStudent());
      }
      log.info("\n----------\n" + lecturerStudents + "\n----------\n");
    } catch (HibernateException e) {
      log.error("Error getLecturerStudents" + e);
    }
    return lecturerStudents;
  }

}
