package CreateMood;

import data_access.create_mood.MoodDataAccessObject;
import entity.Mood;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MoodDataAccessObjectTest {

    @Test
    public void testSave() throws IOException {
        File tempFile = new File("moods.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.print("");
        writer.close();
        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        String header = reader.readLine();
        assertEquals(header, "name,acousticness,danceability,energy,instrumentalness,liveness,speechiness,valence");
    }

    @Test
    public void tesCreateMoodDataAccessObjectEmptyFile() throws IOException {
        File tempFile = new File("moods.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.print("");
        writer.close();
        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        MoodDataAccessObject moodDataAccessObject = new MoodDataAccessObject(tempFile);
        String header = reader.readLine();
        assertEquals(header, "name,acousticness,danceability,energy,instrumentalness,liveness,speechiness,valence");
    }

    @Test
    public void tesCreateMoodDataAccessObjectNonEmptyFile() throws IOException {
        File tempFile = new File("moods.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.println("name,acousticness,danceability,energy,instrumentalness,liveness,speechiness,valence");
        writer.print("mockname,1,1,1,1,1,1,1");
        writer.close();
        MoodDataAccessObject moodDataAccessObject = new MoodDataAccessObject(tempFile);

        Map<String, Mood> expected = new HashMap<String, Mood>();
        expected.put("mockname", new Mood("mockname", 1,1,1,1,1,1,1));
        Map<String, Mood> actual = moodDataAccessObject.getMoods();

        assertEquals(expected.keySet(), actual.keySet());
        for (String key : expected.keySet()){
            assertEquals(expected.get(key).getName(), actual.get(key).getName());
            assertEquals(expected.get(key).getAcousticness(), actual.get(key).getAcousticness(), 0.1);
            assertEquals(expected.get(key).getDanceability(), actual.get(key).getDanceability(), 0.1);
            assertEquals(expected.get(key).getEnergy(), actual.get(key).getEnergy(), 0.1);
            assertEquals(expected.get(key).getInstrumentalness(), actual.get(key).getInstrumentalness(), 0.1);
            assertEquals(expected.get(key).getLiveness(), actual.get(key).getLiveness(), 0.1);
            assertEquals(expected.get(key).getSpeechiness(), actual.get(key).getSpeechiness(), 0.1);
            assertEquals(expected.get(key).getValence(), actual.get(key).getValence(), 0.1);
        }
    }

    @Test
    public void testSaveMood() throws IOException {
        File tempFile = new File("moods.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.print("name,acousticness,danceability,energy,instrumentalness,liveness,speechiness,valence");
        writer.close();

        MoodDataAccessObject moodDataAccessObject = new MoodDataAccessObject(tempFile);

        Mood mockMood = new Mood("mockname", 1,1,1,1,1,1,1);

        Map<String, Mood> expected = new HashMap<String, Mood>();
        expected.put("mockname", mockMood);

        moodDataAccessObject.saveMood("mockname", 1, 1, 1, 1, 1, 1, 1);

        Map<String, Mood> actual = moodDataAccessObject.getMoods();

        for (String key : expected.keySet()){
            assertEquals(expected.get(key).getName(), actual.get(key).getName());
            assertEquals(expected.get(key).getAcousticness(), actual.get(key).getAcousticness(), 0.1);
            assertEquals(expected.get(key).getDanceability(), actual.get(key).getDanceability(), 0.1);
            assertEquals(expected.get(key).getEnergy(), actual.get(key).getEnergy(), 0.1);
            assertEquals(expected.get(key).getInstrumentalness(), actual.get(key).getInstrumentalness(), 0.1);
            assertEquals(expected.get(key).getLiveness(), actual.get(key).getLiveness(), 0.1);
            assertEquals(expected.get(key).getSpeechiness(), actual.get(key).getSpeechiness(), 0.1);
            assertEquals(expected.get(key).getValence(), actual.get(key).getValence(), 0.1);
        }

        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        String header = reader.readLine();
        assertEquals(header, "name,acousticness,danceability,energy,instrumentalness,liveness,speechiness,valence");
        String nextLine = reader.readLine();
        assertEquals(nextLine, "mockname,1.0,1.0,1.0,1.0,1.0,1.0,1.0");
    }
}
