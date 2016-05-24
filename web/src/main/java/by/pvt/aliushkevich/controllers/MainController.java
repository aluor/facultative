package by.pvt.aliushkevich.controllers;

import by.pvt.aliushkevich.pojos.Student;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;

//@WebServlet("/controller")
@Controller
public class MainController extends HttpServlet {
  static Logger log = Logger.getLogger(MainController.class);

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String mainPage(ModelMap model) {
    Student tempStudent = new Student();
    tempStudent.setLogin("Проверка передачи логина");
    model.addAttribute("testMessage", "This is test message!");
    model.put("tempStudent", tempStudent);
    return "login";
  }

  @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
  public String checkLogin(ModelMap model, @ModelAttribute Student tempStudent) {
    log.info("tempStudent= " + tempStudent);
    return "fail";
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

}
