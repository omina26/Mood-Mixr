package CreatePlaylist;

//import CreateMood.MockMoodDataAccessObject;
//import Login.MockUserDataAccessObject;
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

    //@Test
    //public void createTest(){
        //ViewManagerModel viewManagerModel = new ViewManagerModel();
        //CreatePlaylistViewModel createPlaylistViewModel = new CreatePlaylistViewModel();
        //PlaylistCreatedViewModel playlistCreatedViewModel = new PlaylistCreatedViewModel();
        //MockUserDataAccessObject userDataAccessObject = new MockUserDataAccessObject();
        //MockMoodDataAccessObject moodDataAccessObject = new MockMoodDataAccessObject();

        //CreatePlaylistView actual = CreatePlaylistUseCaseFactory.create(viewManagerModel, createPlaylistViewModel, playlistCreatedViewModel, userDataAccessObject, moodDataAccessObject);

        //assertEquals("Create Playlist", actual.viewName);
    }
//}

