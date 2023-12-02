package use_case.login;

import entity.User;

import java.io.IOException;
import java.net.URISyntaxException;

public interface LoginDataAccessInterface {

    public void loginUser(User user);

    User getCurrentUser();
}
