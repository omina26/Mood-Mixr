package CreatePlaylist;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistPresenter;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CreatePlaylistPresenterTest {

    @Test
    public void testPrepareSuccessView(){
        CreatePlaylistViewModel mockCreatePlaylistViewModel = new CreatePlaylistViewModel();
        PlaylistCreatedViewModel mockPlaylistCreatedViewModel = new PlaylistCreatedViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        CreatePlaylistPresenter presenter = new CreatePlaylistPresenter(mockCreatePlaylistViewModel,
                mockPlaylistCreatedViewModel, mockViewManagerModel);

        presenter.prepareSuccessView();

        assertEquals(mockViewManagerModel.getActiveView(), mockPlaylistCreatedViewModel.getViewName());
    }

    @Test
    public void testPrepareFailView(){
        CreatePlaylistViewModel mockCreatePlaylistViewModel = new CreatePlaylistViewModel();
        PlaylistCreatedViewModel mockPlaylistCreatedViewModel = new PlaylistCreatedViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        CreatePlaylistPresenter presenter = new CreatePlaylistPresenter(mockCreatePlaylistViewModel,
                mockPlaylistCreatedViewModel, mockViewManagerModel);

        presenter.prepareFailView("mock error");
        assertEquals(mockCreatePlaylistViewModel.getState().getSaveError(), "mock error");
    }
}
