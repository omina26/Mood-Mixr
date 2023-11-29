package interface_adapter.analyze_playlist;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AnalyzePlaylistViewModel extends ViewModel {
    public final String TITLE_LABEL = "Analyze Playlist View";

    public static final String ANALYZE_PLAYLIST_BUTTON_LABEL = "Analyze Playlist";
    public static final String BACK_BUTTON_LABEL = "Back";

    private AnalyzePlaylistState state = new AnalyzePlaylistState(ANALYZE_PLAYLIST_BUTTON_LABEL);
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AnalyzePlaylistViewModel() {super("Analyze Playlist");}


    public void setState (AnalyzePlaylistState state){this.state = state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AnalyzePlaylistState getState(){
        return state;
    }
}
