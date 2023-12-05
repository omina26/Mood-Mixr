package AnalyzePlaylist;

import interface_adapter.analyze_playlist.AnalyzePlaylistController;
import org.junit.Test;
import use_case.analyze_playlist.AnalyzePlaylistInputBoundary;
import use_case.analyze_playlist.AnalyzePlaylistInputData;

import static org.junit.Assert.assertEquals;

public class AnalyzePlaylistControllerTest {
    @Test
    public void testExecute(){
        MockAnalyzePlaylistInteractor mockInteractor = new MockAnalyzePlaylistInteractor();

        AnalyzePlaylistController controller = new AnalyzePlaylistController(mockInteractor);
        controller.execute("mock");


        assertEquals(mockInteractor.data, "mock");
    }
}

class MockAnalyzePlaylistInteractor implements AnalyzePlaylistInputBoundary{
    String data;

    @Override
    public void execute(AnalyzePlaylistInputData analyzePlaylistInputData) {
        this.data = analyzePlaylistInputData.getPlaylistID();
    }
}