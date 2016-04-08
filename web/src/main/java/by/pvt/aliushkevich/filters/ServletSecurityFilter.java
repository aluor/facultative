package by.pvt.aliushkevich.filters;

import by.pvt.aliushkevich.enums.ClientType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/controller" }, servletNames = { "Controller" })
public class ServletSecurityFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		ClientType type = (ClientType) session.getAttribute("userType");
		String login = req.getParameter("login");
		if (type == null) {
			type = ClientType.GUEST;
			session.setAttribute("userType", type);
			if (session.isNew()) {
				RequestDispatcher dispatcher = ((HttpServletRequest) request).getSession().getServletContext().getRequestDispatcher("/jsp/login.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}