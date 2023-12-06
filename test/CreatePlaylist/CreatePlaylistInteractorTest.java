package CreatePlaylist;

import entity.Mood;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistPresenter;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import org.junit.Test;
import entity.User;
import use_case.create_mood.MoodDataAccessInterface;
import use_case.create_playlist.*;
import use_case.login.LoginDataAccessInterface;
import use_case.services.CreatePlaylistAPIHandlerInterface;
import use_case.services.GetRecommendationAPIHandlerInterface;
import use_case.services.UserTopTracksAPIHandlerInterface;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreatePlaylistInteractorTest {

    @Test
    public void testExecuteSuccess(){
        CreatePlaylistInputData inputData = new CreatePlaylistInputData("mock");

        CreatePlaylistViewModel createPlaylistViewModel = new CreatePlaylistViewModel();
        PlaylistCreatedViewModel playlistCreatedViewModel = new PlaylistCreatedViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreatePlaylistPresenter presenter = new CreatePlaylistPresenter(createPlaylistViewModel,
                playlistCreatedViewModel, viewManagerModel);

        MockUserDataAccessObject userDataAccessObject = new MockUserDataAccessObject();
        MockMoodDataAccessObject moodDataAccessObject = new MockMoodDataAccessObject();

        MockTopTracksHandlerSuccess topTracksHandler = new MockTopTracksHandlerSuccess();
        MockGetRecommendationsHandler getRecommendationsHandler = new MockGetRecommendationsHandler();
        MockCreatePlaylistHandler createPlaylistHandler = new MockCreatePlaylistHandler();

        CreatePlaylistInteractor interactor = new CreatePlaylistInteractor(userDataAccessObject, moodDataAccessObject, presenter, topTracksHandler, getRecommendationsHandler, createPlaylistHandler);

        interactor.execute(inputData);

        assertEquals("playlist created", viewManagerModel.getActiveView());
    }

    @Test
    public void testExecuteFail(){
        CreatePlaylistInputData inputData = new CreatePlaylistInputData("mock");

        CreatePlaylistViewModel createPlaylistViewModel = new CreatePlaylistViewModel();
        PlaylistCreatedViewModel playlistCreatedViewModel = new PlaylistCreatedViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreatePlaylistPresenter presenter = new CreatePlaylistPresenter(createPlaylistViewModel,
                playlistCreatedViewModel, viewManagerModel);

        MockUserDataAccessObject userDataAccessObject = new MockUserDataAccessObject();
        MockMoodDataAccessObject moodDataAccessObject = new MockMoodDataAccessObject();

        MockTopTracksHandlerFail topTracksHandler = new MockTopTracksHandlerFail();
        MockGetRecommendationsHandler getRecommendationsHandler = new MockGetRecommendationsHandler();
        MockCreatePlaylistHandler createPlaylistHandler = new MockCreatePlaylistHandler();

        CreatePlaylistInteractor interactor = new CreatePlaylistInteractor(userDataAccessObject, moodDataAccessObject, presenter, topTracksHandler, getRecommendationsHandler, createPlaylistHandler);

        interactor.execute(inputData);

        assertEquals(null, viewManagerModel.getActiveView());

    }
}

class MockUserDataAccessObject implements LoginDataAccessInterface {
    User mockUser = new User("mockUser", "123", "mockid");
    @Override
    public void loginUser(User user) {}

    @Override
    public User getCurrentUser(){return mockUser;}

}

class MockMoodDataAccessObject implements MoodDataAccessInterface {
    String content = "";

    @Override
    public Map<String, Mood> getMoods() {
        HashMap<String, Mood> moodsMap = new HashMap<>();
        moodsMap.put("mock", new Mood("mock",1.0, 1.0,
                1.0, 1.0, 1.0, 1.0, 1.0));
        return moodsMap;
    }

    @Override
    public Set<String> getMoodNames() {
        HashSet<String> moodNames = new HashSet<>();
        moodNames.add("mock");
        return moodNames;
    }

    @Override
    public void saveMood(String name, double acousticness, double danceability, double energy, double instrumentalness,
                         double liveness, double speechiness, double valence) throws IOException {
        this.content = name + " " + acousticness + " " + danceability + " " + energy + " " + instrumentalness
                + " " + liveness + " " + speechiness + " " + valence;
    }

    @Override
    public void save() throws IOException {}
}

class MockTopTracksHandlerSuccess implements UserTopTracksAPIHandlerInterface{
    List<String> topTracks = Arrays.asList("spotify:track:1", "spotify:track:2", "spotify:track:3");;

    @Override
    public List<String> getUserTopTracks(String accessToken){
        return this.topTracks;
    }

}

class MockTopTracksHandlerFail implements UserTopTracksAPIHandlerInterface{
    List<String> topTracks = Arrays.asList();

    @Override
    public List<String> getUserTopTracks(String accessToken){
        throw new RuntimeException();
    }

}

class MockGetRecommendationsHandler implements GetRecommendationAPIHandlerInterface{
    List<String> recs = Arrays.asList("spotify:track:4","spotify:track:5","spotify:track:6");
    @Override
    public List<String> getRecommendation(String accessToken, List<String> seedTracks, double acousticness, double danceability, double energy, double instrumentalness, double liveness, double speechiness, double valence) {
        return this.recs;
    }

}

class MockCreatePlaylistHandler implements CreatePlaylistAPIHandlerInterface{
    @Override
    public void createPlaylist(String accessToken, String userId, String playlistName, List<String> recommendations){
    }
}
