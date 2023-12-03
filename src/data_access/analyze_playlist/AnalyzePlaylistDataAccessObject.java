package data_access.analyze_playlist;

import entity.AnalyzedPlaylist;
import use_case.analyze_playlist.AnalyzePlaylistDataAccessInterface;

import java.io.File;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/**
 * This class represents the Data Access Object that stores the playlistIDs.
 */

public class AnalyzePlaylistDataAccessObject implements AnalyzePlaylistDataAccessInterface {
    private File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, AnalyzedPlaylist> playlistIDs = new HashMap<>();

    /**
     * Constructs an AnalyzePlaylistDataAccessObject for handling playlist data.
     *
     * @param csvFile The file where playlist data is stored.
     * @throws IOException If there is an error in reading the file.
     */

    public AnalyzePlaylistDataAccessObject(File csvFile) throws IOException {
        this.csvFile = csvFile;
        headers.put("name", 0);
        headers.put("playlist ID", 1);

        if (csvFile.length() == 0) {
            save();
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String playlistID = String.valueOf(col[headers.get("playlist ID")]);
                String name = String.valueOf(col[headers.get("name")]);
                AnalyzedPlaylist analyzedPlaylist = new AnalyzedPlaylist(name, playlistID);
                playlistIDs.put(name, analyzedPlaylist);
            }
        }
    }

    /**
     * Retrieves a map of playlist IDs associated with their names.
     *
     * @return A map where keys are names and values are AnalyzedPlaylist objects.
     */

    @Override
    public Map<String, AnalyzedPlaylist> getPlaylistID() {
        return this.playlistIDs;
    }

    /**
     * Stores a new playlist ID with its associated name.
     *
     * @param name The name associated with the playlist ID.
     * @param playlistID The playlist ID to be stored.
     * @return A set of all names stored in the data access object.
     * @throws IOException If there is an error in writing the file.
     */

    @Override
    public Set<String> savePlaylistID(String name, String playlistID) throws IOException {
        AnalyzedPlaylist analyzedPlaylist = new AnalyzedPlaylist(name, playlistID);
        this.playlistIDs.put(analyzedPlaylist.getName(), analyzedPlaylist);
        this.save();

        return this.playlistIDs.keySet();
    }

    /**
     * Saves the current state of playlist IDs to the CSV file.
     *
     * @throws IOException If there is an error in writing the file.
     */

    public void save() throws IOException {
        BufferedWriter writer;

        writer = new BufferedWriter(new FileWriter(csvFile));
        writer.write(String.join(",", headers.keySet()));
        writer.newLine();

        for (AnalyzedPlaylist analyzedPlaylist : playlistIDs.values()) {
            String line = String.format("%s,%s",
                    analyzedPlaylist.getName(),
                    analyzedPlaylist.getPlaylistID());
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}


