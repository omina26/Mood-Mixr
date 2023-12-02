package CreateMood;

import org.junit.Test;
import use_case.create_mood.CreateMoodInputData;

import static org.junit.Assert.assertEquals;

public class CreateMoodInputDataTest{

    @Test
    public void TestCreateMoodInputData(){
        String name = "mock";
        int acousticness = 1;
        int danceability = 2;
        int energy = 3;
        int instrumentalness = 4;
        int liveness = 5;
        int speechiness = 6;
        int valence = 7;

        CreateMoodInputData data = new CreateMoodInputData(name, acousticness, danceability, energy, instrumentalness,
                liveness, speechiness, valence);
        assertEquals(data.toString(), name + " " + acousticness + " " + danceability + " " + energy +
                " " + instrumentalness + " " + liveness + " " + speechiness + " " + valence);
    }
}
