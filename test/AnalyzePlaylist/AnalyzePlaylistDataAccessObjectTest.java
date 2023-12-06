package AnalyzePlaylist;

import data_access.analyze_playlist.AnalyzePlaylistDataAccessObject;
import entity.AnalyzedPlaylist;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AnalyzePlaylistDataAccessObjectTest {
    @Test
    public void testAnalyzePlaylistDataAccessObjectEmptyFile() throws IOException {
        File tempFile = new File("playlistIDs.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.print("");
        writer.close();
        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        AnalyzePlaylistDataAccessObject analyzePlaylistDataAccessObject = new AnalyzePlaylistDataAccessObject(tempFile);
        String header = reader.readLine();
        assertEquals(header, "name,playlist ID");
    }

    @Test
    public void testAnalyzePlaylistDataAccessObjectNonEmptyFile() throws IOException {
        File tempFile = new File("playlistIDs.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.println("name,playlist ID");
        writer.print("mockname,mockID");
        writer.close();
        AnalyzePlaylistDataAccessObject analyzePlaylistDataAccessObject = new AnalyzePlaylistDataAccessObject(tempFile);

        HashMap<Object, AnalyzedPlaylist> expected = new HashMap<>();
        expected.put("mockname", new AnalyzedPlaylist("mockname", "mockID"));
        Map<String, AnalyzedPlaylist> actual = analyzePlaylistDataAccessObject.getPlaylistID();

        assertEquals(expected.keySet(), actual.keySet());
        for (Object key : expected.keySet()) {
            assertEquals(expected.get(key).getName(), actual.get(key).getName());
            assertEquals(expected.get(key).getPlaylistID(), actual.get(key).getPlaylistID(), "mockID");
        }

        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        String header = reader.readLine();
        assertEquals(header, "name,playlist ID");
        String nextLine = reader.readLine();
        assertEquals(nextLine, "mockname,mockID");
    }

    @Test
    public void testGetPlaylistID() throws IOException {
        File tempFile = new File("playlistIDs.csv");
        PrintWriter writer = new PrintWriter(tempFile);
        writer.print("name,playlist ID");
        writer.close();

        AnalyzePlaylistDataAccessObject analyzePlaylistDataAccessObject = new AnalyzePlaylistDataAccessObject(tempFile);

        AnalyzedPlaylist mockAnalyzedPlaylist = new AnalyzedPlaylist("mockname", "mockID");


        analyzePlaylistDataAccessObject.savePlaylistID("mockname", "mockID");

        Map<String, AnalyzedPlaylist> actual = analyzePlaylistDataAccessObject.getPlaylistID();
        Map<String, AnalyzedPlaylist> expected = new HashMap<String, AnalyzedPlaylist>();
        expected.put("mockname", mockAnalyzedPlaylist);

        // Ensure the key sets are equal
        assertEquals(expected.keySet(), actual.keySet());

        // Now compare each AnalyzedPlaylist object's fields
        for (String key : expected.keySet()) {
            AnalyzedPlaylist expectedPlaylist = expected.get(key);
            AnalyzedPlaylist actualPlaylist = actual.get(key);

            assertEquals(expectedPlaylist.getName(), actualPlaylist.getName());
            assertEquals(expectedPlaylist.getPlaylistID(), actualPlaylist.getPlaylistID());
        }
    }
}
