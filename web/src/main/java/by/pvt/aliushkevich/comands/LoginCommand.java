package by.pvt.aliushkevich.comands;

import by.pvt.aliushkevich.constants.Const;
import by.pvt.aliushkevich.daoServices.StudentService;
import by.pvt.aliushkevich.enums.ClientType;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.logic.LoginLogic;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
  static Logger log = Logger.getLogger(LoginCommand.class);

  @Override
  public String execute(HttpServletRequest request) {
    log.info("LoginCommand used...");
    String page;
    String login = request.getParameter("login");
    String pass = request.getParameter("password");
    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(Const.SESSION_LIFETIME);
    session.setAttribute("sessionLifetime", Const.SESSION_LIFETIME);
    session.setAttribute("user", login);

    if (LoginLogic.checkLecturerLogin(login, pass)) {
      session.setAttribute("userType", ClientType.LECTURER);
      try {
        request.setAttribute("students", StudentService.getInstance().getLecturerValueStudents(login));
      } catch (DaoException e) {
        request.setAttribute("students", "Can not get students..." +e);
      }
      page = "/jsp/lecturer.jsp";
    } else if (LoginLogic.checkStudentLogin(login, pass)) {
      session.setAttribute("userType", ClientType.STUDENT);
      page = "/jsp/student.jsp";
    } else {
      request.setAttribute("errorMessage",
          "INCORRECT LOGIN OR PASSWORD! (please, try again)");
      request.getSession().setAttribute("userType", ClientType.GUEST);
      page = "/jsp/login.jsp";
    }
    log.info("LoginCommand returned: " + page);
    return page;
  }

}
