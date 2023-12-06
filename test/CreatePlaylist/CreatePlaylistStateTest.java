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

        CreatePlaylistState main2 = new CreatePlaylistState("mock2");
        copy.setSelectedMood("mock2");
        assertEquals(main2.getSelectedMood(), copy.getSelectedMood());
    }
}
