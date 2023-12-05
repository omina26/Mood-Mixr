package CreatePlaylist;

import interface_adapter.create_playlist.CreatePlaylistController;
import org.junit.Test;
import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInputData;
import static org.junit.Assert.assertEquals;

public class CreatePlaylistControllerTest {

    @Test
    public void testExecute(){
        MockCreatePlaylistInteractor mockInteractor = new MockCreatePlaylistInteractor();

        CreatePlaylistController controller = new CreatePlaylistController(mockInteractor);
        controller.execute("mock");

        assertEquals(mockInteractor.data, "mock");
    }
}

class MockCreatePlaylistInteractor implements CreatePlaylistInputBoundary{
    String data;
    @Override
    public void execute(CreatePlaylistInputData createPlaylistInputData) {this.data = createPlaylistInputData.getSelectedMoodName();}
}