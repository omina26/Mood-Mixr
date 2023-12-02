package CreateMood;

import entity.Mood;
import org.junit.Test;
import use_case.create_mood.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateMoodInteractorTest {

    @Test
    public void testExecuteSuccess(){
        CreateMoodInputData inputData = new CreateMoodInputData("mock", 100, 100,
                100, 100, 100, 100, 100);

        MockMoodDataAccessObjectSuccess mockDataAccessObject = new MockMoodDataAccessObjectSuccess();
        MockPresenter mockPresenter = new MockPresenter();

        CreateMoodInteractor interactor = new CreateMoodInteractor(mockDataAccessObject, mockPresenter);

        interactor.execute(inputData);
        assertEquals(mockDataAccessObject.content, "mock 1.0 1.0 1.0 1.0 1.0 1.0 1.0");
        assertEquals(mockPresenter.outputData, "[mock] false");
    }

    @Test
    public void testExecuteFail(){
        CreateMoodInputData inputData = new CreateMoodInputData("mock", 100, 100,
                100, 100, 100, 100, 100);

        MockMoodDataAccessObjectThrowsError mockDataAccessObject = new MockMoodDataAccessObjectThrowsError();
        MockPresenter mockPresenter = new MockPresenter();

        CreateMoodInteractor interactor = new CreateMoodInteractor(mockDataAccessObject, mockPresenter);

        interactor.execute(inputData);
        assertTrue(mockPresenter.outputData.contains("Could not save mood: "));
    }
}

class MockMoodDataAccessObjectSuccess implements MoodDataAccessInterface {

    String content = "";

    @Override
    public Map<String, Mood> getMoods() {
        return null;
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
    public void save() throws IOException {

    }
}

class MockMoodDataAccessObjectThrowsError implements MoodDataAccessInterface {

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
        throw new IOException();
    }

    @Override
    public void save() throws IOException {

    }
}

class MockPresenter implements CreateMoodOutputBoundary {

    String outputData = null;

    @Override
    public void prepareMoodListView(CreateMoodOutputData data) {
        this.outputData = data.getMoodNames().toString() + " " + data.getUseCaseFailed();
    }

    @Override
    public void prepareFailView(String error) {
        this.outputData = error;
    }
}