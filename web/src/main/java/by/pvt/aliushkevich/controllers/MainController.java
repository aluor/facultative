package by.pvt.aliushkevich.controllers;

import by.pvt.aliushkevich.daoservices.IStudentService;
import by.pvt.aliushkevich.enums.ClientType;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.logic.ILoginLogic;
import by.pvt.aliushkevich.valueObjects.ClientVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Handles the very first request and requests of user authentication
 */
@Controller
public class MainController {
  static Logger log = Logger.getLogger(MainController.class);
  String page;

  @Autowired
  private IStudentService studentService;

  @Autowired
  private ILoginLogic loginLogic;
  /**
   * Handles the very first request
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String mainPage(ModelMap modelMap) {
    page = "login";
    log.info("MainController mainPage used...");
    ClientVO client = new ClientVO();
    modelMap.put("client", client);
    log.info("MainController mainPage returned: " + page + ".jsp");
    return page;
  }

  /**
   * Handles checkLogin request
   * @see ILoginLogic
   */
  @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
  public String checkLogin(ModelMap modelMap, @ModelAttribute ClientVO client, HttpSession httpSession) {
    log.info("MainController checkLogin used...");
    log.info("Processing client: "+client);
    String login = client.getLogin();
    String password = client.getPassword();
    modelMap.put("client", client);
    httpSession.setAttribute("login", login);
    if (loginLogic.checkLecturerLogin(login, password)) {
      httpSession.setAttribute("userType", ClientType.LECTURER);
      try {
        modelMap.put("students", studentService.getLecturerValueStudents(login));
      } catch (DaoException e) {
        modelMap.addAttribute("students", "Can not get students..." + e);
      }
      page = "lecturer";

    } else if (loginLogic.checkStudentLogin(login, password)) {
      httpSession.setAttribute("userType", ClientType.STUDENT);
      page = "student";

    } else {
      modelMap.addAttribute("errorMessage",
          "INCORRECT LOGIN OR PASSWORD! (please, try again)");
      httpSession.setAttribute("userType", ClientType.GUEST);
      page = "login";
    }

    log.info("MainController checkLogin returned: " + page + ".jsp");
    return page;
  }

  /**
   * Handles logout request
   */
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(ModelMap modelMap, HttpSession httpSession) {
    page = "login";
    log.info("MainController logout used...");
    httpSession.invalidate();
    modelMap.clear();
    ClientVO client = new ClientVO();
    modelMap.put("client", client);
    log.info("MainController logout returned: " + page + ".jsp");
    return page;
  }

}