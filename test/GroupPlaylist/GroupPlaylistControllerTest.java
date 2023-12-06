package GroupPlaylist;

import entity.User;
import interface_adapter.group_playlist.GroupPlaylistController;
import org.junit.Test;
import use_case.group_playlist.GroupPlaylistInputBoundary;
import use_case.group_playlist.GroupPlaylistInputData;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class GroupPlaylistControllerTest {
    @Test
    public void MakeControllerTest(){
        MockGroupPlaylistInteractor testInteractor = new MockGroupPlaylistInteractor();

        GroupPlaylistController testGroupPlaylistController = new GroupPlaylistController(testInteractor);

        ArrayList<String> mockPlaylists = new ArrayList<String>();
        Boolean mockIsUserPlaylists = false;
        User mockUser = new User("test", "testtoken", "testID");
        String mockNonUserPlaylistID = "";
        testGroupPlaylistController.executeUseCase(mockPlaylists, mockIsUserPlaylists, mockUser, mockNonUserPlaylistID);
        assertEquals(testInteractor.userName, "test");

    }
}

class MockGroupPlaylistInteractor implements GroupPlaylistInputBoundary {

    String userName;


    @Override
    public void execute(GroupPlaylistInputData groupPlaylistInputData) {
        this.userName = groupPlaylistInputData.user.getName();
    }

    @Override
    public void getPlaylists(GroupPlaylistInputData inputData) throws IOException {

    }
}

