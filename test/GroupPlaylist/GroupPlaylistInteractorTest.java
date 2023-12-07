package GroupPlaylist;

import data_access.group_playlist.GroupPlaylistDataAccessObject;
import entity.User;
import org.junit.Test;
import use_case.group_playlist.GroupPlaylistInputData;
import use_case.group_playlist.GroupPlaylistInteractor;
import use_case.group_playlist.GroupPlaylistOutputBoundary;
import use_case.group_playlist.GroupPlaylistOutputData;
import use_case.services.GroupPlaylistAPIInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class GroupPlaylistInteractorTest {
        @Test
        public void getPlaylistsGroupPlaylistInteractorTest() {
            GroupPlaylistDataAccessObject testGroupPlaylistDataAccessObject = new GroupPlaylistDataAccessObject();
            MockGroupPlaylistAPIHandler mockGroupPlaylistAPIHandler = new MockGroupPlaylistAPIHandler();
            MockGroupPlaylistPresenter mockGroupPlaylistPresenter = new MockGroupPlaylistPresenter();

            User user = new User("0", "0", "0");
            Boolean selfOnly = true;
            String playlistID = "";
            GroupPlaylistInputData groupPlaylistInputData = new GroupPlaylistInputData(user, selfOnly, playlistID);

            GroupPlaylistInteractor groupPlaylistInteractor = new GroupPlaylistInteractor(
                    testGroupPlaylistDataAccessObject, mockGroupPlaylistPresenter, mockGroupPlaylistAPIHandler);

            groupPlaylistInteractor.getPlaylists(groupPlaylistInputData);

            assertEquals("", mockGroupPlaylistPresenter.result);
        }

    @Test
    public void executeGroupPlaylistInteractorTestIfBlock() {
        GroupPlaylistDataAccessObject testGroupPlaylistDataAccessObject = new GroupPlaylistDataAccessObject();
        MockGroupPlaylistAPIHandler mockGroupPlaylistAPIHandler = new MockGroupPlaylistAPIHandler();
        MockGroupPlaylistPresenter mockGroupPlaylistPresenter = new MockGroupPlaylistPresenter();

        ArrayList<String> playlists = new ArrayList<String>();
        playlists.add("2");
        User user = new User("0", "100", "0");
        Boolean selfOnly = true;
        String playlistID = "";
        GroupPlaylistInputData groupPlaylistInputData = new GroupPlaylistInputData(playlists, selfOnly,
                user, playlistID);

        GroupPlaylistInteractor groupPlaylistInteractor = new GroupPlaylistInteractor(
                testGroupPlaylistDataAccessObject, mockGroupPlaylistPresenter, mockGroupPlaylistAPIHandler);

        groupPlaylistInteractor.execute(groupPlaylistInputData);

        assertEquals( "name", mockGroupPlaylistPresenter.groupPlaylistOutputData.playlistName);
        assertEquals("Successfully created playlist!" ,mockGroupPlaylistPresenter.result);
    }

    @Test
    public void executeGroupPlaylistInteractorTestElseBlock() {
        GroupPlaylistDataAccessObject testGroupPlaylistDataAccessObject = new GroupPlaylistDataAccessObject();
        MockGroupPlaylistAPIHandler mockGroupPlaylistAPIHandler = new MockGroupPlaylistAPIHandler();
        MockGroupPlaylistPresenter mockGroupPlaylistPresenter = new MockGroupPlaylistPresenter();

        ArrayList<String> playlists = new ArrayList<String>();
        playlists.add("2");
        User user = new User("0", "100", "0");
        Boolean selfOnly = false;
        String playlistID = "";
        GroupPlaylistInputData groupPlaylistInputData = new GroupPlaylistInputData(playlists, selfOnly,
                user, playlistID);

        GroupPlaylistInteractor groupPlaylistInteractor = new GroupPlaylistInteractor(
                testGroupPlaylistDataAccessObject, mockGroupPlaylistPresenter, mockGroupPlaylistAPIHandler);

        groupPlaylistInteractor.execute(groupPlaylistInputData);

        assertEquals("Could not properly create group playlist :(", mockGroupPlaylistPresenter.result);
    }
    @Test
    public void executeGroupPlaylistInteractorTestElseBlockValidPlaylist() {
        GroupPlaylistDataAccessObject testGroupPlaylistDataAccessObject = new GroupPlaylistDataAccessObject();
        MockGroupPlaylistAPIHandler mockGroupPlaylistAPIHandler = new MockGroupPlaylistAPIHandler();
        MockGroupPlaylistPresenter mockGroupPlaylistPresenter = new MockGroupPlaylistPresenter();

        ArrayList<String> playlists = new ArrayList<String>();
        playlists.add("2");
        User user = new User("0", "100", "2");
        Boolean selfOnly = false;
        String playlistID = "2";
        GroupPlaylistInputData groupPlaylistInputData = new GroupPlaylistInputData(playlists, selfOnly,
                user, playlistID);

        GroupPlaylistInteractor groupPlaylistInteractor = new GroupPlaylistInteractor(
                testGroupPlaylistDataAccessObject, mockGroupPlaylistPresenter, mockGroupPlaylistAPIHandler);

        groupPlaylistInteractor.execute(groupPlaylistInputData);

        assertEquals("Successfully created playlist!", mockGroupPlaylistPresenter.result);
    }
}

class MockGroupPlaylistPresenter implements GroupPlaylistOutputBoundary{
    String result;
    GroupPlaylistOutputData groupPlaylistOutputData;

    @Override
    public void getCurrentUserPlaylistsSuccessView(GroupPlaylistOutputData groupPlaylistOutputData, GroupPlaylistOutputData names) {
        this.groupPlaylistOutputData = groupPlaylistOutputData;
        this.result = "";
    }

    @Override
    public void prepareSuccessView(GroupPlaylistOutputData groupPlaylistOutputData, String successMessage) {
        this.result = successMessage;
        this.groupPlaylistOutputData = groupPlaylistOutputData;
    }

    @Override
    public void prepareFailView(GroupPlaylistOutputData groupPlaylistOutputData, String failMessage) {
        this.result = failMessage;
        this.groupPlaylistOutputData = groupPlaylistOutputData;
    }
}




class MockGroupPlaylistAPIHandler implements GroupPlaylistAPIInterface {

    @Override
    public ArrayList<ArrayList<String>> getPlaylists(User self) throws IOException {
        ArrayList<String> test1 = new ArrayList<String>();
        ArrayList<String> test2 = new ArrayList<String>();
        ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
        test.add(test1);
        test.add(test2);
        if (!(self.getToken().equals(100))){
            return test;
        } else{
            IOException IOException = new IOException();
            throw IOException;
        }

    }

    @Override
    public ArrayList<String> getTracks(ArrayList<String> playlistsToCombine, User user) {
        ArrayList<String> test = new ArrayList<String>();
        test.add(user.getName());
        return test;
    }

    @Override
    public Boolean combinePlaylists(ArrayList<String> playlistsToCombine, User user) {
        return Objects.equals(playlistsToCombine.get(0), "2");
    }

    @Override
    public Boolean isValidPlaylist(String playlistID, User user) {
        return Objects.equals(playlistID, user.getUserId());
    }
}

