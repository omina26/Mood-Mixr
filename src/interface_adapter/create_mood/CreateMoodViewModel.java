package interface_adapter.create_mood;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateMoodViewModel extends ViewModel {

    public final String TITLE_LABEL = "Create Mood View";

    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String SET_ACOUSTICNESS_LABEL = "Set Acousticness";
    public static final String ACOUSTICNESS_INFO = "A measure of whether the track is acoustic";
    public static final String SET_DANCEABILITY_LABEL = "Set Danceability";
    public static final String DANCEABILITY_INFO = "A measure of how suitable a track is for dancing";
    public static final String SET_ENERGY_LABEL = "Set Energy";
    public static final String ENERGY_INFO = "Represents a measure of intensity and activity";
    public static final String SET_INSTRUMENTALNESS = "Set Instrumentalness";
    public static final String INSTRUMENTALNESS_INFO = "A measure of whether a track contains vocals";
    public static final String SET_LIVENESS = "Set Liveness";

    public static final String LIVENESS_LABEL = "Detects the presence of an audience in the recording and represents " +
            "the probability of the track being live";
    public static final String SET_SPEECHINESS = "Set Speechiness";
    public static final String SPEECHINESS_INFO = "Represents the presence of spoken words in a track ad exclusively " +
            "speech like recoding";
    public static final String SET_VALENCE = "Set Valence";
    public static final String VALENCE_INFO = "A measure of the musical positiveness conveyed by a track";
    public static final int DEFAULT_SLIDER_VALUE = 50;


    private CreateMoodState state = new CreateMoodState(DEFAULT_SLIDER_VALUE);

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