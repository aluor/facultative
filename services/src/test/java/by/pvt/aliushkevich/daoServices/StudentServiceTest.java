package by.pvt.aliushkevich.daoServices;

import by.pvt.aliushkevich.configuration.HibernateConfiguration;
import by.pvt.aliushkevich.dao.IStudentDAO;
import by.pvt.aliushkevich.daoServices.initialialization.InitEntity;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Relation;
import by.pvt.aliushkevich.pojos.Student;
import by.pvt.aliushkevich.valueObjects.ClientVO;
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
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Rabotnik on 29.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
//@TransactionConfiguration(defaultRollback = true)
@Transactional
public class StudentServiceTest {
  private static Logger log = Logger.getLogger(StudentServiceTest.class);
  @Autowired
  private ILecturerService lecturerService;
  @Autowired
  private IStudentDAO studentDAO;
  @Autowired
  private IStudentService studentService;

  private Student testStudent = new Student();
  private Lecturer testLecturer = new Lecturer();

  @Before
  public void setUp() throws Exception {
    log.info("trying setUp...");
    InitEntity.initLecturer(testLecturer);
    InitEntity.initStudent(testStudent);
    studentService.addClient(testStudent);
    lecturerService.addClient(testLecturer);
    log.info("setUp: SUCCESS");
  }

  @Test
  public void addLearningCourse() throws Exception {
    log.info("testing addLearningCourse... ");
    studentService.addLearningCourse(testStudent.getLogin(), testLecturer.getCourseId());
    Set<Relation> relations = studentDAO.getStudentByLogin(testStudent.getLogin()).getRelations();
    Integer expectedCourseId = null;
    for (Relation relation : relations) {
      expectedCourseId = relation.getLecturer().getCourseId();
    }
    assertEquals("addLearningCourse: FAIL", testLecturer.getCourseId(), (int) expectedCourseId);
    log.info("addLearningCourse: SUCCESS");
  }

  @Test
  public void getLecturerValueStudents() throws Exception {
    log.info("testing getLecturerValueStudents... ");
    InitEntity.initRelations(testLecturer, testStudent);
    studentService.addClient(testStudent);
    lecturerService.addClient(testLecturer);
    Set<ClientVO> lecturerValueStudents = studentService.getLecturerValueStudents(testLecturer.getLogin());
    assertNotNull("getLecturerValueStudents: FAIL", lecturerValueStudents);
    log.info("getLecturerValueStudents: SUCCESS\n" + lecturerValueStudents);
  }

  @Test
  public void getStudents() throws Exception {
    log.info("testing getStudents... ");
    List<Student> expectedStudents= studentService.getStudents();
    assertFalse("getStudents: FAIL", expectedStudents.isEmpty());
    log.info("getStudents: SUCCESS\n" + expectedStudents);
  }


  @After
  public void tearDown() throws Exception {
    studentService.deleteClient(testStudent);
    lecturerService.deleteClient(testLecturer);
  }
}