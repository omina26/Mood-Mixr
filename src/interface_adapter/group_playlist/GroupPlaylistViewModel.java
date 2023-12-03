package interface_adapter.group_playlist;

import entity.GroupPlaylist;
import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GroupPlaylistViewModel extends ViewModel {

    public final String TITLE_LABEL = "Group Playlist View";

    public static final String  CHECK_BOX_LABEL = "Select songs to group";

    private GroupPlaylistState state = new GroupPlaylistState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GroupPlaylistViewModel(){super("Group Playlist");}

    public void setState(GroupPlaylistState state){this.state = state;}
    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
