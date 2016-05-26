package by.pvt.aliushkevich.controllers;

import by.pvt.aliushkevich.daoServices.StudentService;
import by.pvt.aliushkevich.enums.ClientType;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.ClientVO;
import by.pvt.aliushkevich.pojos.Student;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Rabotnik on 25.05.2016.
 */
@Controller
public class StudentController {
  static Logger log = Logger.getLogger(StudentController.class);
  String page;

  @RequestMapping(value = "/chooseLearningCourse", method = RequestMethod.POST)
  public String chooseLearningCourse(ModelMap modelMap, @ModelAttribute ClientVO client, HttpSession httpSession) {
    log.info("StudentController chooseLearningCourse used...");
    log.info("Processing client: " + client);
    String login = (String) httpSession.getAttribute("login");
    int choice = client.getChoice();
    try {
      StudentService.getInstance().addLearningCourse(login, choice);
    } catch (DaoException e) {
      log.info("Incorrect input: You have already signed on that course or this course is not available now");
      modelMap.addAttribute("errorMessage", "Incorrect input: You have already signed on that course or this course is not available now");
      page = "fail";
      log.info("StudentController chooseLearningCourse returned: " + page + ".jsp");
      return page;
    }
    page = "success";
    log.info("StudentController chooseLearningCourse returned: " + page + ".jsp");
    return page;
  }

  @RequestMapping(value = "/studentRegisterPage", method = RequestMethod.GET)
  public String studentRegisterPage(ModelMap modelMap) {
    log.info("StudentController studentRegisterPage used...");
    ClientVO client = new ClientVO();
    modelMap.put("client", client);
    page = "register";
    log.info("StudentController studentRegisterPage returned: " + page + ".jsp");
    return page;
  }

  @RequestMapping(value = "/studentRegister", method = RequestMethod.POST)
  public String studentRegister(ModelMap modelMap, @ModelAttribute ClientVO client, HttpSession httpSession) {
    log.info("StudentController studentRegister used...");
    log.info("Processing client: " + client);
    Student student = new Student();
    student.setFirstName(client.getFirstName());
    student.setLastName(client.getLastName());
    student.setLogin(client.getLogin());
    student.setPassword(client.getPassword());
    if (student.getFirstName() != "" && student.getLastName() != "" && student.getLogin() != "" && student.getPassword() != "") {
      try {
        StudentService.getInstance().addClient(student);
      } catch (DaoException e) {
        log.info("Incorrect input: Try to input another data");
        modelMap.addAttribute("errorMessage", "Incorrect input: Try to input another data");
        page = "fail";
        log.info("StudentController studentRegister returned: " + page + ".jsp");
        return page;
      }
      page = "success";
      httpSession.setAttribute("userType", ClientType.STUDENT);
      log.info("StudentController studentRegister returned: " + page + ".jsp");
      return page;
    } else {
      page = "fail";
      log.info("Incorrect input: Empty field(s) left");
      modelMap.addAttribute("errorMessage", "Incorrect input: Empty field(s) left");
      log.info("StudentController studentRegister returned: " + page + ".jsp");
      return page;
    }
  }

}