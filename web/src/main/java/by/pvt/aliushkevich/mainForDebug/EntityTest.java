package by.pvt.aliushkevich.mainForDebug;

import by.pvt.aliushkevich.dao.LecturerDAO;
import by.pvt.aliushkevich.dao.StudentDAO;
import by.pvt.aliushkevich.entity.Lecturer;
import by.pvt.aliushkevich.entity.Student;
import by.pvt.aliushkevich.enums.Courses;

import java.util.ArrayList;

public class EntityTest {

	public static void main(String[] args) {
		Student student1 = new Student("Misha", "Aliushkevich", "login", "password");

		Lecturer lecturer1 = new Lecturer("Olga", "Nicolaevna", "login", "password");
		lecturer1.setTeachesCourse(Courses.MATHEMATICS);
//		lecturer1.setMark(student1, 4);
//		lecturer1.setFeedback(student1, "That student is not bed");

//		System.out.println(lecturer1 + "\n");
//		System.out.println(student1 + "\n");

		StudentDAO studentDAO = new StudentDAO();
		LecturerDAO lecturerDAO = new LecturerDAO();
		
		//studentDAO.addStudent(student1);
		//studentDAO.addLearningCourse("login", 1);
		//lecturerDAO.addLecturer(lecturer1);
		//lecturerDAO.addMarkFeedback(4, "GOOD student", 2, 3);
		
		ArrayList<Student> students = studentDAO.getClients();
		for (Student student : students) {
			System.out.println(student + "\n");
		}
		
		ArrayList<Lecturer> lecturers = lecturerDAO.getClients();
		for (Lecturer lecturer : lecturers) {
			System.out.println(lecturer + "\n");
		}		
		
	}
}