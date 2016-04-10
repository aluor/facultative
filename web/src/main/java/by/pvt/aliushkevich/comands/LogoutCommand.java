package by.pvt.aliushkevich.comands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
  static Logger logger = Logger.getLogger(LogoutCommand.class.getName());

  @Override
  public String execute(HttpServletRequest request) {
    logger.debug("LogoutCommand used...");
    String page = "/index.jsp";
    // уничтожение сессии
    request.getSession().invalidate();
    logger.debug("LogoutCommand returned: " + page);
    return page;
  }
}