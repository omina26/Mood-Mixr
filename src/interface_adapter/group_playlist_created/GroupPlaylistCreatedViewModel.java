package interface_adapter.group_playlist_created;

import entity.User;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GroupPlaylistCreatedViewModel extends ViewModel {

    public final String TITLE_LABEL = "Group Playlist Created";

    private GroupPlaylistCreatedState state = new GroupPlaylistCreatedState();
    private User user;

    public GroupPlaylistCreatedViewModel(){super("Group Playlist Created");}

    public void setState(GroupPlaylistCreatedState state){this.state = state;}

    public GroupPlaylistCreatedState getState(){return this.state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public String getViewName() {return TITLE_LABEL;}
}
