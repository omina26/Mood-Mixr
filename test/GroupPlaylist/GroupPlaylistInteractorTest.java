package GroupPlaylist;

import data_access.group_playlist.GroupPlaylistDataAccessObject;
import entity.User;
import org.junit.Test;
import use_case.services.GroupPlaylistAPIInterface;

import java.io.IOException;
import java.util.ArrayList;

public class GroupPlaylistInteractorTest {
    @Test
    public void createGroupPlaylistInteractorTest() {
        GroupPlaylistDataAccessObject testGroupPlaylistDataAccessObject = new GroupPlaylistDataAccessObject();

    }
}

class MockGroupPlaylisAPIHandler implements GroupPlaylistAPIInterface {

    @Override
    public ArrayList<String> getPlaylists(User self) throws IOException {
        return null;
    }

    @Override
    public ArrayList<String> getTracks(ArrayList<String> playlistsToCombine, User user) {
        return null;
    }

    @Override
    public Boolean combinePlaylists(ArrayList<String> playlistsToCombine, User user) {
        return null;
    }

    @Override
    public Boolean isValidPlaylist(String playlistID, User user) {
        return null;
    }
}