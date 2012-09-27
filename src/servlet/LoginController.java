package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getParameter("username").equals("user") && req.getParameter("password").equals("pass")) {
			req.getRequestDispatcher("succesfulLogin.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("unSuccesfulLogin.jsp").forward(req, resp);
		}
	}
	
}
