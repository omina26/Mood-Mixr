package interface_adapter.analyze_playlist;

import use_case.analyze_playlist.AnalyzePlaylistInputBoundary;
import use_case.analyze_playlist.AnalyzePlaylistInputData;

/**
 * Controller for handling analyze playlist requests.
 */

public class AnalyzePlaylistController {
    final AnalyzePlaylistInputBoundary analyzePlaylistInteractor;

    /**
     * Constructor for AnalyzePlaylistController.
     *
     * @param analyzePlaylistInteractor Interactor for analyzing playlists.
     */
    public AnalyzePlaylistController(AnalyzePlaylistInputBoundary analyzePlaylistInteractor) {
        this.analyzePlaylistInteractor = analyzePlaylistInteractor;
    }

    /**
     * Executes the operation to analyze a playlist.
     *
     * @param playlistID The ID of the playlist to be analyzed.
     */

    public void execute(String playlistID) {
        AnalyzePlaylistInputData data = new AnalyzePlaylistInputData(playlistID);

        analyzePlaylistInteractor.execute(data);
    }
}
