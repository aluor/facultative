package by.pvt.aliushkevich.comands;

import by.pvt.aliushkevich.dao.BaseDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
  static Logger log = Logger.getLogger(LogoutCommand.class);

  @Override
  public String execute(HttpServletRequest request) {
    log.info("LogoutCommand used...");
    String page = "/index.jsp";
    // уничтожение сессии
    request.getSession().invalidate();
    BaseDAO.util.closeSession();
    log.info("LogoutCommand returned: " + page);
    return page;
  }
}