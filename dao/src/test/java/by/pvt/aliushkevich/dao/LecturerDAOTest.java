package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
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
 * Created by Rabotnik on 11.04.2016.
 */
public class LecturerDAOTest {

  private static Logger log = Logger.getLogger(LecturerDAOTest.class);
  private Transaction transaction;
  private Lecturer testLecturer;
  private String testFirstName = "testFirstName";
  private String testLastName = "testLastName";
  private String testLogin = "testLogin";
  private String testPassword = "testPassword";
  private int testCourseId = -1;
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
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    transaction = session.beginTransaction();
    session.saveOrUpdate(testLecturer);
    transaction.commit();
    log.info("setUp testLecturer (commit): SUCCESS");
    util.closeSession();
    log.info("session closed");
  }

  @Test
  public void getLecturerByLogin() throws Exception {
    log.info("testing getLecturerByLogin... (testLecturer=" + testLecturer + ")");
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    String hql = "SELECT L.id FROM Lecturer as L WHERE L.login =\'" + testLogin + "\'";
    Query query = session.createQuery(hql);
    Integer lecturerId = (Integer) query.uniqueResult();
    expectedLecturer = (Lecturer) session.get(Lecturer.class, lecturerId);
    assertEquals("getLecturerByLogin failed: testLecturer mismatch expectedLecturer", testLecturer.getLogin(), expectedLecturer.getLogin());
    log.info("getLecturerByLogin: SUCCESS");
    util.closeSession();
  }

  @Test
  public void getCourseIdByLogin() throws Exception {
    log.info("Testing getCourseIdByLogin: " + testLogin + ":...");
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    String hql = "SELECT L.courseId FROM Lecturer as L WHERE L.login =\'" + testLogin + "\'";
    Query query = session.createQuery(hql);
    int expectedCourseId = (Integer) query.uniqueResult();
    assertEquals("getCourseIdByLogin failed: testCourseId mismatch expectedCourseId", testCourseId, expectedCourseId);
    log.info("getCourseIdByLogin: SUCCESS");
    util.closeSession();
  }

  @Test
  public void getLecturerByCourseId() throws Exception {
    log.info("Testing getLecturerByCourseId: " + testCourseId + "...");
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    String hql = "SELECT L.id FROM Lecturer as L WHERE L.courseId = " + testCourseId;
    Query query = session.createQuery(hql);
    Integer lecturerId = (Integer) query.uniqueResult();
    expectedLecturer = (Lecturer) session.get(Lecturer.class, lecturerId);
    assertEquals("getLecturerByCourseId failed: testLecturer mismatch expectedLecturer", testLecturer.getLogin(), expectedLecturer.getLogin());
    log.info("\n-----getLecturerByCourseId: SUCCESS-----\n" + expectedLecturer + "\n----------------------------------------\n");
    util.closeSession();
  }

  @Test
  public void getLecturers() throws Exception {
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    String hql = "FROM Lecturer";
    Query query = session.createQuery(hql);
    expectedLecturers = query.list();
    assertFalse("getLecturers failed:", expectedLecturers.isEmpty());
    log.info("getLecturers (" + expectedLecturers.size() + " person): SUCCESS");
    util.closeSession();
  }

  @After
  public void tearDown() throws Exception {
    log.info("Trying delete testLecturer...");
    util = HibernateUtil.getHibernateUtil();
    Session session = util.getSession();
    transaction = session.beginTransaction();
    session.delete(testLecturer);
    transaction.commit();
    log.info("Delete testLecturer: SUCCESS");
    util.closeSession();
  }
}