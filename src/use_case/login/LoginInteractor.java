package use_case.login;

import java.io.IOException;
import java.net.URISyntaxException;

public class LoginInteractor implements LoginInputBoundary {

    final LoginDataAccessInterface userDataAcessObject;

    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface userDataAcessObject, LoginOutputBoundary loginPresenter){
        this.loginPresenter = loginPresenter;
        this.userDataAcessObject = userDataAcessObject;

    }
    //@Override
    public void execute() throws IOException, URISyntaxException {

    }
}
