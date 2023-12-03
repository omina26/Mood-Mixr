package interface_adapter.analyze_playlist;

import interface_adapter.ViewManagerModel;
import use_case.analyze_playlist.AnalyzePlaylistOutputBoundary;
import use_case.analyze_playlist.AnalyzePlaylistOutputData;

/**
 * Presenter for the Analyze Playlist feature.
 */

public class AnalyzePlaylistPresenter implements AnalyzePlaylistOutputBoundary {
    private final AnalyzePlaylistViewModel analyzePlaylistViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for AnalyzePlaylistPresenter.
     *
     * @param analyzePlaylistViewModel ViewModel associated with the Analyze Playlist feature.
     * @param viewManagerModel Model for managing views.
     */

    public AnalyzePlaylistPresenter(AnalyzePlaylistViewModel analyzePlaylistViewModel,
                                    ViewManagerModel viewManagerModel) {
        this.analyzePlaylistViewModel = analyzePlaylistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Updates the view with analyzed playlist data.
     *
     * @param data Analyzed playlist output data.
     */

    @Override
    public void analyzePlaylistView(AnalyzePlaylistOutputData data) {

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
