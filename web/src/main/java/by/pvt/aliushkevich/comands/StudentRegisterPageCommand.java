package by.pvt.aliushkevich.comands;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class StudentRegisterPageCommand implements ActionCommand {
	static Logger logger = Logger.getLogger(StudentRegisterPageCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request) {
		logger.debug("StudentRegisterPageCommand used...");
		String page = "/jsp/register.jsp";		
		logger.debug("StudentRegisterPageCommand returned: " + page);
		return page;
	}
}
