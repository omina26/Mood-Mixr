package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.login.LoginInteractor;

import java.io.IOException;
import java.net.URISyntaxException;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginInteractor){
        this.loginUseCaseInteractor = loginInteractor;
    }

    public void executeUseCase() throws IOException, URISyntaxException {
       loginUseCaseInteractor.execute();
    }
}
