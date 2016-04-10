package by.pvt.aliushkevich.comands;

import by.pvt.aliushkevich.daoServices.StudentService;
import by.pvt.aliushkevich.entity.Student;
import by.pvt.aliushkevich.enums.ClientType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class StudentRegisterCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(StudentRegisterCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request) {
		  logger.debug("StudentRegisterCommand used...");
		String page = null;
		HttpSession session = request.getSession();		
		Student student = new Student();
		student.setFirstName(request.getParameter("firstName"));
		student.setLastName(request.getParameter("lastName"));
		student.setLogin(request.getParameter("login"));
		student.setPassword(request.getParameter("password"));
		StudentService studentService = new StudentService();
		try {
			studentService.addStudent(student);
		} catch (SQLException e) {
			logger.debug("Incorrect input: Try to input another data");
			request.setAttribute("errorMessage", "Incorrect input: Try to input another data");
			page = "/jsp/fail.jsp";
			logger.debug("StudentRegisterCommand returned: " +page);
			return page;
		}		
		page = "/jsp/success.jsp";
		session.setAttribute("userType", ClientType.STUDENT);
		logger.debug("StudentRegisterCommand returned: " +page);
		return page;
	}
}