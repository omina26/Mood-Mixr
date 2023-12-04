package interface_adapter.analyzed_playlist;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AnalyzedPlaylistViewModel extends ViewModel {

    public final String TITLE_LABEL = "View Analyzed Playlist";
    private AnalyzedPlaylistState state = new AnalyzedPlaylistState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AnalyzedPlaylistViewModel() {
        super("view analyzed playlist");
    }
    public void setState(AnalyzedPlaylistState state){
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state",null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AnalyzedPlaylistState getState(){return state;}
}
