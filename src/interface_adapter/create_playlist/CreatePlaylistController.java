package interface_adapter.create_playlist;

import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInputData;

public class CreatePlaylistController {

    final CreatePlaylistInputBoundary createPlaylistInteractor;

    public CreatePlaylistController(CreatePlaylistInputBoundary createPlaylistInteractor) {
        this.createPlaylistInteractor = createPlaylistInteractor;
    }

    public void execute(String selectedMoodName) {
        CreatePlaylistInputData data = new CreatePlaylistInputData(selectedMoodName);
        this.createPlaylistInteractor.execute(data);
    }
}
