package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.pojos.Student;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static by.pvt.aliushkevich.dao.BaseDAO.util;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Rabotnik on 13.04.2016.
 */
public class StudentDAOTest {

  private static Logger log = Logger.getLogger(LecturerDAOTest.class);
  private Transaction transaction;
  private LecturerDAO lecturerDAO = new LecturerDAO();
  private StudentDAO studentDAO = new StudentDAO();
  private Student testStudent = new Student();
  private String testFirstName = "testFirstName";
  private String testLastName = "testLastName";
  private String testLogin = "testLogin";
  private String testPassword = "testPassword";

  @Before
  public void setUp() throws Exception {
    log.info("trying setUp testLecturer...");
    testStudent.setFirstName(testFirstName);
    testStudent.setLastName(testLastName);
    testStudent.setLogin(testLogin);
    testStudent.setPassword(testPassword);
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    transaction = session.beginTransaction();
    session.saveOrUpdate(testStudent);
    transaction.commit();
    log.info("setUp testStudent (commit): SUCCESS");
    util.closeSession();
    log.info("session closed");
  }

  @Test
  public void getStudentByLogin() throws Exception {
    log.info("testing getStudentByLogin... (testStudent=" + testStudent + ")");
    Student expectedStudent = studentDAO.getStudentByLogin(testLogin);
    log.info("(get expectedStudent:=" + expectedStudent + ")");
    assertEquals("getLecturerByLogin failed: testLecturer mismatch expectedLecturer", testStudent.getLogin(), expectedStudent.getLogin());
    log.info("getStudentByLogin: SUCCESS");
    util.closeSession();
  }

  @Test
  public void getStudents() throws Exception {
    List<Student> expectedStudents = studentDAO.getStudents();
    assertFalse("getStudents failed:", expectedStudents.isEmpty());
    log.info("getLecturers (" + expectedStudents.size() + " person): SUCCESS");
    util.closeSession();
  }

  @Test
  public void getLecturerStudents() throws Exception {
//    util = HibernateUtil.getHibernateUtil();
//    Session session = util.getSession();
//    transaction = session.beginTransaction();
//
//    Lecturer testLecturer = new Lecturer();
//    testLecturer.setLogin(testLogin);
//    Set<Relation> testRelations = new HashSet<>();
//    Relation testRelation = new Relation();
//
//    testRelation.setStudent(testStudent);
//    testRelations.add(testRelation);
//    testLecturer.setRelations(testRelations);
//
//    studentDAO.saveOrUpdate(testStudent);
//    lecturerDAO.saveOrUpdate(testLecturer);
//    transaction.commit();
//    session.clear();
//    Set<Student> expectedLecturerStudents = studentDAO.getLecturerStudents(testLogin);
//    assertFalse("getLecturerStudents failed:", expectedLecturerStudents.isEmpty());
//    log.info("getLecturerStudents (" + expectedLecturerStudents.size() + " person): SUCCESS");
//    util.closeSession();
  }

  @After
  public void tearDown() throws Exception {
    log.info("Trying delete testStudent...");
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    transaction = session.beginTransaction();
    session.delete(testStudent);
    transaction.commit();
    log.info("Delete testStudent: SUCCESS");
    util.closeSession();
  }

}