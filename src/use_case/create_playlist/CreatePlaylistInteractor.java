package use_case.create_playlist;

import use_case.create_mood.CreateMoodInputData;

public class CreatePlaylistInteractor implements CreatePlaylistInputBoundary{
    public CreatePlaylistInteractor() {}

    public void execute(CreatePlaylistInputData createPlaylistInputData){
        String selectedMood = createPlaylistInputData.getSelectedMoodName();
    }
}
