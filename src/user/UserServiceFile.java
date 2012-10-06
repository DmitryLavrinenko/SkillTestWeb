package user;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class UserServiceFile implements UserService {

	private Properties users;

	public UserServiceFile(String fileName) {
		users = new Properties();
		URL url = ClassLoader.getSystemResource(fileName);
		try {
			users.load(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean checkUser(String userName, String password) {
		return users.containsKey(userName) && users.get(userName).equals(password);
	}
}
