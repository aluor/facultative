package by.pvt.aliushkevich.comands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
/*
 * в случае ошибки или прямого обращения к контроллеру переадресация на
 * страницу ввода логина
 */

public class EmptyCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(EmptyCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request) {
		logger.debug("EmptyCommand used...");
		String page = "/jsp/login.jsp";
		logger.debug("EmptyCommand returned: " +page);
		return page;
	}
}