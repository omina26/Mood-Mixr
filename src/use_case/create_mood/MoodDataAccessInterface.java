package use_case.create_mood;

import entity.Mood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MoodDataAccessInterface {

    public Map<String, Mood> getMoods();

    public void saveMood(String name, double acousticness, double danceability, double energy, double instrumentalness,
                         double liveness, double speechiness, double valence);

    public void save();
}
