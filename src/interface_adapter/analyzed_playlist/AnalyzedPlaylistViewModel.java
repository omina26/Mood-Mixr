package interface_adapter.analyzed_playlist;

import interface_adapter.ViewModel;
import interface_adapter.playlist_created.PlaylistCreatedState;
import view.AnalyzedPlaylistView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AnalyzedPlaylistViewModel extends ViewModel {

    public final String TITLE_LABEL = "Analyzed Playlist View";
    private AnalyzedPlaylistState state = new AnalyzedPlaylistState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AnalyzedPlaylistViewModel() {
        super("analyzed playlist");
    }
    public void setState(AnalyzedPlaylistState state){this.state = state;}

    public AnalyzedPlaylistState getState() {return state;}


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state",null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
