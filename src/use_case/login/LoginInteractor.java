package use_case.login;

import entity.User;
import use_case.login.services.AuthRedirectServerHandler;
import use_case.login.services.LoginAPIHandler;
import use_case.login.services.LoginAPIInterface;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LoginInteractor implements LoginInputBoundary {

    final LoginDataAccessInterface userDataAcessObject;

    final LoginOutputBoundary loginPresenter;
    final LoginAPIInterface handler;

    public LoginInteractor(LoginDataAccessInterface userDataAcessObject, LoginOutputBoundary loginPresenter, LoginAPIInterface handler){
        this.loginPresenter = loginPresenter;
        this.userDataAcessObject = userDataAcessObject;
        this.handler = handler;
    }

    @Override
    public void execute()  {
        try {
            User user = this.handler.getLoginUserInfo();
            userDataAcessObject.loginUser(user);

            LoginOutputData loginOutputData = new LoginOutputData(user, false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }catch (Exception e){
            loginPresenter.prepareFailView(e.getMessage());
        }
    }

}
