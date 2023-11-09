package use_case.login;

import interface_adapter.LogInViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;

public class LoginPresenter implements LoginOutputBoundary{
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private LogInViewManagerModel viewManagerModel;

    public LoginPresenter(LogInViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }
}
