package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.analyze_playlist.AnalyzePlaylistController;
import interface_adapter.analyze_playlist.AnalyzePlaylistPresenter;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import use_case.analyze_playlist.*;
import view.AnalyzePlaylistView;
import view.AnalyzedPlaylistView;
import view.CreatePlaylistView;

public class AnalyzePlaylistUseCaseFactory {
    private AnalyzePlaylistUseCaseFactory() {}

    public static AnalyzePlaylistView create (ViewManagerModel viewManagerModel,
                                              AnalyzePlaylistViewModel analyzePlaylistViewModel,
                                              AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject){
        AnalyzePlaylistController analyzePlaylistController = analyzePlaylistUseCase(viewManagerModel,
                analyzePlaylistViewModel, analyzePlaylistDataAccessObject);
        return new AnalyzePlaylistView(analyzePlaylistViewModel, analyzePlaylistController);
    }

    private static AnalyzePlaylistController analyzePlaylistUseCase(ViewManagerModel viewManagerModel,
                                                                    AnalyzePlaylistViewModel analyzePlaylistViewModel,
                                                                    AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject) {
        AnalyzePlaylistOutputBoundary analyzePlaylistOutputBoundary = new AnalyzePlaylistPresenter(analyzePlaylistViewModel,
                viewManagerModel);

        AnalyzePlaylistInputBoundary analyzePlaylistInputBoundary = new AnalyzePlaylistInteractor(analyzePlaylistDataAccessObject,
                analyzePlaylistOutputBoundary);

        return new AnalyzePlaylistController(analyzePlaylistInputBoundary);
    }
}
