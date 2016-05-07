package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.LecturerDAO;
import by.pvt.aliushkevich.dao.StudentDAO;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Relation;
import by.pvt.aliushkevich.pojos.Student;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rabotnik on 10.04.2016.
 */
public class StudentService extends BaseService<Student> {
  private StudentDAO studentDAO;
  private LecturerDAO lecturerDAO;
  private static StudentService studentService;
  private Transaction transaction;
  private static Logger log = Logger.getLogger(StudentService.class);

  private StudentService() {
    super();
    studentDAO = new StudentDAO();
    lecturerDAO = new LecturerDAO();
  }

  public static StudentService getInstance() {
    if (studentService == null) {
      studentService = new StudentService();
      return studentService;
    } else {
      return studentService;
    }
  }

  public void addLearningCourse(String login, int courseId) throws DaoException {
    log.info("trying addLearningCourse...");
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      transaction = session.beginTransaction();
      Student student = studentDAO.getStudentByLogin(login);
      Lecturer lecturer = lecturerDAO.getLecturerByCourseId(courseId);
      Relation relation = new Relation();
      relation.setStudent(student);
      relation.setLecturer(lecturer);
      student.getRelations().add(relation);
      lecturer.getRelations().add(relation);
      studentDAO.saveOrUpdate(student);
      log.info("addLearningCourse(saveOrUpdate student):" + student);
      transaction.commit();
      log.info("addLearningCourse(commit): SUCCESS");
    } catch (HibernateException e) {
      log.error("Error addLearningCourse to student" + e);
      transaction.rollback();
      throw new DaoException(e);
    }
  }

  public Set<Student> getLecturerStudents (String lecturerLogin) {
    log.info("trying getLecturerStudents...");
    Set<Student> lecturerStudents = new HashSet<>();
    try {
      util = HibernateUtil.getHibernateUtil();
      Session session = util.getSession();
      String hql = "SELECT L.id FROM Lecturer as L WHERE L.login =\'" + lecturerLogin + "\'";
      Query query = session.createQuery(hql);
      Integer lecturerId = (Integer) query.uniqueResult();
      Lecturer lecturer = (Lecturer) session.get(Lecturer.class, lecturerId);
      log.info("\n----------\n" + lecturer + "\n----------\n");
      Set<Relation> relations = lecturer.getRelations();
      for (Relation relation: relations) {
        log.info("relation.getStudent(): "+relation.getStudent());
        lecturerStudents.add(relation.getStudent());
      }
      log.info("\n----------\n" + lecturerStudents + "\n----------\n");
    } catch (HibernateException e) {
      log.error("Error getLecturerStudents" + e);
    }
    return lecturerStudents;
  }



  public List<Student> getStudents() {
    return studentDAO.getStudents();
  }

}