package use_case.analyze_playlist;

import java.io.IOException;

public class AnalyzePlaylistInteractor implements AnalyzePlaylistInputBoundary {

    final AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject;
    final AnalyzePlaylistOutputBoundary analyzePlaylistPresenter;
    public AnalyzePlaylistInteractor(AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject,
                                     AnalyzePlaylistOutputBoundary analyzePlaylistPresenter) {
        this.analyzePlaylistDataAccessObject = analyzePlaylistDataAccessObject;
        this.analyzePlaylistPresenter = analyzePlaylistPresenter;
    }

    @Override
    public void execute(AnalyzePlaylistInputData analyzePlaylistInputData) {
        String playlistID = analyzePlaylistInputData.getPlaylistID();

        analyzePlaylistDataAccessObject.savePlaylistID(playlistID);
    }
}
