package data_access.analyze_playlist;

import use_case.analyze_playlist.AnalyzePlaylistDataAccessInterface;

import java.io.File;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnalyzePlaylistDataAccessObject implements AnalyzePlaylistDataAccessInterface {
    private File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    public AnalyzePlaylistDataAccessObject(File csvFile) throws IOException {
        this.csvFile = csvFile;
        headers.put("playlist name", 0);
        headers.put("average acousticness", 1);
        headers.put("average danceability", 2);
        headers.put("average energy", 3);
        headers.put("average instrumentalness", 4);
        headers.put("average liveness", 5);
        headers.put("average speechiness", 6);
        headers.put("average valance", 7);

        File cvsFile;
        if (cvsFile.length() == 0){
            save();
            return;
        }


    }

    private void save() {
    }

}
