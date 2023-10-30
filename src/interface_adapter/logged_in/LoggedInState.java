package interface_adapter.logged_in;

public class LoggedInState {
    private String name = "";

    public LoggedInState(LoggedInState copy) { name = copy.name;}

    public LoggedInState() {}

    public String getName() {return name;}
}
