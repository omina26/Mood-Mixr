package interface_adapter.login;

import interface_adapter.LogInViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private LogInViewManagerModel viewManagerModel;
    public LoginPresenter(LogInViewManagerModel viewManagerModel, LoginViewModel loginViewModel){
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(LoginOutputData response){

    }


}
