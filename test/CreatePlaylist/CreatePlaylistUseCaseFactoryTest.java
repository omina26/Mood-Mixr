package CreatePlaylist;

import app.CreatePlaylistUseCaseFactory;
import entity.Mood;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import org.junit.Test;
import use_case.create_mood.MoodDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import view.CreatePlaylistView;
import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class CreatePlaylistUseCaseFactoryTest {

    @Test
    public void createTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreatePlaylistViewModel createPlaylistViewModel = new CreatePlaylistViewModel();
        PlaylistCreatedViewModel playlistCreatedViewModel = new PlaylistCreatedViewModel();
        MockUserDataAccessObject userDataAccessObject = new MockUserDataAccessObject();
        MockMoodDataAccessObject moodDataAccessObject = new MockMoodDataAccessObject();

        CreatePlaylistView actual = CreatePlaylistUseCaseFactory.create(viewManagerModel, createPlaylistViewModel, playlistCreatedViewModel, userDataAccessObject, moodDataAccessObject);

        assertEquals("Create Playlist", actual.viewName);
    }


    class MockMoodDataAccessObject implements MoodDataAccessInterface{

        @Override
        public Map<String, Mood> getMoods() {
            return null;
        }

        @Override
        public Set<String> getMoodNames() {
            return null;
        }

        @Override
        public void saveMood(String name, double acousticness, double danceability, double energy, double instrumentalness, double liveness, double speechiness, double valence) throws IOException {

        }

        @Override
        public void save() throws IOException {

        }
    }

    class MockUserDataAccessObject implements LoginDataAccessInterface{

        @Override
        public void loginUser(User user) {

        }

        @Override
        public User getCurrentUser() {
            return null;
        }
    }
}

