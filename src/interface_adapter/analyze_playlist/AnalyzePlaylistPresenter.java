package interface_adapter.analyze_playlist;

import interface_adapter.ViewManagerModel;
import use_case.analyze_playlist.AnalyzePlaylistOutputBoundary;
import use_case.analyze_playlist.AnalyzePlaylistOutputData;

public class AnalyzePlaylistPresenter implements AnalyzePlaylistOutputBoundary {
    private final AnalyzePlaylistViewModel analyzePlaylistViewModel;
    private final ViewManagerModel viewManagerModel;

    public AnalyzePlaylistPresenter(AnalyzePlaylistViewModel analyzePlaylistViewModel,
                                    ViewManagerModel viewManagerModel) {
        this.analyzePlaylistViewModel = analyzePlaylistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void analyzePlaylistView(AnalyzePlaylistOutputData data) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
