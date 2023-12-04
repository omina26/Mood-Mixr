package CreateMood;

import app.CreateMoodUseCaseFactory;
import entity.Mood;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_mood.CreateMoodViewModel;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.view_moods.ViewMoodsViewModel;
import org.junit.Test;
import use_case.create_mood.MoodDataAccessInterface;
import view.CreateMoodView;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CreateMoodUseCaseFactoryTest {

    @Test
    public void createTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateMoodViewModel createMoodViewModel = new CreateMoodViewModel();
        ViewMoodsViewModel viewMoodsViewModel = new ViewMoodsViewModel();
        CreatePlaylistViewModel createPlaylistViewModel = new CreatePlaylistViewModel();
        MockMoodDataAccessObject moodDataAccessObject = new MockMoodDataAccessObject();

        CreateMoodView actual = CreateMoodUseCaseFactory.create(viewManagerModel, createMoodViewModel, viewMoodsViewModel, createPlaylistViewModel, moodDataAccessObject);

        assertEquals("Create Mood", actual.viewName);
    }
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