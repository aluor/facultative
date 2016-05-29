
package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.configuration.HibernateConfiguration;
import by.pvt.aliushkevich.dao.initialialization.InitEntity;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Student;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class StudentDAOTest {
  private static Logger log = Logger.getLogger(StudentDAOTest.class);
  @Autowired
  private ILecturerDAO lecturerDAO;
  @Autowired
  private IStudentDAO studentDAO;
  private Student testStudent = new Student();
  private Lecturer testLecturer = new Lecturer();

  @Before
  public void setUp() throws Exception {
    log.info("trying setUp testStudent...");
    InitEntity.initStudent(testStudent);
    studentDAO.saveOrUpdate(testStudent);
    log.info("setUp testStudent: SUCCESS");
  }

  @Test
  public void getStudentByLogin() throws Exception {
    log.info("testing getStudentByLogin... (testStudent=" + testStudent + ")");
    Student expectedStudent = studentDAO.getStudentByLogin(testStudent.getLogin());
    log.info("(get expectedStudent: " + expectedStudent + ")");
    assertEquals("getStudentByLogin failed: testStudent mismatch expectedStudent", testStudent.getLogin(), expectedStudent.getLogin());
    log.info("getStudentByLogin: SUCCESS");
  }

  @Test
  public void getStudents() throws Exception {
    List<Student> expectedStudents = studentDAO.getStudents();
    assertFalse("getStudents failed:", expectedStudents.isEmpty());
    log.info("getStudents (" + expectedStudents.size() + " person): SUCCESS");
  }

  @Test
  public void getLecturerStudents() throws Exception {
    log.info("testing getLecturerStudents...");
    InitEntity.initLecturer(testLecturer);
    lecturerDAO.saveOrUpdate(testLecturer);
    InitEntity.initRelations(testLecturer,testStudent);
    lecturerDAO.saveOrUpdate(testLecturer);
    Set<Student> expectedLecturerStudents = studentDAO.getLecturerStudents(testLecturer.getLogin());
    log.info("getLecturerStudents (" + expectedLecturerStudents);
    assertFalse("getLecturerStudents failed:", expectedLecturerStudents.isEmpty());
    log.info("getLecturerStudents (" + expectedLecturerStudents.size() + " person): SUCCESS");
    lecturerDAO.delete(testLecturer);
  }

//  No need any more because of: @TransactionConfiguration(defaultRollback = true)
//  @After
//  public void tearDown() throws Exception {
//    log.info("Trying delete testStudent...");
//    studentDAO.delete(testStudent);
//    log.info("Delete testStudent: SUCCESS");
//  }

}
