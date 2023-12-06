package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.analyze_playlist.AnalyzePlaylistController;
import interface_adapter.analyze_playlist.AnalyzePlaylistPresenter;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import interface_adapter.analyzed_playlist.AnalyzedPlaylistViewModel;
import interface_adapter.create_mood.CreateMoodViewModel;
import use_case.analyze_playlist.*;
import use_case.login.LoginDataAccessInterface;
import use_case.services.TracksAudioFeaturesAPIHandler;
import use_case.services.UserPlaylistItemsAPIHandler;
import view.AnalyzePlaylistView;

/**
 * Factory class for creating Analyze Playlist use case components.
 * Responsible for creating and wiring together all the necessary components
 * for the Analyze Playlist use case.
 */

public class AnalyzePlaylistUseCaseFactory {
    private AnalyzePlaylistUseCaseFactory() {}

    /**
     * Creates and returns an instance of AnalyzePlaylistView.
     *
     * @param viewManagerModel The ViewManagerModel instance for managing views.
     * @param analyzePlaylistViewModel The ViewModel for Analyze Playlist feature.
     * @param analyzedPlaylistViewModel The ViewModel for Analyzed Playlist feature
     * @param analyzePlaylistDataAccessObject The data access object for Analyze Playlist.
     * @param userDataAccessObject The data access object for user login data.
     * @return An instance of AnalyzePlaylistView.
     */

    public static AnalyzePlaylistView create (ViewManagerModel viewManagerModel,
                                              AnalyzePlaylistViewModel analyzePlaylistViewModel,
                                              AnalyzedPlaylistViewModel analyzedPlaylistViewModel,
                                              AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject,
                                              LoginDataAccessInterface userDataAccessObject,
                                              CreateMoodViewModel createMoodViewModel){

        AnalyzePlaylistController analyzePlaylistController = analyzePlaylistUseCase(viewManagerModel,
                analyzePlaylistViewModel, analyzedPlaylistViewModel, analyzePlaylistDataAccessObject,
                userDataAccessObject, createMoodViewModel);

        return new AnalyzePlaylistView(viewManagerModel, analyzePlaylistViewModel, analyzePlaylistController);
    }

    /**
     * Creates and returns an instance of AnalyzePlaylistController.
     *
     * @param viewManagerModel                The ViewManagerModel instance for managing views.
     * @param analyzePlaylistViewModel        The ViewModel for Analyze Playlist feature.
<<<<<<< HEAD
     * @param analyzedPlaylistViewModel       The ViewModel for Analyzed Playlist feature.
=======
     * @param analyzedPlaylistViewModel
>>>>>>> 5aed474 (resolving conflicts)
     * @param analyzePlaylistDataAccessObject The data access object for Analyze Playlist.
     * @param userDataAccessObject            The data access object for user login data.
     * @return An instance of AnalyzePlaylistController.
     */

    private static AnalyzePlaylistController analyzePlaylistUseCase(ViewManagerModel viewManagerModel,
                                                                    AnalyzePlaylistViewModel analyzePlaylistViewModel,
                                                                    AnalyzedPlaylistViewModel analyzedPlaylistViewModel,
                                                                    AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject,
                                                                    LoginDataAccessInterface userDataAccessObject,
                                                                    CreateMoodViewModel createMoodViewModel) {

        AnalyzePlaylistOutputBoundary analyzePlaylistOutputBoundary =
                new AnalyzePlaylistPresenter(analyzePlaylistViewModel, analyzedPlaylistViewModel, viewManagerModel, createMoodViewModel);

        UserPlaylistItemsAPIHandler playlistItemsHandler = new UserPlaylistItemsAPIHandler();
        TracksAudioFeaturesAPIHandler trackHandler = new TracksAudioFeaturesAPIHandler();

                AnalyzePlaylistInputBoundary analyzePlaylistInputBoundary = new AnalyzePlaylistInteractor(userDataAccessObject, analyzePlaylistDataAccessObject,
                analyzePlaylistOutputBoundary, playlistItemsHandler,trackHandler);

        return new AnalyzePlaylistController(analyzePlaylistInputBoundary);
    }
}
