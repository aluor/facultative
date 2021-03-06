package by.pvt.aliushkevich.logic;

import by.pvt.aliushkevich.daoServices.LecturerService;
import by.pvt.aliushkevich.daoServices.StudentService;
import by.pvt.aliushkevich.entity.Lecturer;
import by.pvt.aliushkevich.entity.Student;
import org.apache.log4j.Logger;
import java.util.ArrayList;

public class LoginLogic {
	static Logger logger = Logger.getLogger(LoginLogic.class.getName());

	public static boolean checkLecturerLogin(String enterLogin, String enterPass) {
		boolean check = false;
		ArrayList<Lecturer> lecturers = LecturerService.getInstance().getLecturers();
		for (Lecturer lecturer : lecturers) {
			if (lecturer.getLogin().equals(enterLogin) && lecturer.getPassword().equals(enterPass)) {
				check = true;
				break;
			}
		}
		logger.debug("Lecturers login/password checked: " + check);
		return check;
	}

	public static boolean checkStudentLogin(String enterLogin, String enterPass) {
		boolean check = false;		
		ArrayList<Student> students = StudentService.getInstance().getStudents();
		for (Student student : students) {
			if (student.getLogin().equals(enterLogin) && student.getPassword().equals(enterPass)) {
				check = true;
				break;
			}
		}
		logger.debug("Students login/password checked: " + check);
		return check;
	}
}