package test.servlet;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import servlet.LoginController;

public class LoginControllerTest {

	@Test
	public void testLogin() throws ServletException, IOException {
		LoginController loginController = new LoginController();
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
		
		when(req.getParameter("username")).thenReturn("user");
		when(req.getParameter("password")).thenReturn("pass");
		when(req.getRequestDispatcher("succesfulLogin.jsp")).thenReturn(requestDispatcher);
		
		loginController.doPost(req, resp);
		verify(requestDispatcher).forward(req, resp);
	}
	
	@Test
	public void testUnsuccessfullLoginIfNoUser() throws ServletException, IOException {
		LoginController loginController = new LoginController();
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
		
		when(req.getParameter("username")).thenReturn("user" + "unknown");
		when(req.getParameter("password")).thenReturn("pass");
		when(req.getRequestDispatcher("unSuccesfulLogin.jsp")).thenReturn(requestDispatcher);

		loginController.doPost(req, resp);
		verify(requestDispatcher).forward(req, resp);
	}
	
	@Test
	public void testUnsuccessfullLoginIfWrongPassword() throws ServletException, IOException {
		LoginController loginController = new LoginController();
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
		
		when(req.getParameter("username")).thenReturn("user");
		when(req.getParameter("password")).thenReturn("pass" + "wrong");
		when(req.getRequestDispatcher("unSuccesfulLogin.jsp")).thenReturn(requestDispatcher);

		loginController.doPost(req, resp);
		verify(requestDispatcher).forward(req, resp);
	}
}
