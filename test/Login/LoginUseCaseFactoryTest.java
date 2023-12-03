package Login;

import app.LoginUseCaseFactory;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import use_case.login.LoginDataAccessInterface;
import view.LoginView;

import javax.swing.text.View;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoginUseCaseFactoryTest {

    @Test
    public void testLoginView(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        LoginDataAccessInterface dataAccessInterface = new LoginDataAccessInterface() {
            @Override
            public void loginUser(User user) throws IOException {

            }

            @Override
            public User getCurrentUser() {
                return null;
            }
        };

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, dataAccessInterface);
        assertEquals(loginView.viewName, "log in");
    }
}
