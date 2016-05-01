package by.pvt.aliushkevich.comands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddLearningCourseCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(AddLearningCourseCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request) {
		  logger.debug("AddLearningCourseCommand used...");
		String page = null;
		// извлечение из запроса выбора курса обучения
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("user");
		int choice = Integer.parseInt(request.getParameter("choise"));
//		TODO
//		try {
//
//			StudentService.getInstance().addLearningCourse(login, choice);
//		} catch (SQLException e) {
//			System.err.println("Incorrect input: You have already signed on that course");
//			request.setAttribute("errorMessage", "Incorrect input: You have already signed on that course");
//			page = "/jsp/fail.jsp";
//			logger.debug("AddLearningCourseCommand returned: " +page);
//			return page;
//		}
		page = "/jsp/success.jsp";
		logger.debug("AddLearningCourseCommand returned: " +page);
		return page;
	}
}