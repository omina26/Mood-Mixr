package interface_adapter.analyze_playlist;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * ViewModel for the Analyze Playlist feature.
 */
public class AnalyzePlaylistViewModel extends ViewModel {
    public final String TITLE_LABEL = "Analyze Playlist View";

    public static final String ANALYZE_PLAYLIST_BUTTON_LABEL = "Analyze Playlist";
    public static final String BACK_BUTTON_LABEL = "Back";

    private AnalyzePlaylistState state = new AnalyzePlaylistState(ANALYZE_PLAYLIST_BUTTON_LABEL);
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructor for AnalyzePlaylistViewModel.
     */
    public AnalyzePlaylistViewModel() {super("Analyze Playlist");}

    /**
     * Sets the state of the Analyze Playlist.
     *
     * @param state The new state.
     */
    public void setState (AnalyzePlaylistState state){this.state = state;}

    /**
     * Notifies listeners of a property change.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener.
     *
     * @param listener The listener to add.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the Analyze Playlist.
     *
     * @return The current state.
     */
    public AnalyzePlaylistState getState(){
        return state;
    }
    /**
     * Gets the property change support being used
     * @return the property change support being used
     */
    public PropertyChangeSupport getSupport(){
        return this.support;
    }
}
