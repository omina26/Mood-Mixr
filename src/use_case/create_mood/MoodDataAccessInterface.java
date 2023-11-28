package use_case.create_mood;

import entity.Mood;

import java.io.IOException;
import java.util.*;

public interface MoodDataAccessInterface {

    Map<String, Mood> getMoods();

    Set<String> getMoodNames();

    void saveMood(String name, double acousticness, double danceability, double energy, double instrumentalness,
                         double liveness, double speechiness, double valence) throws IOException;

    void save() throws IOException;
}
