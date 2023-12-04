package interface_adapter.analyze_playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.analyzed_playlist.AnalyzedPlaylistState;
import interface_adapter.analyzed_playlist.AnalyzedPlaylistViewModel;
import interface_adapter.create_playlist.CreatePlaylistState;
import interface_adapter.view_moods.ViewMoodsState;
import use_case.analyze_playlist.AnalyzePlaylistOutputBoundary;
import use_case.analyze_playlist.AnalyzePlaylistOutputData;
import view.AnalyzedPlaylistView;

/**
 * Presenter for the Analyze Playlist feature.
 */

public class AnalyzePlaylistPresenter implements AnalyzePlaylistOutputBoundary {
    private final AnalyzePlaylistViewModel analyzePlaylistViewModel;
    private final AnalyzedPlaylistViewModel analyzedPlaylistViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for AnalyzePlaylistPresenter.
     *
     * @param analyzePlaylistViewModel ViewModel associated with the Analyze Playlist feature.
     * @param viewManagerModel Model for managing views.
     */

    public AnalyzePlaylistPresenter(AnalyzePlaylistViewModel analyzePlaylistViewModel,
                                    AnalyzedPlaylistViewModel analyzedPlaylistViewModel,
                                    ViewManagerModel viewManagerModel) {
        this.analyzePlaylistViewModel = analyzePlaylistViewModel;
        this.analyzedPlaylistViewModel = analyzedPlaylistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Updates the view with analyzed playlist data.
     *
     * @param data Analyzed playlist output data.
     */

    @Override
    public void analyzePlaylistView(AnalyzePlaylistOutputData data) {
        System.out.println("in analyze playlist view");
        AnalyzedPlaylistState analyzedPlaylistState = analyzedPlaylistViewModel.getState();
        analyzedPlaylistState.setPlaylistAudioFeaturesList(data.getPlaylistIDs());

        this.analyzedPlaylistViewModel.setState(analyzedPlaylistState);
        this.analyzedPlaylistViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(analyzedPlaylistViewModel.getViewName());

        this.viewManagerModel.firePropertyChanged();

    }

    /**
     * Prepares the view for displaying an error message.
     *
     * @param error The error message to be displayed.
     */
    @Override
    public void prepareFailView(String error) {

    }

    /**
     * Prepares the view for displaying a success message.
     */
    @Override
    public void prepareSuccessView() {

    }
}
