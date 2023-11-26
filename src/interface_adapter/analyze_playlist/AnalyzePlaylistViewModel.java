package interface_adapter.analyze_playlist;

import interface_adapter.ViewModel;
import use_case.analyze_playlist.AnalyzePlaylistOutputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AnalyzePlaylistViewModel extends ViewModel {
    public final String TITLE_LABLE = "Analyze Playlist View";

    public static final String SELECT_PLAYLIST_LABLE = "Select Playlist";

    private AnalyzePlaylistState state = new AnalyzePlaylistState(SELECT_PLAYLIST_LABLE);
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AnalyzePlaylistViewModel(String viewName) {
        super("Analyze Playlist");
    }

    public void setState (AnalyzePlaylistState state){
        this.state = state;}

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
