
package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.pojos.Lecturer;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class LecturerDAOTest {

  private static Logger log = Logger.getLogger(LecturerDAOTest.class);
  @Autowired
  private LecturerDAO lecturerDAO;
  private Lecturer testLecturer = new Lecturer();
  private String testFirstName = "testFirstName";
  private String testLastName = "testLastName";
  private String testLogin = "testLogin";
  private String testPassword = "testPassword";
  private int testCourseId = -1;

  @Before
  @Transactional
  public void setUp() throws Exception {
    log.info("trying setUp testLecturer...");
    testLecturer.setFirstName(testFirstName);
    testLecturer.setLastName(testLastName);
    testLecturer.setLogin(testLogin);
    testLecturer.setPassword(testPassword);
    testLecturer.setCourseId(testCourseId);
    lecturerDAO.saveOrUpdate(testLecturer);
    log.info("setUp testLecturer (commit): SUCCESS");
  }

  @Test
  @Transactional
  public void getLecturerByLogin() throws Exception {
    log.info("testing getLecturerByLogin... (testLecturer=" + testLecturer + ")");
    Lecturer expectedLecturer = lecturerDAO.getLecturerByLogin(testLogin);
    log.info("(get expectedLecturer:=" + expectedLecturer + ")");
    assertEquals("getLecturerByLogin failed: testLecturer mismatch expectedLecturer", testLecturer.getLogin(), expectedLecturer.getLogin());
    log.info("getLecturerByLogin: SUCCESS");
  }



  @Test
  @Transactional
  public void getCourseIdByLogin() throws Exception {
    log.info("Testing getCourseIdByLogin: " + testLogin + ":...");
    int expectedCourseId = lecturerDAO.getCourseIdByLogin(testLogin);
    assertEquals("getCourseIdByLogin failed: testCourseId mismatch expectedCourseId", testCourseId, expectedCourseId);
    log.info("getCourseIdByLogin: SUCCESS");
  }

  @Test
  @Transactional
  public void getLecturerByCourseId() throws Exception {
    log.info("Testing getLecturerByCourseId: " + testCourseId + "...");
    Lecturer expectedLecturer = lecturerDAO.getLecturerByCourseId(testCourseId);
    assertEquals("getLecturerByCourseId failed: testLecturer mismatch expectedLecturer", testLecturer.getLogin(), expectedLecturer.getLogin());
    log.info("\n-----getLecturerByCourseId: SUCCESS-----\n" + expectedLecturer + "\n----------------------------------------\n");
  }

  @Test
  @Transactional
  public void getLecturers() throws Exception {
    List<Lecturer> expectedLecturers = lecturerDAO.getLecturers();
    assertFalse("getLecturers failed:", expectedLecturers.isEmpty());
    log.info("getLecturers (" + expectedLecturers.size() + " person): SUCCESS");
  }

  @After
  @Transactional
  public void tearDown() throws Exception {
    log.info("Trying delete testLecturer...");
    lecturerDAO.delete(testLecturer);
    log.info("Delete testLecturer: SUCCESS");
  }

}
