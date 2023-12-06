package AnalyzePlaylist;

import interface_adapter.ViewManagerModel;
import interface_adapter.analyze_playlist.AnalyzePlaylistPresenter;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import interface_adapter.analyzed_playlist.AnalyzedPlaylistViewModel;
import interface_adapter.create_mood.CreateMoodViewModel;
import org.junit.Test;
import use_case.analyze_playlist.AnalyzePlaylistOutputData;

import java.util.HashMap;
import java.util.HashSet;
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

        AnalyzedPlaylistViewModel mockAnalyzedPlaylistViewModel = new AnalyzedPlaylistViewModel();
        AnalyzePlaylistViewModel mockAnalyzePlaylistViewModel = new AnalyzePlaylistViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();
        CreateMoodViewModel mockCreateMoodViewModel = new CreateMoodViewModel();

        AnalyzePlaylistPresenter presenter = new AnalyzePlaylistPresenter(mockAnalyzePlaylistViewModel, mockAnalyzedPlaylistViewModel,
                mockViewManagerModel,mockCreateMoodViewModel);


        presenter.prepareAnalyzedPlaylistView(data);

        assertTrue(playlistIDs.contains("mockID"));
       assertEquals(mockViewManagerModel.getActiveView(), mockAnalyzedPlaylistViewModel.getViewName());

    }
}
