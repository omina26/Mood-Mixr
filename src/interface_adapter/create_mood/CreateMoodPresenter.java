package interface_adapter.create_mood;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_moods.ViewMoodsState;
import interface_adapter.view_moods.ViewMoodsViewModel;
import use_case.create_mood.CreateMoodOutputBoundary;
import use_case.create_mood.CreateMoodOutputData;

public class CreateMoodPresenter implements CreateMoodOutputBoundary{

    private final CreateMoodViewModel createMoodViewModel;
    private final ViewMoodsViewModel viewMoodsViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateMoodPresenter(CreateMoodViewModel createMoodViewModel, ViewMoodsViewModel viewMoodsViewModel,
                               ViewManagerModel viewManagerModel) {
        this.createMoodViewModel = createMoodViewModel;
        this.viewMoodsViewModel = viewMoodsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareMoodListView(CreateMoodOutputData data) {
        ViewMoodsState viewMoodsState = viewMoodsViewModel.getState();
        viewMoodsState.setMoods(data.getMoodNames());
        this.viewMoodsViewModel.setState(viewMoodsState);
        this.viewMoodsViewModel.firePropertyChanged();

        this.viewMoodsViewModel.setActiveView(viewMoodsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
