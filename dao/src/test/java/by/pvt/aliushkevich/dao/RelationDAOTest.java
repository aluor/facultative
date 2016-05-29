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
  public void getRelationId() throws Exception {
    log.info("testing getRelationId...");
    Lecturer expectedLecturer = lecturerDAO.getLecturerByLogin(testLecturer.getLogin());
    int expectedLecturerId = expectedLecturer.getId();
    Student expectedStudent = studentDAO.getStudentByLogin(testStudent.getLogin());
    int expectedStudentId = expectedStudent.getId();
    int relationId = relationDAO.getRelationId(expectedStudentId, expectedLecturerId);
    int expectedMark = relationDAO.getMark(expectedStudentId, expectedLecturerId);
    String expectedFeedback = relationDAO.getFeedback(expectedStudentId, expectedLecturerId);
    log.info("getRelationId: SUCCESS (relationId=" + relationId + " mark=" + expectedMark + " feedback=" + expectedFeedback);
  }

//  No need @After because of @TransactionConfiguration(defaultRollback = true)

}