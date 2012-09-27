package user;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserServiceTest {

	private static final UserServiceMock users = new UserServiceMock();

	@Test
	public void checkValidUser() {
		assertTrue("User doesn't exist", users.checkUser("user", "pass"));
	}
	
	@Test
	public void checkInvalidUserName() {
		assertFalse("Non existing user is recognized", users.checkUser("user" + "unknown", "pass"));
	}
	
	@Test
	public void checkInvalidPassword() {
		assertFalse("Non existing user is recognized", users.checkUser("user", "pass" + "wrong"));
	}
	
}
