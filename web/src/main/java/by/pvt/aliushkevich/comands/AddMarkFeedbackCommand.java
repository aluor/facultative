package by.pvt.aliushkevich.comands;

import by.pvt.aliushkevich.daoServices.LecturerService;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class AddMarkFeedbackCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(AddMarkFeedbackCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request) {
		  logger.debug("AddMarkFeedbackCommand used...");
		String page = null;
		int studentId = -1;
		String feedback = "";
		// извлечение из запроса выбора курса обучения
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		try {
			studentId = Integer.parseInt(request.getParameter("studentId"));
		} catch (NumberFormatException e) {
			System.err.println("Incorrect data or empty field \"studentId\"");
			request.setAttribute("errorMessage", "Incorrect data or empty field \"studentId\"");
			page = "/jsp/fail.jsp";
		}
		int mark = Integer.parseInt(request.getParameter("mark"));
		feedback = request.getParameter("feedback");		
		
		 logger.debug("AddMarkFeedbackCommand recieved: mark= " + mark + " feedback= "
		+ feedback + " studentId= " + studentId + " courseId= " + courseId);

		if (feedback == "") {
			request.setAttribute("errorMessage", "Incorrect data or empty field \"feedback\"");
			page = "/jsp/fail.jsp";
		} else {
			LecturerService lecturerService = new LecturerService();
			lecturerService.addMarkFeedback(mark, feedback, studentId, courseId);
			if (lecturerService.hasMarkFeedback(mark, feedback, studentId, courseId)) {
				page = "/jsp/success.jsp";
			} else {
				request.setAttribute("errorMessage", "Incorrect data or empty field left");
				page = "/jsp/fail.jsp";
			}
		}
		logger.debug("AddMarkFeedbackCommand returned: " +page);
		return page;
	}
}