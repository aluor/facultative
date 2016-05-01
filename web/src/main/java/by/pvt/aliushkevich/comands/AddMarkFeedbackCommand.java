package by.pvt.aliushkevich.comands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddMarkFeedbackCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(AddMarkFeedbackCommand.class.getName());
  String page;
  int courseId;
  int studentId;
  int mark;
  String feedback;

  @Override
	public String execute(HttpServletRequest request) {
		  logger.debug("AddMarkFeedbackCommand used...");

		// извлечение из запроса выбора курса обучения
		try {
      courseId = Integer.parseInt(request.getParameter("courseId"));
			studentId = Integer.parseInt(request.getParameter("studentId"));
      mark = Integer.parseInt(request.getParameter("mark"));
		} catch (NumberFormatException e) {
			System.err.println("Incorrect data or empty field left");
			request.setAttribute("errorMessage", "Incorrect data or empty field left");
			return page = "/jsp/fail.jsp";
		}
		feedback = request.getParameter("feedback");		
		
		 logger.debug("AddMarkFeedbackCommand recieved: mark= " + mark + " feedback= "
		+ feedback + " studentId= " + studentId + " courseId= " + courseId);

		//TODO
//		if (feedback == "") {
//			request.setAttribute("errorMessage", "Incorrect data or empty field left");
//			page = "/jsp/fail.jsp";
//		} else {
//			LecturerService.getInstance().addMarkFeedback(mark, feedback, studentId, courseId);
//			if (LecturerService.getInstance().hasMarkFeedback(mark, feedback, studentId, courseId)) {
//				page = "/jsp/success.jsp";
//			} else {
//				request.setAttribute("errorMessage", "Incorrect data or empty field left");
//				page = "/jsp/fail.jsp";
//			}
//		}

		logger.debug("AddMarkFeedbackCommand returned: " +page);
		return page;
	}
}