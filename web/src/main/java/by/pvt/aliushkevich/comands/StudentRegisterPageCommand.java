package by.pvt.aliushkevich.comands;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class StudentRegisterPageCommand implements ActionCommand {
	static Logger log = Logger.getLogger(StudentRegisterPageCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		log.info("StudentRegisterPageCommand used...");
		String page = "/jsp/register.jsp";		
		log.info("StudentRegisterPageCommand returned: " + page);
		return page;
	}

}
