package by.pvt.aliushkevich.dao;

import by.pvt.aliushkevich.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rabotnik on 13.04.2016.
 */
public class StudentDAOTest {

  private StudentDAO studentDAO;
  private Student testStudent;
  private Student expectedStudent;
  private ArrayList<Student> students;

  @Before
  public void setUp() throws Exception {
    studentDAO = new StudentDAO();
    testStudent = new Student();
    testStudent.setFirstName("testFirstName");
    testStudent.setLastName("testLastName");
    testStudent.setLogin("testLogin");
    testStudent.setPassword("testPassword");
  }

  @Test
  public void addClient() throws Exception {
    studentDAO.addClient(testStudent);
    students = studentDAO.getClients();
    expectedStudent = students.get(students.size() - 1);
    assertEquals("Add lecturer failed: firstName mismatch", testStudent.getFirstName(), expectedStudent.getFirstName());
    assertEquals("Add lecturer failed: lastName mismatch", testStudent.getLastName(), expectedStudent.getLastName());
    assertEquals("Add lecturer failed: login mismatch", testStudent.getLogin(), expectedStudent.getLogin());
    assertEquals("Add lecturer failed: password mismatch", testStudent.getPassword(), expectedStudent.getPassword());
  }

  @After
  public void tearDown() throws Exception {
    studentDAO.deleteStudent(testStudent);
  }
}