package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.entity.Lecturer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * Created by Rabotnik on 11.04.2016.
 */
public class LecturerDAOTest {

  private LecturerDAO lecturerDAO;
  private Lecturer testLecturer;
  private Lecturer expectedLecturer;
  private ArrayList<Lecturer> lecturers;

  @Before
  public void setUp() throws Exception {
    lecturerDAO = new LecturerDAO();
    testLecturer = new Lecturer();
    testLecturer.setFirstName("testFirstName");
    testLecturer.setLastName("testLastName");
    testLecturer.setLogin("testLogin");
    testLecturer.setPassword("testPassword");
  }

  @Test
  public void addClient() throws Exception {
    lecturerDAO.addClient(testLecturer);
    lecturers = lecturerDAO.getClients();
    expectedLecturer = lecturers.get(lecturers.size() - 1);
    assertEquals("Add lecturer failed: firstName mismatch", testLecturer.getFirstName(), expectedLecturer.getFirstName());
    assertEquals("Add lecturer failed: lastName mismatch", testLecturer.getLastName(), expectedLecturer.getLastName());
    assertEquals("Add lecturer failed: login mismatch", testLecturer.getLogin(), expectedLecturer.getLogin());
    assertEquals("Add lecturer failed: password mismatch", testLecturer.getPassword(), expectedLecturer.getPassword());
  }

  @After
  public void tearDown() throws Exception {
    lecturerDAO.deleteLecturer(testLecturer);
  }
}