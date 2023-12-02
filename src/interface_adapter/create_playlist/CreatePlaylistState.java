package interface_adapter.create_playlist;

import java.util.HashSet;
import java.util.Set;

public class CreatePlaylistState {
    private Set<String> moodsList = new HashSet<String>();
    private String selectedMood;
    private String saveError = null;

    public CreatePlaylistState(String defaultMessage) {
        this.selectedMood = defaultMessage;
    }

    public CreatePlaylistState(CreatePlaylistState copy) {this.selectedMood = copy.selectedMood;}

    public String getSelectedMood() {return selectedMood;}

    public void setSelectedMood(String selectedMood) {
        this.selectedMood = selectedMood;
    }

    public void setMoodsList(Set<String> moodsList) {this.moodsList = moodsList;}
    public String[] getMoodsList() {
        return moodsList.toArray(new String[0]);}
    public String getSaveError () {return this.saveError;}

    public void setSaveError(String saveError) {
        this.saveError = saveError;
    }
}
