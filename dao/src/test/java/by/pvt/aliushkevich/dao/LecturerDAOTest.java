
package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.configuration.HibernateConfiguration;
import by.pvt.aliushkevich.dao.initialialization.InitEntity;
import by.pvt.aliushkevich.pojos.Lecturer;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
//@TransactionConfiguration(defaultRollback = true)
@Transactional
public class LecturerDAOTest {

  private static Logger log = Logger.getLogger(LecturerDAOTest.class);
  @Autowired
  private ILecturerDAO lecturerDAO;
  private Lecturer testLecturer = new Lecturer();

  @Before
  public void setUp() throws Exception {
    log.info("trying setUp testLecturer...");
    InitEntity.initLecturer(testLecturer);
    lecturerDAO.saveOrUpdate(testLecturer);
    log.info("setUp testLecturer: SUCCESS");
  }

  @Test
  public void get() throws Exception {
    log.info("testing get... (testLecturer=" + testLecturer + ")");
    Lecturer expectedLecturerByLogin = lecturerDAO.getLecturerByLogin(testLecturer.getLogin());
    Lecturer expectedLecturerById = lecturerDAO.get(expectedLecturerByLogin.getId());
    assertEquals("get (lecturer by id) failed: expectedLecturerByLogin mismatch expectedLecturerById", expectedLecturerByLogin, expectedLecturerById);
    log.info("get (lecturer by id): SUCCESS");
  }

  @Test
  public void getLecturerByLogin() throws Exception {
    log.info("testing getLecturerByLogin... (testLecturer=" + testLecturer + ")");
    Lecturer expectedLecturer = lecturerDAO.getLecturerByLogin(testLecturer.getLogin());
    log.info("(get expectedLecturer:=" + expectedLecturer + ")");
    assertEquals("getLecturerByLogin failed: testLecturer mismatch expectedLecturer", testLecturer.getLogin(), expectedLecturer.getLogin());
    log.info("getLecturerByLogin: SUCCESS");
  }



  @Test
  public void getCourseIdByLogin() throws Exception {
    log.info("Testing getCourseIdByLogin: " + testLecturer.getLogin() + ":...");
    int expectedCourseId = lecturerDAO.getCourseIdByLogin(testLecturer.getLogin());
    assertEquals("getCourseIdByLogin failed: testCourseId mismatch expectedCourseId", testLecturer.getCourseId(), expectedCourseId);
    log.info("getCourseIdByLogin: SUCCESS");
  }

  @Test
  public void getLecturerByCourseId() throws Exception {
    log.info("Testing getLecturerByCourseId: " + testLecturer.getCourseId() + "...");
    Lecturer expectedLecturer = lecturerDAO.getLecturerByCourseId(testLecturer.getCourseId());
    assertEquals("getLecturerByCourseId failed: testLecturer mismatch expectedLecturer", testLecturer.getLogin(), expectedLecturer.getLogin());
    log.info("\n-----getLecturerByCourseId: SUCCESS-----\n" + expectedLecturer + "\n----------------------------------------\n");
  }

  @Test
  public void getLecturers() throws Exception {
    List<Lecturer> expectedLecturers = lecturerDAO.getLecturers();
    assertFalse("getLecturers failed:", expectedLecturers.isEmpty());
    log.info("getLecturers (" + expectedLecturers.size() + " person): SUCCESS");
  }

  @After
  public void tearDown() throws Exception {
    log.info("Trying delete testLecturer...");
    lecturerDAO.delete(testLecturer);
    log.info("Delete testLecturer: SUCCESS");
  }

}
