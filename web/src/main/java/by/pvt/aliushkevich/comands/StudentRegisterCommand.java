package by.pvt.aliushkevich.comands;

import by.pvt.aliushkevich.daoServices.StudentService;
import by.pvt.aliushkevich.enums.ClientType;
import by.pvt.aliushkevich.exceptions.DaoException;
import by.pvt.aliushkevich.pojos.Student;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StudentRegisterCommand implements ActionCommand {
  static Logger log = Logger.getLogger(StudentRegisterCommand.class);

  @Override
  public String execute(HttpServletRequest request) {
    log.info("StudentRegisterCommand used...");
    String page;
    HttpSession session = request.getSession();
    Student student = new Student();
    student.setFirstName(request.getParameter("firstName"));
    student.setLastName(request.getParameter("lastName"));
    student.setLogin(request.getParameter("login"));
    student.setPassword(request.getParameter("password"));
    if (request.getParameter("firstName") != "" && request.getParameter("lastName") != "" && request.getParameter("login") != "" && request.getParameter("password") != "") {
      try {
        StudentService.getInstance().addClient(student);
      } catch (DaoException e) {
        log.info("Incorrect input: Try to input another data");
        request.setAttribute("errorMessage", "Incorrect input: Try to input another data");
        page = "/jsp/fail.jsp";
        log.info("StudentRegisterCommand returned: " + page);
        return page;
      }
      page = "/jsp/success.jsp";
      session.setAttribute("userType", ClientType.STUDENT);
      log.info("StudentRegisterCommand returned: " + page);
      return page;
    } else {
      page = "/jsp/fail.jsp";
      log.info("Incorrect input: Empty field(s) left");
      request.setAttribute("errorMessage", "Incorrect input: Empty field(s) left");
      return page;
    }
  }

}
