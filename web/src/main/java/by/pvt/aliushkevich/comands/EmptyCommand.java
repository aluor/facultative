package by.pvt.aliushkevich.comands;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
/*
 * в случае ошибки или прямого обращения к контроллеру переадресация на
 * страницу ввода логина
 */

public class EmptyCommand implements ActionCommand {
  static Logger log = Logger.getLogger(EmptyCommand.class);

  @Override
  public String execute(HttpServletRequest request) {
    log.info("EmptyCommand used...");
    String page = "/jsp/login.jsp";
    log.info("EmptyCommand returned: " + page);
    return page;
  }
}