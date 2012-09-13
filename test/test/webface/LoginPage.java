package test.webface;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.*;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class LoginPage {
	
	private static WebTester tester;
	private static Server server;
	
	@BeforeClass
	public static void init() throws Exception {
		tester = new WebTester();
		server = new Server(0);
		server.addHandler(new WebAppContext("WebContent/WEB-INF", "/controller"));
		server.stop();
		server.start();
		
		int port = server.getConnectors()[0].getLocalPort();
		System.out.println(port);
		server.join();
		tester.getTestContext().setBaseUrl("http://localhost:" + port + "/controller");
	}
	
	@Test
	public void test() {
		tester.beginAt("/login.jsp");
		tester.assertFormPresent("form_login");
		tester.assertFormElementPresent("username");
		tester.assertFormElementPresent("password");
		tester.assertSubmitButtonPresent("Login");
	}

	@AfterClass
	public static void end() throws Exception {
		server.stop();
	}
}
