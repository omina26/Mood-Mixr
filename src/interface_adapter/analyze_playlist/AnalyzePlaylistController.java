package interface_adapter.analyze_playlist;

import use_case.analyze_playlist.AnalyzePlaylistInputBoundary;
import use_case.analyze_playlist.AnalyzePlaylistInputData;

public class AnalyzePlaylistController {
    final AnalyzePlaylistInputBoundary analyzePlaylistInteractor;
    public AnalyzePlaylistController(AnalyzePlaylistInputBoundary analyzePlaylistInteractor) {
        this.analyzePlaylistInteractor = analyzePlaylistInteractor;
    }

    public void execute(String playlist) {
        AnalyzePlaylistInputData analyzePlaylistInputData = new AnalyzePlaylistInputData(playlist);

        analyzePlaylistInteractor.execute(analyzePlaylistInputData);
    }

}
