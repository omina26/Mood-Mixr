package interface_adapter.login;

import interface_adapter.LogInViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends LogInViewModel {

    public final String TITLE_LABEL = "Log In View";

    public static final String LOGIN_BUTTON_LABEL = "Log in";

    private LoginState state = new LoginState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LoginViewModel(){super("Log in");}

    public void setState(LoginState state){this.state = state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoginState getState(){return state;}
}
