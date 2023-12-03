package interface_adapter.logged_in;

import entity.User;

public class LoggedInState {
    private String name = "";
    private User user;

    public LoggedInState(LoggedInState copy) {
        name = copy.name;
        user = null;
    }

    public LoggedInState() {}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
    public void setUser(User user){this.user = user;}
}
