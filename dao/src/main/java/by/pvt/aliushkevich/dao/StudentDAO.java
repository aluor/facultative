package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Student;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

/**
 * Created by Rabotnik on 20.04.2016.
 */
public class StudentDAO extends BaseDAO<Student> {
  private static Logger log = Logger.getLogger(StudentDAO.class);
//  private Transaction transaction = null;

  public Student getStudentByLogin(String login) throws DaoException {
    Student student = null;
    log.info("Trying getStudentByLogin "+login+"...");
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
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

  public List<Student> getStudents() {
    List<Student> students = null;
    log.info("Trying getStudents...");
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      String hql = "FROM Student";
      Query query = session.createQuery(hql);
      students = query.list();
      log.info("\n----------\n" + students + "\n----------\n");
    } catch (HibernateException e) {
      log.error("Error getStudents" + e);
    }
    return students;
  }

}
