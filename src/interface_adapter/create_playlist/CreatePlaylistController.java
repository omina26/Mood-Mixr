package interface_adapter.create_playlist;

import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInputData;

/**
 * A controller class for the Create Playlist use case
 */
public class CreatePlaylistController {

    final CreatePlaylistInputBoundary createPlaylistInteractor;

    /**
     * Constructor for the Create Playlist Controller object
     * @param createPlaylistInteractor The interactor to execute the use case
     */
    public CreatePlaylistController(CreatePlaylistInputBoundary createPlaylistInteractor) {
        this.createPlaylistInteractor = createPlaylistInteractor;
    }

    /**
     * Triggers the interactor to create a playlist with the selected mood and proceed with the use case
     * @param selectedMoodName The name of the selected mood
     */
    public void execute(String selectedMoodName) {
        CreatePlaylistInputData data = new CreatePlaylistInputData(selectedMoodName);
        this.createPlaylistInteractor.execute(data);
    }
}
