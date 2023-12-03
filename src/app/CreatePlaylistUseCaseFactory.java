package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.create_playlist.CreatePlaylistPresenter;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import use_case.create_mood.MoodDataAccessInterface;
import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInteractor;
import use_case.create_playlist.CreatePlaylistOutputBoundary;
import use_case.services.CreatePlaylistAPIHandler;
import use_case.services.GetRecommendationAPIHandler;
import use_case.login.LoginDataAccessInterface;
import view.CreatePlaylistView;

public class CreatePlaylistUseCaseFactory {
    private CreatePlaylistUseCaseFactory() {}

    public static CreatePlaylistView create(ViewManagerModel viewManagerModel,
                                            CreatePlaylistViewModel createPlaylistViewModel,
                                            PlaylistCreatedViewModel playlistCreatedViewModel,
                                            LoginDataAccessInterface userDataAccessObject,
                                            MoodDataAccessInterface moodDataAccessObject) {
        CreatePlaylistController createPlaylistController = createPlaylistUseCase(viewManagerModel,
                createPlaylistViewModel, playlistCreatedViewModel, userDataAccessObject, moodDataAccessObject);
        return new CreatePlaylistView(createPlaylistViewModel, createPlaylistController);
    }

    private static CreatePlaylistController createPlaylistUseCase(ViewManagerModel viewManagerModel,
                                                                  CreatePlaylistViewModel createPlaylistViewModel,
                                                                  PlaylistCreatedViewModel playlistCreatedViewModel,
                                                                  LoginDataAccessInterface userDataAccessObject,
                                                                  MoodDataAccessInterface moodDataAccessObject){
        CreatePlaylistOutputBoundary createPlaylistOutputBoundary =
                new CreatePlaylistPresenter(createPlaylistViewModel, playlistCreatedViewModel, viewManagerModel);

        GetRecommendationAPIHandler getRecommendationHandler = new GetRecommendationAPIHandler();
        CreatePlaylistAPIHandler playlistHandler = new CreatePlaylistAPIHandler();
        CreatePlaylistInputBoundary createPlaylistInputBoundary = new CreatePlaylistInteractor(userDataAccessObject,
                moodDataAccessObject, createPlaylistOutputBoundary, getRecommendationHandler, playlistHandler);

        return new CreatePlaylistController(createPlaylistInputBoundary);
    }
}
