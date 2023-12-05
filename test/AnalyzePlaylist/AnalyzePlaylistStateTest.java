package AnalyzePlaylist;

import interface_adapter.analyze_playlist.AnalyzePlaylistState;
import interface_adapter.create_mood.CreateMoodState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnalyzePlaylistStateTest {
    @Test
    public void TestAnalyzePlaylistStateWithCopy(){
        AnalyzePlaylistState copy = new AnalyzePlaylistState("1");
        AnalyzePlaylistState main = new AnalyzePlaylistState("1");

        assertEquals(main.getPlaylist(), copy.getPlaylist());
        assertEquals(main.getPlaylistID(), copy.getPlaylistID());

    }
}
