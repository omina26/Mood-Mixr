package interface_adapter.analyze_playlist;

import use_case.analyze_playlist.AnalyzePlaylistInputBoundary;
import use_case.analyze_playlist.AnalyzePlaylistInputData;

/**
 * Controller for handling analyze playlist requests.
 */

public class AnalyzePlaylistController {

    /**
     * Constructs an AnalyzePlaylistController with a given AnalyzePlaylistInputBoundary.
     *
     * @param analyzePlaylistInteractor The interactor that provides business logic for
     *                                  analyzing playlists.
     */
    final AnalyzePlaylistInputBoundary analyzePlaylistInteractor;

    public AnalyzePlaylistController(AnalyzePlaylistInputBoundary analyzePlaylistInteractor) {
        this.analyzePlaylistInteractor = analyzePlaylistInteractor;

    }

    /**
     * Initiates the process of analyzing a playlist.
     * This method creates an AnalyzePlaylistInputData object and calls the execute method
     * on the interactor to begin the analysis of the playlist.
     *
     * @param playlistID The unique identifier of the playlist to be analyzed.
     */

    public void execute(String playlistID) {
        AnalyzePlaylistInputData data = new AnalyzePlaylistInputData(playlistID);
        analyzePlaylistInteractor.execute(data);


    }
}