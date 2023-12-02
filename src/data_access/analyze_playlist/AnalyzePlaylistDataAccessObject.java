package data_access.analyze_playlist;

import entity.AnalyzedPlaylist;
import use_case.analyze_playlist.AnalyzePlaylistDataAccessInterface;

import java.io.File;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AnalyzePlaylistDataAccessObject implements AnalyzePlaylistDataAccessInterface {
    private File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, AnalyzedPlaylist> playlistIDs = new HashMap<>();

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
                AnalyzedPlaylist analyzedPlaylist = new AnalyzedPlaylist(playlistID);
                playlistIDs.put(name, analyzedPlaylist);
            }
        }
    }

    @Override
    public Map<String, AnalyzedPlaylist> getPlaylistID() {
        return this.playlistIDs;
    }

    @Override
    public Set<String> savePlaylistID(String playlistID) {
        return this.playlistIDs.keySet();
    }

    @Override
    public void save() throws IOException {
        BufferedWriter writer;

        writer = new BufferedWriter(new FileWriter(csvFile));
        writer.write(String.join(",", headers.keySet()));
        writer.newLine();

        for (AnalyzedPlaylist analyzedPlaylist : playlistIDs.values()) {
            String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                    analyzedPlaylist.getPlaylistID());
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}


