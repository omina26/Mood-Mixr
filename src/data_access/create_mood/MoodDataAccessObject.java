package data_access.create_mood;

import entity.Mood;
import use_case.create_mood.MoodDataAccessInterface;

import java.io.*;
import java.util.*;

/**
 * This class represents the Data Access Object that stores the Moods
 */
public class MoodDataAccessObject implements MoodDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Mood> moods = new HashMap<>();

    /**
     * Constructor for MoodDataAccessObject, which gives a MoodDataAccessObject type and adds the appropriate
     * headers to the csv file
     * @param csvFile The file to store the Mood attributes
     * @throws IOException
     */
    public MoodDataAccessObject(File csvFile) throws IOException {
        this.csvFile = csvFile;
        headers.put("name", 0);
        headers.put("acousticness", 1);
        headers.put("danceability", 2);
        headers.put("energy", 3);
        headers.put("instrumentalness", 4);
        headers.put("liveness", 5);
        headers.put("speechiness", 6);
        headers.put("valence", 7);

        if (csvFile.length() == 0){
            save();
            return;
        }


        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();

            // For later: clean this up by creating a new Exception subclass and handling it in the UI.
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String name = String.valueOf(col[headers.get("name")]);
                double acousticness = Double.valueOf(col[headers.get("acousticness")]);
                double danceability = Double.valueOf(col[headers.get("danceability")]);
                double energy = Double.valueOf(col[headers.get("energy")]);
                double instrumentalness = Double.valueOf(col[headers.get("instrumentalness")]);
                double liveness = Double.valueOf(col[headers.get("liveness")]);
                double speechiness = Double.valueOf(col[headers.get("speechiness")]);
                double valence = Double.valueOf(col[headers.get("valence")]);
                Mood mood = new Mood(name, acousticness, danceability, energy, instrumentalness, liveness, speechiness, valence);
                moods.put(name, mood);
            }
        }
    }

    /**
     * Gets the moods stored locally
     * @return A Map of the mood name to its corresponding Mood object
     */
    @Override
    public Map<String, Mood> getMoods() {
        return this.moods;
    }

    /**
     * Gets the names of all the moods stored
     * @return The names of the moods
     */
    @Override
    public Set<String> getMoodNames() {
        return this.moods.keySet();
    }

    /**
     * Saves a Mood with the attributes entered as parameters
     * @param name Name of the mood
     * @param acousticness The acousticness from 0 to 1.0
     * @param danceability The danceability from 0 to 1.0
     * @param energy The energy from 0 to 1.0
     * @param instrumentalness The danceability from 0 to 1.0
     * @param liveness The liveness from 0 to 1.0
     * @param speechiness The speechiness from 0 to 1.0
     * @param valence The valence from 0 to 1.0
     * @throws IOException
     */
    @Override
    public void saveMood(String name, double acousticness, double danceability, double energy, double instrumentalness,
                         double liveness, double speechiness, double valence) throws IOException {
        Mood mood = new Mood(name,acousticness, danceability, energy, instrumentalness, liveness, speechiness, valence);
        this.moods.put(mood.getName(), mood);
        this.save();
    }

    /**
     * Saves the locally stored moods in csvFile
     * @throws IOException
     */
    @Override
    public void save() throws IOException {
        BufferedWriter writer;

            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Mood mood : moods.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                        mood.getName(), mood.getAcousticness(), mood.getDanceability(), mood.getEnergy(), mood.getInstrumentalness(),
                        mood.getLiveness(), mood.getSpeechiness(), mood.getValence());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

    }
}
