package interface_adapter.group_playlist;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GroupPlaylistViewModel extends ViewModel {

    public String viewName;
    public final String TITLE_LABEL = "Group Playlist View";
    public static final String  CHECK_BOX_LABEL = "Select Playlists to group";
    private GroupPlaylistState state = new GroupPlaylistState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GroupPlaylistViewModel(){super("Group Playlist");}

    public void setState(GroupPlaylistState state){this.state = state;}
    public GroupPlaylistState getState() {
        return this.state;
    }
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
