package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.create_playlist.CreatePlaylistPresenter;
import interface_adapter.create_playlist.CreatePlaylistState;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInteractor;
import use_case.create_playlist.CreatePlaylistOutputBoundary;
import view.CreatePlaylistView;

public class CreatePlaylistUseCaseFactory {
    private CreatePlaylistUseCaseFactory() {}

    public static CreatePlaylistView create(ViewManagerModel viewManagerModel,
                                            CreatePlaylistViewModel createPlaylistViewModel, CreatePlaylistState createPlaylistState) {
        CreatePlaylistController createPlaylistController = createPlaylistUseCase(viewManagerModel, createPlaylistViewModel);
        return new CreatePlaylistView(createPlaylistViewModel, createPlaylistController, createPlaylistState);
    }

    private static CreatePlaylistController createPlaylistUseCase(ViewManagerModel viewManagerModel,
                                                                  CreatePlaylistViewModel createPlaylistViewModel){
        //CreatePlaylistOutputBoundary createPlaylistOutputBoundary =
        //        new CreatePlaylistPresenter(createPlaylistViewModel, playlistCreatedViewModel, viewManagerModel);

        //CreatePlaylistInputBoundary createPlaylistInputBoundary = new CreatePlaylistInteractor(createPlaylistOutputBoundary);

        CreatePlaylistInputBoundary createPlaylistInputBoundary = (CreatePlaylistInputBoundary) new CreatePlaylistInteractor();

        return new CreatePlaylistController(createPlaylistInputBoundary);
    }
}
