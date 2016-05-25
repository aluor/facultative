package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.LecturerDAO;
import by.pvt.aliushkevich.dao.RelationDAO;
import by.pvt.aliushkevich.dao.StudentDAO;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.ClientVO;
import by.pvt.aliushkevich.pojos.Relation;
import by.pvt.aliushkevich.pojos.Student;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
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
  private RelationDAO relationDAO;
  private static StudentService studentService;
  private Transaction transaction;
  private static Logger log = Logger.getLogger(StudentService.class);

  private StudentService() {
    super();
    studentDAO = new StudentDAO();
    lecturerDAO = new LecturerDAO();
    relationDAO = new RelationDAO();
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
    log.info("Trying addLearningCourse...");
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

  public Set<ClientVO> getLecturerValueStudents(String lecturerLogin) throws DaoException {
    log.info("Trying getLecturerValueStudents...");
    Set<ClientVO> clientVOs = new HashSet<>();
    Lecturer lecturer = lecturerDAO.getLecturerByLogin(lecturerLogin);
    Set<Student> students= studentDAO.getLecturerStudents(lecturerLogin);
    for (Student student: students){
      ClientVO clientVO = new ClientVO();
      clientVO.setId(student.getId());
      clientVO.setFirstName(student.getFirstName());
      clientVO.setLastName(student.getLastName());
      clientVO.setLogin(student.getLogin());
      clientVO.setMark(relationDAO.getMark(student.getId(), lecturer.getId()));
      clientVO.setFeedback(relationDAO.getFeedback(student.getId(), lecturer.getId()));
      clientVOs.add(clientVO);
    }
    return clientVOs;
  }

  public List<Student> getStudents() {
    log.info("Trying getStudents...");
    return studentDAO.getStudents();
  }

}