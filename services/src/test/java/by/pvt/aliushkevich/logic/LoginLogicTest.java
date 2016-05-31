package by.pvt.aliushkevich.logic;

import by.pvt.aliushkevich.configuration.HibernateConfiguration;
import by.pvt.aliushkevich.daoservices.ILecturerService;
import by.pvt.aliushkevich.daoservices.IStudentService;
import by.pvt.aliushkevich.daoservices.initialialization.InitEntity;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Rabotnik on 30.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class LoginLogicTest {
  private static Logger log = Logger.getLogger(LoginLogicTest.class);
  @Autowired
  private ILecturerService lecturerService;
  @Autowired
  private IStudentService studentService;
  @Autowired
  private ILoginLogic loginLogic;

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
  public void checkLecturerLogin() throws Exception {
    log.info("testing checkLecturerLogin... ");
    assertTrue(loginLogic.checkLecturerLogin(testLecturer.getLogin(), testLecturer.getPassword()));
    assertFalse(loginLogic.checkLecturerLogin(testLecturer.getLogin(), testLecturer.getPassword()+"someText"));
    log.info("checkLecturerLogin: SUCCESS");
  }

  @Test
  public void checkStudentLogin() throws Exception {
    log.info("testing checkStudentLogin... ");
    assertTrue(loginLogic.checkStudentLogin(testStudent.getLogin(), testStudent.getPassword()));
    assertFalse(loginLogic.checkStudentLogin(testStudent.getLogin(), testStudent.getPassword()+"someText"));
    log.info("checkStudentLogin: SUCCESS");
  }
}