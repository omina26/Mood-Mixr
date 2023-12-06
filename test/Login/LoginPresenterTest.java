package Login;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import use_case.login.LoginOutputData;

import static org.junit.Assert.assertEquals;

public class LoginPresenterTest {
    @Test  
    public void testPrepareLoginView() {
        String mock = "mock";

        User user = new User("mockName","1234", "mockID");
        LoginOutputData data = new LoginOutputData(user, false);

        LoginViewModel mockLoginMoodViewModel = new LoginViewModel();
        LoggedInViewModel mockLoggedInViewModel = new LoggedInViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        LoginPresenter presenter = new LoginPresenter(mockViewManagerModel, mockLoggedInViewModel, mockLoginMoodViewModel);

        presenter.prepareSuccessView(data);

        assertEquals(mockLoggedInViewModel.getState().getName(), "mockName");
        assertEquals(mockViewManagerModel.getActiveView(), mockLoggedInViewModel.getViewName());
    }
}
