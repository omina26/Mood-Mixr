package AnalyzePlaylist;

import app.AnalyzePlaylistUseCaseFactory;
import entity.AnalyzedPlaylist;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import interface_adapter.analyzed_playlist.AnalyzedPlaylistViewModel;
import interface_adapter.create_mood.CreateMoodViewModel;
import org.junit.Test;
import use_case.analyze_playlist.AnalyzePlaylistDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import view.AnalyzePlaylistView;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class AnalyzePlaylistUseCaseFactoryTest {
    @Test
    public void createTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AnalyzePlaylistViewModel analyzePlaylistViewModel = new AnalyzePlaylistViewModel();
        AnalyzedPlaylistViewModel analyzedPlaylistViewModel = new AnalyzedPlaylistViewModel();
        MockAnalyzePlaylistDataAccessObject analyzePlaylistDataAccessObject = new MockAnalyzePlaylistDataAccessObject();
        MockLoginDataAccessObject userDataAccessObject = new MockLoginDataAccessObject();
        CreateMoodViewModel createMoodViewModel = new CreateMoodViewModel();

        AnalyzePlaylistView actual = AnalyzePlaylistUseCaseFactory.create(viewManagerModel, analyzePlaylistViewModel,
                analyzedPlaylistViewModel, analyzePlaylistDataAccessObject, userDataAccessObject, createMoodViewModel);

        assertEquals("Analyze Playlist", actual.viewName);
    }
}

class MockAnalyzePlaylistDataAccessObject implements AnalyzePlaylistDataAccessInterface{

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

class MockLoginDataAccessObject implements LoginDataAccessInterface{

    @Override
    public void loginUser(User user) throws IOException {

    }

    @Override
    public User getCurrentUser() {
        return null;
    }
}
