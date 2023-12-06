package interface_adapter.create_playlist;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The view model class for the Create Playlist View
 */
public class CreatePlaylistViewModel extends ViewModel{

    public final String TITLE_LABEL = "Create Playlist View";

    public static final String GET_BUTTON_LABEL = "Get";
    public static final String MAIN_MENU_BUTTON_LABEL = "Main Menu";
    public static final String SELECT_MOOD = "[Select a mood]";

    private CreatePlaylistState state = new CreatePlaylistState(SELECT_MOOD);

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructor for the Create Playlist View Model object
     */
    public CreatePlaylistViewModel() {super("Create Playlist");}

    /**
     * Sets the state for the current view
     * @param state The state to set
     */
    public void setState(CreatePlaylistState state) {
        this.state = state;
        System.out.println(state.getMoodsList());
    }

    /**
     * triggers a change for the state
     */
    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the view
     * @param listener The listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the view
     * @return the current state
     */
    public CreatePlaylistState getState(){return state;}

    /**
     * Gets the property change support being used
     * @return the property change support being used
     */
    public PropertyChangeSupport getSupport(){
        return this.support;
    }
}
