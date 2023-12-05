package interface_adapter.create_playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.playlist_created.PlaylistCreatedState;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import use_case.create_playlist.CreatePlaylistOutputBoundary;

/**
 * The Presenter for the Create Playlist use case
 */
public class CreatePlaylistPresenter implements CreatePlaylistOutputBoundary{

    private final CreatePlaylistViewModel createPlaylistViewModel;

    private final PlaylistCreatedViewModel playlistCreatedViewModel;

    private ViewManagerModel viewManagerModel;

    /**
     * The constructor for the Create Playlist Presenter object
     * @param createPlaylistViewModel The view model for the Create Playlist View
     * @param playlistCreatedViewModel The view model for the Playlist Created View
     * @param viewManagerModel The view manager to handle the views
     */
    public CreatePlaylistPresenter(CreatePlaylistViewModel createPlaylistViewModel, PlaylistCreatedViewModel playlistCreatedViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.createPlaylistViewModel = createPlaylistViewModel;
        this.playlistCreatedViewModel = playlistCreatedViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares a success screen
     */
    @Override
    public void prepareSuccessView() {
        System.out.println("Playlist successfully created");
        PlaylistCreatedState playlistCreatedState = playlistCreatedViewModel.getState();
        this.playlistCreatedViewModel.setState(playlistCreatedState);
        this.playlistCreatedViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(playlistCreatedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Triggers the pop up window on failure of the use case
     * @param error The error that caused the failure
     */
    @Override
    public void prepareFailView(String error) {
        CreatePlaylistState createPlaylistState = createPlaylistViewModel.getState();
        createPlaylistState.setSaveError(error);
        System.out.println(error);
        createPlaylistViewModel.firePropertyChanged();
    }
}
