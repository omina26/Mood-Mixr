package AnalyzePlaylist;

import entity.AnalyzedPlaylist;
import entity.User;
import org.junit.Test;
import use_case.analyze_playlist.*;
import use_case.login.LoginDataAccessInterface;
import use_case.services.TracksAudioFeaturesAPIHandler;
import use_case.services.UserPlaylistItemsAPIHandler;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

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

        // Manually calculate expected average features based on mock data
        Map<String, Double> expectedAverageFeatures = new HashMap<>();
        expectedAverageFeatures.put("acousticness", (0.5 + 0.3) / 2);
        expectedAverageFeatures.put("danceability", (0.7 + 0.6) / 2);
        expectedAverageFeatures.put("energy", (0.6 + 0.8) / 2);
        expectedAverageFeatures.put("instrumentalness", (0.1 + 0.2) / 2);
        expectedAverageFeatures.put("liveness", (0.3 + 0.5) / 2);
        expectedAverageFeatures.put("speechiness", (0.4 + 0.6) / 2);
        expectedAverageFeatures.put("valence", (0.5 + 0.7) / 2);

        // Expected track IDs based on mock data
        Set<String> expectedTrackIDs = new HashSet<>(Arrays.asList("123", "456"));

        // Construct the expected AnalyzePlaylistOutputData
        AnalyzePlaylistOutputData expectedOutputData = new AnalyzePlaylistOutputData(
                expectedTrackIDs, expectedAverageFeatures, false);

        interactor.execute(inputData);
        assertEquals(mockPlaylistDataAccessObject.content, "");
        //assertEquals(mockPresenter.outputDataS, "mockID");

        // Assert that the presenter received the expected data
        //assertEquals(expectedOutputData, mockPresenter.outputData);

        assertNotNull(mockPresenter.outputData);
        assertEquals(expectedOutputData.getPlaylistIDs(), mockPresenter.outputData.getPlaylistIDs());
        // Asserting each average feature
        assertEquals(expectedOutputData.getAverageFeatures().get("acousticness"), mockPresenter.outputData.getAverageFeatures().get("acousticness"));
        assertEquals(expectedOutputData.getAverageFeatures().get("danceability"), mockPresenter.outputData.getAverageFeatures().get("danceability"));
        assertEquals(expectedOutputData.getAverageFeatures().get("energy"), mockPresenter.outputData.getAverageFeatures().get("energy"));
        assertEquals(expectedOutputData.getAverageFeatures().get("instrumentalness"), mockPresenter.outputData.getAverageFeatures().get("instrumentalness"));
        assertEquals(expectedOutputData.getAverageFeatures().get("liveness"), mockPresenter.outputData.getAverageFeatures().get("liveness"));
        assertEquals(expectedOutputData.getAverageFeatures().get("speechiness"), mockPresenter.outputData.getAverageFeatures().get("speechiness"));
        assertEquals(expectedOutputData.getAverageFeatures().get("valence"), mockPresenter.outputData.getAverageFeatures().get("valence"));

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
        assertTrue(mockPresenter.outputDataS.contains("Could not save playlistID"));
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
    private User mockUser;
    public MockLoginDataAccessObjectSuccess() {
        // Create a mock user with a token
        this.mockUser = new User("mockUserName", "mockToken", "mockID");
    }
    @Override
    public void loginUser(User user) {

    }

    @Override
    public User getCurrentUser() {
        return this.mockUser;
    }
}
class MockPlaylistItemsHandlerSuccess extends UserPlaylistItemsAPIHandler {
    @Override
    public List<String> getPlaylistItems(String token, String playlistID) {
        // Return a list of mock Spotify URIs
        return Arrays.asList("spotify:track:123", "spotify:track:456");
    }
}
class MockTrackHandlerSuccess extends TracksAudioFeaturesAPIHandler{
    @Override
    public JsonObject getTracksAudioFeatures(List<String> trackIDs, String token) {
        // Create a mock response with track audio features
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        JsonObject track1 = Json.createObjectBuilder()
                .add("id", "123")
                .add("acousticness", 0.5)
                .add("danceability", 0.7)
                .add("energy", 0.6)
                .add("instrumentalness", 0.1)
                .add("liveness", 0.3)
                .add("speechiness", 0.4)
                .add("valence", 0.5)
                .build();
        JsonObject track2 = Json.createObjectBuilder()
                .add("id", "456")
                .add("acousticness", 0.3)
                .add("danceability", 0.6)
                .add("energy", 0.8)
                .add("instrumentalness", 0.2)
                .add("liveness", 0.5)
                .add("speechiness", 0.6)
                .add("valence", 0.7)
                .build();

        arrayBuilder.add(track1);
        arrayBuilder.add(track2);
        builder.add("audio_features", arrayBuilder.build());

        return builder.build();
    }
}

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

class MockPlaylistItemsHandlerThrowsError extends UserPlaylistItemsAPIHandler{
}

class MockTrackHandlerThrowsError extends TracksAudioFeaturesAPIHandler{}

class MockPresenter implements AnalyzePlaylistOutputBoundary {

    String outputDataS = "";
    AnalyzePlaylistOutputData outputData;


    @Override
    public void prepareAnalyzedPlaylistView(AnalyzePlaylistOutputData data) {
        this.outputData = data;
    }

    @Override
    public void prepareFailView(String error) {
        this.outputDataS = error;
    }
}

