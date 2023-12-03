package use_case.create_playlist;

//import use_case.create_mood.CreateMoodInputData;

public class CreatePlaylistInputData {

    final private String selectedMoodName;

    public CreatePlaylistInputData(String selectedMoodName) {
        this.selectedMoodName = selectedMoodName;
    }

    String getSelectedMoodName() {return this.selectedMoodName;}
}
