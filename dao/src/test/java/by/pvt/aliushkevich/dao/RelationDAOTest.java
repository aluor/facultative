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

import static org.junit.Assert.assertNotNull;

/**
 * Created by Rabotnik on 29.05.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class RelationDAOTest {
  private static Logger log = Logger.getLogger(RelationDAOTest.class);
  private Lecturer testLecturer = new Lecturer();
  private Student testStudent = new Student();

  @Autowired
  private ILecturerDAO lecturerDAO;

  @Autowired
  private IStudentDAO studentDAO;

  @Autowired
  private IRelationDAO relationDAO;

  @Before
  public void setUp() throws Exception {
    InitEntity.initLecturer(testLecturer);
    InitEntity.initStudent(testStudent);
    InitEntity.initRelations(testLecturer, testStudent);
    lecturerDAO.saveOrUpdate(testLecturer);
    studentDAO.saveOrUpdate(testStudent);
  }

  @Test
  public void getRelationInfo() throws Exception {
    log.info("testing getRelationInfo...");
    Lecturer expectedLecturer = lecturerDAO.getLecturerByLogin(testLecturer.getLogin());
    int expectedLecturerId = expectedLecturer.getId();
    Student expectedStudent = studentDAO.getStudentByLogin(testStudent.getLogin());
    int expectedStudentId = expectedStudent.getId();
    int expectedRelationId = relationDAO.getRelationId(expectedStudentId, expectedLecturerId);
    assertNotNull("getRelationId: FAIL (No relation id!)", expectedRelationId);
    int expectedMark = relationDAO.getMark(expectedStudentId, expectedLecturerId);
    assertNotNull("getMark: FAIL", expectedMark);
    String expectedFeedback = relationDAO.getFeedback(expectedStudentId, expectedLecturerId);
    assertNotNull("getFeedback: FAIL", expectedFeedback);
    log.info("getRelationId: SUCCESS (relationId=" + expectedRelationId + " mark=" + expectedMark + " feedback=" + expectedFeedback);

  }

//  No need @After because of @TransactionConfiguration(defaultRollback = true)

}