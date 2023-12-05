package CreatePlaylist;

import org.junit.Test;
import use_case.create_playlist.CreatePlaylistInputData;
import static org.junit.Assert.assertEquals;

public class CreatePlaylistInputDataTest {

    @Test
    public void TestCreatePlaylistInputData(){
        String name = "mock";

        CreatePlaylistInputData data = new CreatePlaylistInputData(name);
        assertEquals(data.getSelectedMoodName(), name);
    }
}
