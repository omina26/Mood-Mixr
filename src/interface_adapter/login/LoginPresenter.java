package interface_adapter.login;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;

    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    public LoginPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel){
        this.loginViewModel = loginViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(LoginOutputData response){
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setName(response.getName());
        loggedInState.setUser(response.getUser());

        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        // removed loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
