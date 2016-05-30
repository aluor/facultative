package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.configuration.HibernateConfiguration;
import by.pvt.aliushkevich.daoServices.initialialization.InitEntity;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Student;
import by.pvt.aliushkevich.valueObjects.ClientVO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Rabotnik on 30.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class LecturerServiceTest {
  private static Logger log = Logger.getLogger(LecturerServiceTest.class);
  @Autowired
  private ILecturerService lecturerService;
  @Autowired
  private IStudentService studentService;

  private Student testStudent = new Student();
  private Lecturer testLecturer = new Lecturer();

  @Before
  public void setUp() throws Exception {
    log.info("trying setUp...");
    InitEntity.initLecturer(testLecturer);
    InitEntity.initStudent(testStudent);
    InitEntity.initRelations(testLecturer, testStudent);
    studentService.addClient(testStudent);
    lecturerService.addClient(testLecturer);
    log.info("setUp: SUCCESS");
  }

  @Test
  public void addMarkFeedback() throws Exception {
    log.info("testing addMarkFeedback... ");
    int addedMark = -5;
    int expectedMark = 0;
    String addedFeedback = "addedFeedback";
    String expectedFeedback = null;
    lecturerService.addMarkFeedback(addedMark, addedFeedback, testStudent.getId(), testLecturer.getCourseId());
    Set<ClientVO> expectedStudents = studentService.getLecturerValueStudents(testLecturer.getLogin());
    for (ClientVO expectedStudent : expectedStudents) {
      expectedMark = expectedStudent.getMark();
      expectedFeedback = expectedStudent.getFeedback();
    }
    assertEquals("addMark(Feedback): FAIL", addedMark, expectedMark);
    assertEquals("add(Mark)Feedback: FAIL", addedFeedback, expectedFeedback);
    log.info("addMarkFeedback: SUCCESS");
  }

  @Test
  public void getLecturers() throws Exception {
    log.info("testing getLecturers... ");
    List<Lecturer> expectedLecturers = lecturerService.getLecturers();
    assertFalse("getLecturers: FAIL", expectedLecturers.isEmpty());
    log.info("getLecturers: SUCCESS\n" + expectedLecturers);

  }

  @Test
  public void getCourseIdByLogin() throws Exception {
    log.info("testing getCourseIdByLogin... ");
    int expectedCourseId = lecturerService.getCourseIdByLogin(testLecturer.getLogin());
    assertEquals("getCourseIdByLogin: FAIL", testLecturer.getCourseId(), expectedCourseId);
    log.info("getCourseIdByLogin: SUCCESS");
  }
}