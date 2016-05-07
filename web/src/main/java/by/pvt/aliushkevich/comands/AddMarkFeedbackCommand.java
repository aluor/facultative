package by.pvt.aliushkevich.comands;

import by.pvt.aliushkevich.daoServices.LecturerService;
import by.pvt.aliushkevich.exceptions.DaoException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddMarkFeedbackCommand implements ActionCommand {
  static Logger log = Logger.getLogger(AddMarkFeedbackCommand.class);
  String page;
  int courseId;
  int studentId;
  int mark;
  String feedback;

  @Override
  public String execute(HttpServletRequest request) {
    log.info("AddMarkFeedbackCommand used...");
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

    log.info("AddMarkFeedbackCommand recieved: mark= " + mark + " feedback= "
        + feedback + " studentId= " + studentId + " courseId= " + courseId);


    if (feedback == "") {
      request.setAttribute("errorMessage", "Incorrect data or empty field left");
      page = "/jsp/fail.jsp";
    } else {
      try {
        LecturerService.getInstance().addMarkFeedback(mark, feedback, studentId, courseId);
        page = "/jsp/success.jsp";
      } catch (DaoException e) {
        log.error("Error addLearningCourse to student " + e);
        request.setAttribute("errorMessage", "Incorrect data or empty field left");
        page = "/jsp/fail.jsp";
      }
//			if (LecturerService.getInstance().hasMarkFeedback(mark, feedback, studentId, courseId)) {
//				page = "/jsp/success.jsp";
//			} else {
//				request.setAttribute("errorMessage", "Incorrect data or empty field left");
//				page = "/jsp/fail.jsp";
//			}

    }
    log.info("AddMarkFeedbackCommand returned: " + page);
    return page;
  }
}