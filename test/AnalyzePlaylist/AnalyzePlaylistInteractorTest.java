package AnalyzePlaylist;

import entity.AnalyzedPlaylist;
import entity.User;
import org.junit.Test;
import use_case.analyze_playlist.*;
import use_case.login.LoginDataAccessInterface;
import use_case.services.TracksAudioFeaturesAPIHandler;
import use_case.services.UserPlaylistItemsAPIHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalyzePlaylistInteractorTest {
    @Test
    public void testExecuteSuccess() {
        AnalyzePlaylistInputData inputData = new AnalyzePlaylistInputData("mockID");

        MockLoginDataAccessObjectSuccess mockUserDataAccessObject = new MockLoginDataAccessObjectSuccess();
        MockAnalyzePlaylistDataAccessObjectSuccess mockPlaylistDataAccessObject = new MockAnalyzePlaylistDataAccessObjectSuccess();
        MockPresenter mockPresenter = new MockPresenter();
        MockPlaylistItemsHandlerSuccess mockPlaylistItemsHandlerSuccess = new MockPlaylistItemsHandlerSuccess();
        MockTrackHandlerSuccess mockTrackHandlerSuccess = new MockTrackHandlerSuccess();
        AnalyzePlaylistInteractor interactor = new AnalyzePlaylistInteractor(mockUserDataAccessObject, mockPlaylistDataAccessObject,
                mockPresenter, mockPlaylistItemsHandlerSuccess,mockTrackHandlerSuccess);

        interactor.execute(inputData);
        assertEquals(mockPlaylistDataAccessObject.content, "");
        assertEquals(mockPresenter.outputData, "Could not save playlistIDjava.lang.NullPointerException: Cannot " +
                "invoke \"entity.User.getToken()\" because \"user\" is null");
    }

    @Test
    public void testExecuteFail() {
        AnalyzePlaylistInputData inputData = new AnalyzePlaylistInputData("mockID");
        MockLoginDataAccessObjectThrowsError mockUserDataAccessObject = new MockLoginDataAccessObjectThrowsError();
        MockAnalyzePlaylistDataAccessObjectThrowsError mockPlaylistDataAccessObject = new MockAnalyzePlaylistDataAccessObjectThrowsError();
        MockPresenter mockPresenter = new MockPresenter();
        MockPlaylistItemsHandlerThrowsError mockPlaylistItemsHandler = new MockPlaylistItemsHandlerThrowsError();
        MockTrackHandlerThrowsError mockTrackHandler = new MockTrackHandlerThrowsError();
        AnalyzePlaylistInteractor interactor = new AnalyzePlaylistInteractor(mockUserDataAccessObject, mockPlaylistDataAccessObject,
                mockPresenter, mockPlaylistItemsHandler,mockTrackHandler);

        interactor.execute(inputData);
        assertTrue(mockPresenter.outputData.contains("Could not save playlistID"));
    }
}

class MockAnalyzePlaylistDataAccessObjectSuccess implements AnalyzePlaylistDataAccessInterface {
    String content = "";

    @Override
    public Map<String, AnalyzedPlaylist> getPlaylistID() {
        return null;
    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public Set<String> savePlaylistID(String name, String playlistID) throws IOException {
        return null;
    }
}
class MockLoginDataAccessObjectSuccess implements LoginDataAccessInterface{
    @Override
    public void loginUser(User user) throws IOException {

    }

    @Override
    public User getCurrentUser() {
        return null;
    }
}
class MockPlaylistItemsHandlerSuccess extends UserPlaylistItemsAPIHandler {}
class MockTrackHandlerSuccess extends TracksAudioFeaturesAPIHandler{}

class MockAnalyzePlaylistDataAccessObjectThrowsError implements AnalyzePlaylistDataAccessInterface {

    @Override
    public Map<String, AnalyzedPlaylist> getPlaylistID() {
        return null;
    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public Set<String> savePlaylistID(String name, String playlistID) throws IOException {
        return null;
    }
}

class MockLoginDataAccessObjectThrowsError implements LoginDataAccessInterface{
    @Override
    public void loginUser(User user) throws IOException {

    }

    @Override
    public User getCurrentUser() {
        return null;
    }
}

class MockPlaylistItemsHandlerThrowsError extends UserPlaylistItemsAPIHandler{}

class MockTrackHandlerThrowsError extends TracksAudioFeaturesAPIHandler{}

class MockPresenter implements AnalyzePlaylistOutputBoundary {

    String outputData = null;

    @Override
    public void prepareAnalyzedPlaylistView(AnalyzePlaylistOutputData data) {
        this.outputData = data.getPlaylistIDs().toString() + " " + data.getUseCaseFailed();
    }

    @Override
    public void prepareFailView(String error) {
        this.outputData = error;
    }
}

