package use_case.login;

import entity.User;

import java.io.IOException;
import java.net.URISyntaxException;

public interface LoginDataAccessInterface {

    public String getAccessToken() throws IOException, URISyntaxException, InterruptedException;

    public void loginUser();
}
