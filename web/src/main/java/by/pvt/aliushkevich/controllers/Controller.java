package by.pvt.aliushkevich.controllers;

import by.pvt.aliushkevich.comands.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {	
	static Logger logger = Logger.getLogger(Controller.class.getName());
	//static Logger logger = Logger.getRootLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		logger.debug("controller used...");		
		String page = "/jsp/login.jsp";
		// определение команды, пришедшей из JSP
		String action = request.getParameter("command");
		logger.debug("controller recieved parameter: " +action);		
		/*
		 * вызов реализованного метода execute() и передача параметров
		 * классу-обработчику конкретной команды
		 */
		ActionCommand command = null;
		switch (action) {
		case "login":
			command = new LoginCommand();
			break;
		case "logout":
			command = new LogoutCommand();
			break;
		case "chooseLearningCourse":
			command = new AddLearningCourseCommand();
			break;
		case "addMarkFeedback":
			command = new AddMarkFeedbackCommand();
			break;
		case "studentRegisterPage":
			command = new StudentRegisterPageCommand();
			break;
		case "studentRegister":
			command = new StudentRegisterCommand();			
			break;			
		default:
			command = new EmptyCommand();
			break;
		}

		page = command.execute(request);
		if (page != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			// вызов страницы ответа на запрос
			dispatcher.forward(request, response);
			logger.debug("controller forward client to: " +page);
		} else {
			// установка страницы c cообщением об ошибке
			page = "/jsp/login.jsp";
			request.getSession().setAttribute("nullPage", "Page not found. Business logic error.");
			response.sendRedirect(request.getContextPath() + page);
			logger.error("controller could not found the page");
		}
	}
}
