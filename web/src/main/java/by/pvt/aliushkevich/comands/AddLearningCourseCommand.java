package by.pvt.aliushkevich.comands;

import by.pvt.aliushkevich.daoServices.StudentService;
import by.pvt.aliushkevich.exceptions.DaoException;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddLearningCourseCommand implements ActionCommand {
	static Logger log = Logger.getLogger(AddLearningCourseCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		  log.info("AddLearningCourseCommand used...");
		String page;
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("user");
		int choice = Integer.parseInt(request.getParameter("choise"));
		try {
			StudentService.getInstance().addLearningCourse(login, choice);
		} catch (DaoException e) {
			log.info("Incorrect input: You have already signed on that course or this course is not available now");
			request.setAttribute("errorMessage", "Incorrect input: You have already signed on that course or this course is not available now");
			page = "/jsp/fail.jsp";
			log.info("AddLearningCourseCommand returned: " +page);
			return page;
		}
		page = "/jsp/success.jsp";
		log.info("AddLearningCourseCommand returned: " +page);
		return page;
	}
}