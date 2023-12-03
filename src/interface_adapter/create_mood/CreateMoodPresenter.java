package interface_adapter.create_mood;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistState;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.view_moods.ViewMoodsState;
import interface_adapter.view_moods.ViewMoodsViewModel;
import use_case.create_mood.CreateMoodOutputBoundary;
import use_case.create_mood.CreateMoodOutputData;

/**
 * The Presenter for the Create Mood use case
 */
public class CreateMoodPresenter implements CreateMoodOutputBoundary{

    private final CreateMoodViewModel createMoodViewModel;
    private final ViewMoodsViewModel viewMoodsViewModel;
    private final CreatePlaylistViewModel createPlaylistViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * The constructor for the Create Mood Presenter object
     * @param createMoodViewModel The view model for the Create Mood View
     * @param viewMoodsViewModel The view model for the View Moods View
     * @param viewManagerModel The view manager to handle the views
     */
    public CreateMoodPresenter(CreateMoodViewModel createMoodViewModel, ViewMoodsViewModel viewMoodsViewModel,
                               CreatePlaylistViewModel createPlaylistViewModel, ViewManagerModel viewManagerModel) {
        this.createMoodViewModel = createMoodViewModel;
        this.viewMoodsViewModel = viewMoodsViewModel;
        this.createPlaylistViewModel = createPlaylistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares a list view for the created moods
     * @param data The output data to display
     */
    @Override
    public void prepareMoodListView(CreateMoodOutputData data) {

        System.out.println("in prepare mood list view");
        ViewMoodsState viewMoodsState = viewMoodsViewModel.getState();
        viewMoodsState.setMoodsList(data.getMoodNames());
        CreatePlaylistState createPlaylistState = createPlaylistViewModel.getState();
        createPlaylistState.setMoodsList(data.getMoodNames());
        this.viewMoodsViewModel.setState(viewMoodsState);
        this.viewMoodsViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(viewMoodsViewModel.getViewName());

        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Triggers the pop up window on failure of the use case
     * @param error The error that caused the failure
     */
    @Override
    public void prepareFailView(String error) {
        CreateMoodState createMoodState = createMoodViewModel.getState();
        createMoodState.setSaveError(error);
        createMoodViewModel.firePropertyChanged();
    }
}
