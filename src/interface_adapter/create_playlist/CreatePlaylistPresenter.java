package interface_adapter.create_playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.playlist_created.PlaylistCreatedState;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import use_case.create_playlist.CreatePlaylistOutputBoundary;

public class CreatePlaylistPresenter implements CreatePlaylistOutputBoundary {

    private final CreatePlaylistViewModel createPlaylistViewModel;

    private final PlaylistCreatedViewModel playlistCreatedViewModel;

    private ViewManagerModel viewManagerModel;

    public CreatePlaylistPresenter(CreatePlaylistViewModel createPlaylistViewModel, PlaylistCreatedViewModel playlistCreatedViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.createPlaylistViewModel = createPlaylistViewModel;
        this.playlistCreatedViewModel = playlistCreatedViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    //@Override
    public void prepareSuccessView() {
        System.out.println("Playlist successfully created");
        PlaylistCreatedState playlistCreatedState = playlistCreatedViewModel.getState();
        this.playlistCreatedViewModel.setState(playlistCreatedState);
        this.playlistCreatedViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(playlistCreatedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    //@Override
    public void prepareFailView(String error) {
        CreatePlaylistState createPlaylistState = createPlaylistViewModel.getState();
        createPlaylistState.setSaveError(error);
        createPlaylistViewModel.firePropertyChanged();
    }
}
