package AnalyzePlaylist;

import entity.AnalyzedPlaylist;
import org.junit.Test;
import use_case.analyze_playlist.AnalyzePlaylistInputData;

import static org.junit.Assert.assertEquals;

public class AnalyzePlaylistInputDataTest {
    @Test
    public void TestAnalyzePlaylistInputData(){
        String playlistID = "mockID";

        AnalyzePlaylistInputData data = new AnalyzePlaylistInputData(playlistID);

        assertEquals(data.toString(), playlistID);

    }
}
