package CreatePlaylist;

import entity.Mood;
import org.junit.Test;
import entity.User;
import use_case.create_mood.MoodDataAccessInterface;
import use_case.create_playlist.*;
import use_case.login.LoginDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreatePlaylistInteractorTest {

    @Test
    public void testExecuteSuccess(){
        CreatePlaylistInputData inputData = new CreatePlaylistInputData("mock");

        MockUserDataAccessObject mockUserDataAccessObject = new MockUserDataAccessObject();
        MockMoodDataAccessObject moodDataAccessObject = new MockMoodDataAccessObject();

    }
}

class MockUserDataAccessObject implements LoginDataAccessInterface {
    @Override
    public void loginUser(User user) {

    }

    @Override
    public User getCurrentUser(){return null;}
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


