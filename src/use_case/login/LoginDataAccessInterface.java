package use_case.login;

import entity.User;

import java.io.IOException;
import java.net.URISyntaxException;

public interface LoginDataAccessInterface {

    User get(String username);

    void logUserIn() throws IOException, URISyntaxException;
}
