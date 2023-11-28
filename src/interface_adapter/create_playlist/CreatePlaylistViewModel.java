package interface_adapter.create_playlist;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreatePlaylistViewModel {

    public final String TITLE_LABEL = "Create Playlist View";

    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String SELECT_MOOD = "[Select a mood]";

    private CreatePlaylistState state = new CreatePlaylistState(SELECT_MOOD);

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    //public CreatePlaylistViewModel(){super("Create Playlist");}

    public void setState(CreatePlaylistState state) {this.state = state;}

    public void firePropertyChanged(){support.firePropertyChange("state", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreatePlaylistState getState(){return state;}
}
