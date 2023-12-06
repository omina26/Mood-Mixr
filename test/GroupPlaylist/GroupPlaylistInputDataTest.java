package GroupPlaylist;

import entity.User;
import org.junit.Test;
import use_case.group_playlist.GroupPlaylistInputData;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GroupPlaylistInputDataTest {
    @Test
    public void groupPlaylistInputDataCreateTest(){
        Boolean mockIsUserPlaylists = false;
        User mockUser = new User("test", "testtoken", "testID");
        String mockNonUserPlaylistID = "testID";
        GroupPlaylistInputData groupPlaylistInputData = new GroupPlaylistInputData(mockUser,
                mockIsUserPlaylists, mockNonUserPlaylistID);
        assertEquals(groupPlaylistInputData.user, mockUser);
        assertEquals(groupPlaylistInputData.nonUserPlaylistID, "testID");
    }
    @Test
    public void groupPlaylistInputDataCreateTestType2(){
        ArrayList<String> mockSelectedPlaylists = new ArrayList<String>();
        Boolean mockIsUserPlaylists = false;
        User mockUser = new User("test", "testtoken", "testID");
        String mockNonUserPlaylistID = "testID";
        GroupPlaylistInputData groupPlaylistInputData = new GroupPlaylistInputData(mockSelectedPlaylists,
                mockIsUserPlaylists, mockUser, mockNonUserPlaylistID);
        assertEquals(groupPlaylistInputData.user, mockUser);
        assertEquals(groupPlaylistInputData.nonUserPlaylistID, "testID");
    }
}
