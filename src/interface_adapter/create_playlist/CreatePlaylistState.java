package interface_adapter.create_playlist;

import java.util.HashSet;
import java.util.Set;


public class CreatePlaylistState {
    private Set<String> moodsList = new HashSet<String>();
    private String selectedMood;
    private String saveError = null;

    /**
     * A constructor for the CreatePlaylistState object which sets selectedMood to the input
     * @param moodName The selected mood
     */
    public CreatePlaylistState(String moodName) {
        this.selectedMood = moodName;
    }

    /**
     * Gets the name of the Mood in the state of being created
     * @return the name of the Mood in the state of being created
     */
    public String getSelectedMood() {return selectedMood;}

    /**
     * Sets the name for the Mood in the state of being created
     * @param selectedMood the name for the Mood in the state of being created
     */
    public void setSelectedMood(String selectedMood) {
        this.selectedMood = selectedMood;
    }

    /**
     * Sets the mood names in the state of being created
     * @return mood names in the state of being created
     */
    public void setMoodsList(Set<String> moodsList) {this.moodsList = moodsList;}

    /**
     * Gets the mood names in the state of being created
     * @return mood names in the state of being created
     */
    public Set<String> getMoodsList() {
        return this.moodsList;}
    public String getSaveError () {return this.saveError;}

    /**
     * Gets the save error which happens due to a failure of the use case
     * @return the name of the Mood in the state of being created
     */
    public void setSaveError(String saveError) {
        this.saveError = saveError;
    }
}
