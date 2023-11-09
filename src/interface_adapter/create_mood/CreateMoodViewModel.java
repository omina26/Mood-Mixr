package interface_adapter.create_mood;

import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateMoodViewModel extends ViewModel {

    public final String TITLE_LABEL = "Create Mood View";

    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String SET_ACOUSTICNESS_LABEL = "Set Acousticness";
    public static final String SET_DANCEABILITY_LABEL = "Set Danceability";
    public static final String SET_ENERGY_LABEL = "Set Energy";
    public static final String SET_INSTRUMENTALNESS = "Set Instrumentalness";
    public static final String SET_LIVENESS = "Set Liveness";
    public static final String SET_SPEECHINESS = "Set Speechiness";
    public static final String SET_VALENCE = "Set Valence";


    private CreateMoodState state = new CreateMoodState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CreateMoodViewModel(){super("Create Mood");}

    public void setState(CreateMoodState state){this.state = state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateMoodState getState(){return state;}
}