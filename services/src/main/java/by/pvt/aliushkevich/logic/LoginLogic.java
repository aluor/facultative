package by.pvt.aliushkevich.logic;

import by.pvt.aliushkevich.daoServices.ILecturerService;
import by.pvt.aliushkevich.daoServices.IStudentService;
import by.pvt.aliushkevich.pojos.Lecturer;
import by.pvt.aliushkevich.pojos.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loginLogic")
public class LoginLogic implements ILoginLogic{
  static Logger log = Logger.getLogger(LoginLogic.class);

  @Autowired
  private ILecturerService lecturerService;
  @Autowired
  private IStudentService studentService;


  public boolean checkLecturerLogin(String enterLogin, String enterPass) {
    boolean check = false;
    List<Lecturer> lecturers = lecturerService.getLecturers();
    for (Lecturer lecturer : lecturers) {
      if (lecturer.getLogin().equals(enterLogin) && lecturer.getPassword().equals(enterPass)) {
        check = true;
        break;
      }
    }
    log.info("Lecturers login/password checked: " + check);
    return check;
  }

  public boolean checkStudentLogin(String enterLogin, String enterPass) {
    boolean check = false;
    List<Student> students = studentService.getStudents();
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