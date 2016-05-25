package by.pvt.aliushkevich.controllers;

import by.pvt.aliushkevich.daoServices.StudentService;
import by.pvt.aliushkevich.enums.ClientType;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.logic.LoginLogic;
import by.pvt.aliushkevich.pojos.Client;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
  static Logger log = Logger.getLogger(MainController.class);
  String page;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String mainPage(ModelMap modelMap) {
    page = "login";
    log.info("MainController mainPage used...");
    Client client = new Client();
    modelMap.put("client", client);
    log.info("MainController mainPage returned: " + page + ".jsp");
    return page;
  }

  @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
  public String checkLogin(ModelMap modelMap, @ModelAttribute Client client, HttpSession httpSession) {
    log.info("MainController checkLogin used...");
    log.info("Processing client: " + client);
    String login = client.getLogin();
    String password = client.getPassword();
    modelMap.addAttribute("user", login.toString());
    httpSession.setAttribute("login", login);
    if (LoginLogic.checkLecturerLogin(login, password)) {
      modelMap.put("userType", ClientType.LECTURER);
      try {
        modelMap.put("students", StudentService.getInstance().getLecturerValueStudents(login));
      } catch (DaoException e) {
        modelMap.addAttribute("students", "Can not get students..." + e);
      }
      page = "lecturer";

    } else if (LoginLogic.checkStudentLogin(login, password)) {
      modelMap.put("userType", ClientType.STUDENT);
      page = "student";

    } else {
      modelMap.addAttribute("errorMessage",
          "INCORRECT LOGIN OR PASSWORD! (please, try again)");
      modelMap.put("userType", ClientType.GUEST);
      page = "login";
    }

    log.info("MainController checkLogin returned: " + page + ".jsp");
    return page;
  }


  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(ModelMap modelMap) {
    page = "login";
    log.info("MainController logout used...");
    modelMap.clear();
    Client client = new Client();
    modelMap.put("client", client);
    log.info("MainController logout returned: " + page + ".jsp");
    return page;
  }
}

 /* protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    log.info("controller used...");
    String page;
    // определение команды, пришедшей из JSP
    String action = request.getParameter("command");
    log.info("controller received parameter: " + action);
    *//*
     * вызов реализованного метода execute() и передача параметров
		 * классу-обработчику конкретной команды
		 *//*
    ActionCommand command;
    switch (action) {
      case "login":
        command = new LoginCommand();
        break;
      case "logout":
        command = new LogoutCommand();
        break;
      case "chooseLearningCourse":
        command = new AddLearningCourseCommand();
        break;
      case "addMarkFeedback":
        command = new AddMarkFeedbackCommand();
        break;
      case "studentRegisterPage":
        command = new StudentRegisterPageCommand();
        break;
      case "studentRegister":
        command = new StudentRegisterCommand();
        break;
      default:
        command = new EmptyCommand();
        break;
    }

    page = command.execute(request);
    if (page != null) {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
      // вызов страницы ответа на запрос
      dispatcher.forward(request, response);
      log.info("controller forward client to: " + page);
    } else {
      // установка страницы c сообщением об ошибке
      page = "/jsp/login.jsp";
      request.getSession().setAttribute("nullPage", "Page not found. Business logic error");
      response.sendRedirect(request.getContextPath() + page);
      log.error("controller could not found the page");
    }
  }*/


