package use_case.login;

import entity.User;

public class LoginOutputData {

    private final User user;

    private boolean useCaseFailed;

    public LoginOutputData(User user, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }
    public String getName() {
        return user.getName();
    }

    public User getUser(){return this.user;}

}
