package use_case.login.services;

import entity.User;

import java.io.IOException;
import java.net.URISyntaxException;

public interface LoginAPIInterface {
    User getLoginUserInfo() throws IOException, URISyntaxException, InterruptedException;
}
