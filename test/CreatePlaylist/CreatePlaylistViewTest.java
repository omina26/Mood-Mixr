package CreatePlaylist;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import org.junit.Test;
import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInputData;
import view.CreatePlaylistView;

import javax.swing.*;

import static org.junit.Assert.assertEquals;


public class CreatePlaylistViewTest {

    @Test
    public void testCreatePlaylistView(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MockInteractor interactor = new MockInteractor();
        CreatePlaylistController controller = new CreatePlaylistController(interactor);
        CreatePlaylistViewModel viewModel = new CreatePlaylistViewModel();

        JPanel view = new CreatePlaylistView(viewManagerModel, viewModel, controller);

    }
}

class MockInteractor implements CreatePlaylistInputBoundary{
    String execution = "";
    @Override
    public void execute(CreatePlaylistInputData createPlaylistInputData) {}
}
