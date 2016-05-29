package by.pvt.aliushkevich.controllers;

import by.pvt.aliushkevich.daoServices.ILecturerService;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.valueObjects.ClientVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LecturerController {
  static Logger log = Logger.getLogger(LecturerController.class);
  String page;
  int courseId;
  Integer studentId;
  int mark;
  String feedback;

  @Autowired
  private ILecturerService lecturerService;

  @RequestMapping(value = "/addMarkFeedback", method = RequestMethod.POST)
  public String addMarkFeedback(ModelMap modelMap, @ModelAttribute ClientVO client, HttpSession httpSession) {
    log.info("LecturerController addMarkFeedback used...");
    log.info("Processing client: " + client);

    try {
      String login = (String) httpSession.getAttribute("login");
      try {
        courseId = lecturerService.getCourseIdByLogin(login);
      } catch (DaoException e) {
        log.error("Error getCourseIdByLogin");
        modelMap.addAttribute("errorMessage", "Can not getCourseIdByLogin");
      }
      studentId = client.getId();
      mark = client.getMark();
    } catch (Exception e) {
      log.error("Incorrect data or empty field left");
      modelMap.addAttribute("errorMessage", "Incorrect data or empty field left");
      return "fail";
    }
    feedback = client.getFeedback();
    log.info("LecturerController addMarkFeedback received: mark=" + mark + " feedback="
        + feedback + " studentId=" + studentId + " courseId=" + courseId);
    if (feedback == "") {
      modelMap.addAttribute("errorMessage", "Incorrect data or empty field left");
      page = "fail";
    } else {
      try {
        lecturerService.addMarkFeedback(mark, feedback, studentId, courseId);
        page = "success";
      } catch (DaoException e) {
        log.error("Error addLearningCourse to student " + e);
        modelMap.addAttribute("errorMessage", "Incorrect data or empty field left");
        page = "fail";
      }
    }
    log.info("LecturerController addMarkFeedbackCommand returned: " + page + ".jsp");
    return page;
  }

}
