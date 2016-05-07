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
import org.hibernate.Session;
import org.hibernate.Transaction;

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

      Set<Relation> relations = student.getRelations();
      boolean hasLearningCourse = false;
      for (Relation relation : relations) {
        if (relation.getStudent() == student && relation.getLecturer() == lecturer) {
          hasLearningCourse = true;
        }
      }
      if (lecturer != null && !hasLearningCourse) {
        Relation relation = new Relation();
        relation.setStudent(student);
        relation.setLecturer(lecturer);
        student.getRelations().add(relation);
        lecturer.getRelations().add(relation);
        studentDAO.saveOrUpdate(student);
        log.info("addLearningCourse(saveOrUpdate student):" + student);
        transaction.commit();
        log.info("addLearningCourse(commit): SUCCESS");
      } else {
        Exception e = null;
        throw new DaoException(e);
      }
    } catch (HibernateException e) {
      log.error("Error addLearningCourse to student" + e);
      transaction.rollback();
      throw new DaoException(e);
    }
  }

  public Set<Student> getLecturerStudents(String lecturerLogin) {
    log.info("trying getLecturerStudents...");
    return studentDAO.getLecturerStudents(lecturerLogin);
  }

  public List<Student> getStudents() {
    log.info("trying getStudents...");
    return studentDAO.getStudents();
  }

}