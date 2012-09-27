package user;

public class UserServiceMock implements UserService {

	@Override
	public boolean checkUser(String userName, String password) {
		return userName.equals("user") && password.equals("pass");
	}

}
