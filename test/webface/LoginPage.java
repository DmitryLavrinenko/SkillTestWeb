package webface;

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
		server.addHandler(new WebAppContext("WebContent", "/SkillTestWeb"));
		server.stop();
		server.start();
		
		int port = server.getConnectors()[0].getLocalPort();
		tester.getTestContext().setBaseUrl("http://localhost:" + port + "/SkillTestWeb");
	}
	
	@Test
	public void testLogin() {
		tester.beginAt("/");
		tester.assertFormPresent("form_login");
		tester.assertFormElementPresent("username");
		tester.assertFormElementPresent("password");
		tester.assertButtonPresentWithText("Login");
	}
	
	@Test
	public void testLoginSuccesfully() {
		tester.beginAt("/");
		
		tester.setTextField("username", "user");
		tester.setTextField("password", "pass");
		tester.clickButtonWithText("Login");
		
		tester.assertTextPresent("You have successfully logged in");
		tester.assertTextPresent("User name: user"); 
		tester.assertTextPresent("Password: pass"); 
	}
	
	@Test
	public void testLoginIfNoSuchUser() {
		tester.beginAt("/");
		
		tester.setTextField("username", "user" + "unknown");
		tester.setTextField("password", "pass");
		tester.clickButtonWithText("Login");
		
		tester.assertTextPresent("You have entered an incorrect username or password");
	}
	
	@Test
	public void testLoginIfWrongPassword() {
		tester.beginAt("/");
		
		tester.setTextField("username", "user");
		tester.setTextField("password", "pass" + "wrong");
		tester.clickButtonWithText("Login");
		
		tester.assertTextPresent("You have entered an incorrect username or password");
	}
	
	@Test
	public void testUnsuccessfullLoginPageHasALinkToLoginPage() {
		testLoginIfNoSuchUser();
		
		tester.assertLinkPresentWithExactText("go to login");
		tester.clickLinkWithExactText("go to login");
	}

	@Test
	public void testLoginAnotherUserSuccesfully() {
		tester.beginAt("/");

		tester.setTextField("username", "kuku");
		tester.setTextField("password", "ruku");
		tester.clickButtonWithText("Login");

		tester.assertTextPresent("You have successfully logged in");
		tester.assertTextPresent("User name: kuku");
		tester.assertTextPresent("Password: ruku");
	}

	@AfterClass
	public static void end() throws Exception {
		server.stop();
	}
}
