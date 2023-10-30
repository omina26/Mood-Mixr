package use_case.login;

import interface_adapter.login.LoginController;

import java.io.IOException;
import java.net.URISyntaxException;

public interface LoginInputBoundary {
    public void execute() throws IOException, URISyntaxException;
}
