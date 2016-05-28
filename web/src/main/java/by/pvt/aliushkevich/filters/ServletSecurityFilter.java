package by.pvt.aliushkevich.filters;

import by.pvt.aliushkevich.enums.ClientType;
import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/checkLogin", "/logout", "/chooseLearningCourse", "/studentRegisterPage", "/studentRegister", "/addMarkFeedback"}, servletNames = {"MainController", "StudentController", "LecturerController"})

public class ServletSecurityFilter implements Filter {
  static Logger log = Logger.getLogger(ServletSecurityFilter.class);

  public void init(FilterConfig fConfig) throws ServletException {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    log.info("ServletSecurityFilter used...");
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    ClientType type = (ClientType) session.getAttribute("userType");
    if (type == null) {
      type = ClientType.GUEST;
      session.setAttribute("userType", type);
    }
    chain.doFilter(request, response);
//    BaseDAO.util.closeSession();
//    log.info("Hibernate session closed!");
  }

  public void destroy() {
  }

}
