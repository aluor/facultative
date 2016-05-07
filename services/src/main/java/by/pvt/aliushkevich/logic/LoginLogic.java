package by.pvt.aliushkevich.logic;

import by.pvt.aliushkevich.daoServices.LecturerService;
import by.pvt.aliushkevich.daoServices.StudentService;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Student;
import org.apache.log4j.Logger;

import java.util.List;

public class LoginLogic {
  static Logger log = Logger.getLogger(LoginLogic.class);

  public static boolean checkLecturerLogin(String enterLogin, String enterPass) {
    boolean check = false;
    List<Lecturer> lecturers = LecturerService.getInstance().getLecturers();
    for (Lecturer lecturer : lecturers) {
      if (lecturer.getLogin().equals(enterLogin) && lecturer.getPassword().equals(enterPass)) {
        check = true;
        break;
      }
    }
    log.info("Lecturers login/password checked: " + check);
    return check;
  }

  public static boolean checkStudentLogin(String enterLogin, String enterPass) {
    boolean check = false;
    List<Student> students = StudentService.getInstance().getStudents();
    for (Student student : students) {
      if (student.getLogin().equals(enterLogin) && student.getPassword().equals(enterPass)) {
        check = true;
        break;
      }
    }
    log.info("Students login/password checked: " + check);
    return check;
  }
}