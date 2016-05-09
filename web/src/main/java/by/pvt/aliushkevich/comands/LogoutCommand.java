package by.pvt.aliushkevich.comands;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
  static Logger log = Logger.getLogger(LogoutCommand.class);

  @Override
  public String execute(HttpServletRequest request) {
    log.info("LogoutCommand used...");
    String page = "/index.jsp";
    request.getSession().invalidate();
    log.info("LogoutCommand returned: " + page);
    return page;
  }

}