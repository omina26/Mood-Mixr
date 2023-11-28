package app;

import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.login.*;
import use_case.login.services.LoginAPIHandler;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory(){}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel, LoginDataAccessInterface userDataAccessObject
    ){
        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loggedInViewModel,
                    loginViewModel, userDataAccessObject);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not Access User Data");
        }
        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            LoginViewModel loginViewModel,
            LoginDataAccessInterface userDataAccessObject) throws IOException{
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        LoginAPIHandler handler = new LoginAPIHandler();
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessObject, loginOutputBoundary, handler);
        return new LoginController(loginInteractor);
    }
}
