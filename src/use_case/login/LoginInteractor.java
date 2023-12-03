package use_case.login;

import entity.User;
import use_case.services.LoginAPIInterface;

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

            LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
            loginPresenter.prepareSuccessView(loginOutputData);
        }catch (Exception e){
            loginPresenter.prepareFailView(e.getMessage());
        }
    }

}
