package servlet;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import servlet.LoginController;
import user.UserService;

public class LoginControllerTest {
	
	private LoginController loginController;
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private RequestDispatcher requestDispatcher;
	private UserService userService;

	@Before
	public void initMocks() {
		userService = mock(UserService.class);
		loginController = new LoginController(userService);
		req = mock(HttpServletRequest.class);
		resp = mock(HttpServletResponse.class);
		requestDispatcher = mock(RequestDispatcher.class);
	}

	@Test
	public void testLoginUserSuccessfully() throws ServletException, IOException {
		when(req.getParameter("username")).thenReturn("user");
		when(req.getParameter("password")).thenReturn("pass");
		when(userService.checkUser("user", "pass")).thenReturn(true);

		when(req.getRequestDispatcher("succesfulLogin.jsp")).thenReturn(requestDispatcher);

		validate();
	}
	
	@Test
	public void testUnsuccessfullLoginIfNoUser() throws ServletException, IOException {
		when(req.getParameter("username")).thenReturn("user" + "unknown");
		when(req.getParameter("password")).thenReturn("pass");
		when(userService.checkUser("user" + "unknown", "pass")).thenReturn(false);

		when(req.getRequestDispatcher("unSuccesfulLogin.jsp")).thenReturn(requestDispatcher);

		validate();
	}
	
	@Test
	public void testUnsuccessfullLoginIfWrongPassword() throws ServletException, IOException {
		when(req.getParameter("username")).thenReturn("user");
		when(req.getParameter("password")).thenReturn("pass" + "wrong");
		when(userService.checkUser("user", "pass" + "wrong")).thenReturn(false);

		when(req.getRequestDispatcher("unSuccesfulLogin.jsp")).thenReturn(requestDispatcher);

		validate();
	}

	private void validate() throws ServletException, IOException {
		loginController.doPost(req, resp);
		verify(requestDispatcher).forward(req, resp);
	}
}
