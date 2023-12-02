import entity.User;
import org.junit.Test;
import use_case.login.LoginDataAccessInterface;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.login.services.LoginAPIInterface;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class LoginInteractorTest {

    @Test
    public void testExecuteSuccess(){
        MockUserDataAccessObject dataAccessObject = new MockUserDataAccessObject();
        MockLoginPresenter presenter = new MockLoginPresenter();
        MockApiHandlerSuccess handler = new MockApiHandlerSuccess();

        LoginInteractor interactor = new LoginInteractor(dataAccessObject, presenter, handler);

        interactor.execute();
        assertEquals(presenter.outputData, "mock");
    }

    @Test
    public void testExecuteFail(){
        MockUserDataAccessObject dataAccessObject = new MockUserDataAccessObject();
        MockLoginPresenter presenter = new MockLoginPresenter();
        MockApiHandlerFail handler = new MockApiHandlerFail();

        LoginInteractor interactor = new LoginInteractor(dataAccessObject, presenter, handler);

        interactor.execute();
        assertEquals(presenter.outputData, "fail");
    }
}
class MockUserDataAccessObject implements LoginDataAccessInterface{

    @Override
    public void loginUser(User user) {

    }

    @Override
    public User getCurrentUser() {
        return null;
    }
}

class MockApiHandlerSuccess implements LoginAPIInterface{

    @Override
    public User getLoginUserInfo() throws IOException, URISyntaxException, InterruptedException {
        return new User("mock", "63275");
    }
}

class MockApiHandlerFail implements LoginAPIInterface{

    @Override
    public User getLoginUserInfo() throws IOException, URISyntaxException, InterruptedException {
        throw new IOException();
    }
}

class MockLoginPresenter implements LoginOutputBoundary{

    String outputData = "";

    @Override
    public void prepareSuccessView(LoginOutputData user) {
        this.outputData = user.getName();
    }

    @Override
    public void prepareFailView(String error) {
        this.outputData = "fail";
    }
}