package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserService;
import user.UserServiceMock;

public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserService users;

	public LoginController(UserService userService) {
		this.users = userService;
	}

	public LoginController() {
		this.users = new UserServiceMock();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (users.checkUser(req.getParameter("username"), req.getParameter("password"))) {
			req.getRequestDispatcher("succesfulLogin.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("unSuccesfulLogin.jsp").forward(req, resp);
		}
	}
	
}
