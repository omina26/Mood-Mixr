package AnalyzePlaylist;

import interface_adapter.ViewManagerModel;
import interface_adapter.analyze_playlist.AnalyzePlaylistPresenter;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import interface_adapter.analyzed_playlist.AnalyzedPlaylistViewModel;
import org.junit.Test;
import use_case.analyze_playlist.AnalyzePlaylistOutputData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnalyzePlaylistPresenterTest {
    @Test
    public void testPrepareMoodListView() {
        HashSet<String> playlistIDs = new HashSet<>();
        HashMap<String, Double> averageFeatures = new HashMap<>();
        playlistIDs.add("mockID");
        averageFeatures.put("string", 1.0);
        AnalyzePlaylistOutputData data = new AnalyzePlaylistOutputData(playlistIDs, averageFeatures, false);

        AnalyzePlaylistViewModel analyzePlaylistViewModel = new AnalyzePlaylistViewModel();
        AnalyzedPlaylistViewModel mockAnalyzedPlaylistViewModel = new AnalyzedPlaylistViewModel();
        AnalyzePlaylistViewModel mockAnalyzePlaylistViewModel = new AnalyzePlaylistViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        AnalyzePlaylistPresenter presenter = new AnalyzePlaylistPresenter(mockAnalyzePlaylistViewModel, mockAnalyzedPlaylistViewModel,
                mockViewManagerModel);

        presenter.prepareAnalyzedPlaylistView(data);

        String actualPlaylistID = mockAnalyzePlaylistViewModel.getState().getPlaylistID();
        assertTrue(playlistIDs.contains(actualPlaylistID));

        assertEquals(mockViewManagerModel.getActiveView(), mockAnalyzePlaylistViewModel.getViewName());

    }
}
