package use_case.create_playlist;

//import use_case.create_mood.CreateMoodInputData;

/**
 * Represents the input data for the Create Playlist use case
 */
public class CreatePlaylistInputData {

    final private String selectedMoodName;

    /**
     * A constructor for the CreatePlaylistInputData
     * @param selectedMoodName The name for the selected mood
     */
    public CreatePlaylistInputData(String selectedMoodName) {
        this.selectedMoodName = selectedMoodName;
    }

    /**
     * Gets the selected mood name
     * @return mood name
     */
    public String getSelectedMoodName() {return this.selectedMoodName;}
}
