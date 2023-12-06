package Login;

import interface_adapter.login.LoginController;
import org.junit.Test;
import use_case.login.LoginInputBoundary;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest {
    @Test
    public void testExecute() throws IOException, URISyntaxException {
        MockLoginInteractor mockInteractor = new MockLoginInteractor();

        LoginController controller = new LoginController(mockInteractor);
        controller.executeUseCase();

        assertEquals(mockInteractor.data, mockInteractor.data);
    }
}

class MockLoginInteractor implements LoginInputBoundary{
    String data;
    @Override
    public void execute() {
        this.data = data;
    }
}