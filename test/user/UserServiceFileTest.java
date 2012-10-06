package user;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class UserServiceFileTest {

	public static final String TEST_USERS_FILE = "user/users_accounts.txt";
	private static final UserService users = new UserServiceFile(TEST_USERS_FILE);
	private static final Object[][] data = new Object[][]{
			{"user", "pass"},
			{"kuku", "ruku"},
			{"mama", "rama"}
	};

	private String userName;
	private String password;

	public UserServiceFileTest(final String userName, final String password) {
		this.userName = userName;
		this.password = password;
	}

//	@BeforeClass
//	public static void makeUsersFile() throws IOException {
//		Properties users = new Properties();
//		for (Object[] userData : data) {
//			users.put(userData[0], userData[1]);
//		}
//		users.store(new FileOutputStream(TEST_USERS_FILE), "test data");
//	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(data);
	}

	@Test
	public void checkValidUser() {
		assertTrue("User doesn't exist", users.checkUser(userName, password));
	}
	
	@Test
	public void checkInvalidUserName() {
		assertFalse("Non existing user is recognized", users.checkUser(userName + "unknown", password));
	}
	
	@Test
	public void checkInvalidPassword() {
		assertFalse("Non existing user is recognized", users.checkUser(userName, password + "wrong"));
	}
	
}
