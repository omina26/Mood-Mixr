package Login;

import interface_adapter.login.LoginController;
import org.junit.Test;
import use_case.login.LoginInputBoundary;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;

public class LoginControllerTest {

    @Test
    public void testExecuteUseCase() throws IOException, URISyntaxException {
        MockLoginUsecasInteractor interactor = new MockLoginUsecasInteractor();
        LoginController controller = new LoginController(interactor);
        controller.executeUseCase();
        assertTrue(interactor.executed);
    }
}

class MockLoginUsecasInteractor implements LoginInputBoundary {
    boolean executed = false;

    @Override
    public void execute() {
        executed = true;
    }
}

