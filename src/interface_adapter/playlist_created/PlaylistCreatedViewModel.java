package interface_adapter.playlist_created;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlaylistCreatedViewModel extends ViewModel{

    public final String TITLE_LABEL = "Playlist Created View";

    private PlaylistCreatedState state = new PlaylistCreatedState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PlaylistCreatedViewModel(){super("playlist created");}

    public void setState(PlaylistCreatedState state) {this.state = state;}

    public PlaylistCreatedState getState() {return state;}

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
