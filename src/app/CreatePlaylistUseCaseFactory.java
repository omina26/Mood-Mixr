package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.create_playlist.CreatePlaylistPresenter;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import interface_adapter.view_moods.ViewMoodsViewModel;
import use_case.create_mood.MoodDataAccessInterface;
import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInteractor;
import use_case.create_playlist.CreatePlaylistOutputBoundary;
import use_case.services.CreatePlaylistAPIHandler;
import use_case.services.GetRecommendationAPIHandler;
import use_case.login.LoginDataAccessInterface;
import use_case.services.UserTopTracksAPIHandler;
import use_case.services.UserTopTracksAPIHandlerInterface;
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
        return new CreatePlaylistView(viewManagerModel, createPlaylistViewModel, createPlaylistController);
    }

    private static CreatePlaylistController createPlaylistUseCase(ViewManagerModel viewManagerModel,
                                                                  CreatePlaylistViewModel createPlaylistViewModel,
                                                                  PlaylistCreatedViewModel playlistCreatedViewModel,
                                                                  LoginDataAccessInterface userDataAccessObject,
                                                                  MoodDataAccessInterface moodDataAccessObject){
        CreatePlaylistOutputBoundary createPlaylistOutputBoundary =
                new CreatePlaylistPresenter(createPlaylistViewModel, playlistCreatedViewModel, viewManagerModel);

        UserTopTracksAPIHandler userTopTracksAPIHandler = new UserTopTracksAPIHandler();
        GetRecommendationAPIHandler getRecommendationHandler = new GetRecommendationAPIHandler();
        CreatePlaylistAPIHandler playlistHandler = new CreatePlaylistAPIHandler();
        CreatePlaylistInputBoundary createPlaylistInputBoundary = new CreatePlaylistInteractor(userDataAccessObject,
                moodDataAccessObject, createPlaylistOutputBoundary, userTopTracksAPIHandler, getRecommendationHandler, playlistHandler);

        return new CreatePlaylistController(createPlaylistInputBoundary);
    }
}
