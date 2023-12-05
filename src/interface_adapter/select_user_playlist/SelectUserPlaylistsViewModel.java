package interface_adapter.select_user_playlist;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SelectUserPlaylistsViewModel extends ViewModel {

    String viewName;
    SelectUserPlaylistState state = new SelectUserPlaylistState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SelectUserPlaylistsViewModel() {
        super("Select User Playlists");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SelectUserPlaylistState getState() {
        return this.state;
    }

    public void setState(SelectUserPlaylistState selectUserPlaylistState) {
        this.state = selectUserPlaylistState;
    }
}
