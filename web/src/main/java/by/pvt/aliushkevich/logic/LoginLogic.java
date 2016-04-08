package by.pvt.aliushkevich.logic;

import by.pvt.aliushkevich.dao.LecturerDAO;
import by.pvt.aliushkevich.dao.StudentDAO;
import by.pvt.aliushkevich.entity.Lecturer;
import by.pvt.aliushkevich.entity.Student;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class LoginLogic {
	static Logger logger = Logger.getLogger(LoginLogic.class.getName());

	public static boolean checkLecturerLogin(String enterLogin, String enterPass) {
		boolean check = false;		
		LecturerDAO lecturerDAO = new LecturerDAO();
		ArrayList<Lecturer> lecturers = lecturerDAO.getLecturers();
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
		StudentDAO studentDAO = new StudentDAO();
		ArrayList<Student> students = studentDAO.getStudents();
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