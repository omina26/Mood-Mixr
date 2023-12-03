package interface_adapter.logged_in;

import entity.User;
import interface_adapter.ViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {

    public final String TITLE_LABEL = "Logged In View";

    private LoggedInState state = new LoggedInState();

    public final String LOGOUT_BUTTON_LABEL = "Log out";
    public final String CREATE_MOOD_BUTTON_LABEL = "Create Custom Mood";
    public final String GET_PLAYLIST_BUTTON_LABEL = "Get Custom Playlist";
    public final String ANALYZE_PLAYLIST_BUTTON_LABEL = "Analyze Playlist";
    public final String GROUP_PLAYLIST_BUTTON_LABEL = "Generate Group Playlist";

    private User loggedInUser;

    public LoggedInViewModel(){super("logged in");}

    public void setState(LoggedInState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoggedInState getState(){return state;}

    public User getLoggedInUser(){return loggedInUser;}

}
