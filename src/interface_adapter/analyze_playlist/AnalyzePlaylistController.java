package interface_adapter.analyze_playlist;

import use_case.analyze_playlist.AnalyzePlaylistInputBoundary;
import use_case.analyze_playlist.AnalyzePlaylistInputData;

public class AnalyzePlaylistController {
    final AnalyzePlaylistInputBoundary analyzePlaylistInteractor;
    public AnalyzePlaylistController(AnalyzePlaylistInputBoundary analyzePlaylistInteractor) {
        this.analyzePlaylistInteractor = analyzePlaylistInteractor;
    }

    public void execute(String playlistID) {
        AnalyzePlaylistInputData data = new AnalyzePlaylistInputData(playlistID);

        analyzePlaylistInteractor.execute(data);
    }

}
