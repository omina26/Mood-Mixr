package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.analyze_playlist.AnalyzePlaylistController;
import interface_adapter.analyze_playlist.AnalyzePlaylistPresenter;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import use_case.analyze_playlist.*;
import use_case.login.LoginDataAccessInterface;
import use_case.login.services.TracksAudioFeaturesAPIHandler;
import use_case.login.services.UserPlaylistItemsAPIHandler;
import view.AnalyzePlaylistView;

public class AnalyzePlaylistUseCaseFactory {
    private AnalyzePlaylistUseCaseFactory() {}

    public static AnalyzePlaylistView create (ViewManagerModel viewManagerModel,
                                              AnalyzePlaylistViewModel analyzePlaylistViewModel,
                                              AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject,
                                              LoginDataAccessInterface userDataAccessObject){
        AnalyzePlaylistController analyzePlaylistController = analyzePlaylistUseCase(viewManagerModel,
                analyzePlaylistViewModel, analyzePlaylistDataAccessObject, userDataAccessObject);
        return new AnalyzePlaylistView(analyzePlaylistViewModel, analyzePlaylistController);
    }

    private static AnalyzePlaylistController analyzePlaylistUseCase(ViewManagerModel viewManagerModel,
                                                                    AnalyzePlaylistViewModel analyzePlaylistViewModel,
                                                                    AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject,
                                                                    LoginDataAccessInterface userDataAccessObject) {

        AnalyzePlaylistOutputBoundary analyzePlaylistOutputBoundary =
                new AnalyzePlaylistPresenter(analyzePlaylistViewModel, viewManagerModel);

        UserPlaylistItemsAPIHandler playlistItemsHandler = new UserPlaylistItemsAPIHandler();
        TracksAudioFeaturesAPIHandler trackHandler = new TracksAudioFeaturesAPIHandler();

                AnalyzePlaylistInputBoundary analyzePlaylistInputBoundary = new AnalyzePlaylistInteractor(userDataAccessObject, analyzePlaylistDataAccessObject,
                analyzePlaylistOutputBoundary, playlistItemsHandler,trackHandler);

        return new AnalyzePlaylistController(analyzePlaylistInputBoundary);
    }
}
