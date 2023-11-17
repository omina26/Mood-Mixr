package use_case.create_mood;

import entity.Mood;

import java.util.*;

public interface MoodDataAccessInterface {

    public Map<String, Mood> getMoods();

    public Set<String> getMoodNames();

    public void saveMood(String name, double acousticness, double danceability, double energy, double instrumentalness,
                         double liveness, double speechiness, double valence);

    public void save();
}
