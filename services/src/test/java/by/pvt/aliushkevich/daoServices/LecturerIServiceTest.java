/*
package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.dao.LecturerDAO;
import by.pvt.aliushkevich.dao.RelationDAO;
import by.pvt.aliushkevich.dao.StudentDAO;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Relation;
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


public class LecturerIServiceTest {

  private static Logger log = Logger.getLogger(LecturerIServiceTest.class);
  private LecturerDAO lecturerDAO = new LecturerDAO();
  private StudentDAO studentDAO = new StudentDAO();
  private RelationDAO relationDAO = new RelationDAO();
  private Transaction transaction;
  private Lecturer testLecturer;
  private Student testStudent;
  private Relation testRelation;
  private String testFirstName = "testFirstName";
  private String testLastName = "testLastName";
  private String testLogin = "testLogin";
  private String testPassword = "testPassword";
  private int testCourseId = -1;
  private int testMark = -1;
  private String testFeedback = "testFeedback";
  private Lecturer expectedLecturer;
  private List<Lecturer> expectedLecturers;

  @Before
  public void setUp() throws Exception {
    log.info("trying setUp testLecturer...");
    testLecturer = new Lecturer();
    testLecturer.setFirstName(testFirstName);
    testLecturer.setLastName(testLastName);
    testLecturer.setLogin(testLogin);
    testLecturer.setPassword(testPassword);
    testLecturer.setCourseId(testCourseId);
    testStudent = new Student();
    testStudent.setFirstName(testFirstName);
    testStudent.setLastName(testLastName);
    testStudent.setLogin(testLogin);
    testStudent.setPassword(testPassword);
    testRelation = new Relation();
    testRelation.setLecturer(testLecturer);
    testRelation.setStudent(testStudent);
    testLecturer.getRelations().add(testRelation);
    testStudent.getRelations().add(testRelation);
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    transaction = session.beginTransaction();
    session.saveOrUpdate(testLecturer);
    session.saveOrUpdate(testStudent);
    transaction.commit();
    log.info("setUp testLecturer (commit): SUCCESS");
    log.info("setUp testStudent (commit): SUCCESS");
    util.closeSession();
    log.info("session closed");
  }

  @Test
  public void addMarkFeedback() throws Exception {
    log.info("testing addMarkFeedback to student...");
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    transaction = session.beginTransaction();
    expectedLecturer = lecturerDAO.getLecturerByCourseId(testCourseId);
    Integer expectedRelationId = relationDAO.getRelationId(testStudent.getId(), testLecturer.getId());
    Relation expectedRelation = relationDAO.get(expectedRelationId);
    expectedRelation.setMark(testMark);
    expectedRelation.setFeedback(testFeedback);
    relationDAO.saveOrUpdate(expectedRelation);
    log.info("addMarkFeedback(saveOrUpdate relation):" + expectedRelation);
    transaction.commit();
    log.info("addMarkFeedback(commit): SUCCESS");
    util.closeSession();

    expectedRelationId = relationDAO.getRelationId(testStudent.getId(), testLecturer.getId());
    expectedRelation = relationDAO.get(expectedRelationId);
    assertEquals("addMarkFeedback failed: testMark mismatch expectedMark", testMark, expectedRelation.getMark());
  }

  @Test
  public void getLecturers() throws Exception {

  }

  @Test
  public void getCourseIdByLogin() throws Exception {

  }

  @After
  public void tearDown() throws Exception {
    log.info("Trying delete testPojos...");
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    transaction = session.beginTransaction();
    testLecturer = lecturerDAO.getLecturerByLogin(testLogin);
    testStudent = studentDAO.getStudentByLogin(testLogin);
    session.delete(testLecturer);
    session.delete(testStudent);
    transaction.commit();
    log.info("Delete testPojos: SUCCESS");
    util.closeSession();
  }
}
*/