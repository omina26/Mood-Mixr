package CreatePlaylist;

import interface_adapter.create_playlist.CreatePlaylistState;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CreatePlaylistStateTest {

    @Test
    public void TestCreatePlaylistStateWithCopy(){
        CreatePlaylistState copy = new CreatePlaylistState("mock");
        CreatePlaylistState main = new CreatePlaylistState("mock");

        assertEquals(main.getSelectedMood(), copy.getSelectedMood());
    }
}
