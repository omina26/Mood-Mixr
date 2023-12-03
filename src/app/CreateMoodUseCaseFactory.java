package app;

import data_access.create_mood.MoodDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_mood.CreateMoodController;
import interface_adapter.create_mood.CreateMoodPresenter;
import interface_adapter.create_mood.CreateMoodViewModel;
import interface_adapter.view_moods.ViewMoodsViewModel;
import use_case.create_mood.CreateMoodInputBoundary;
import use_case.create_mood.CreateMoodInteractor;
import use_case.create_mood.CreateMoodOutputBoundary;
import use_case.create_mood.MoodDataAccessInterface;
import view.CreateMoodView;

import java.io.IOException;

/**
 * This class is a Factory for functions that create aspects of the CreateMood use case
 */
public class CreateMoodUseCaseFactory {

    /**
     * Create the View for the CreateMood Screen
     * @param viewManagerModel The view manager model that handles the views
     * @param createMoodViewModel The view model for the CreateMood View
     * @param viewMoodsViewModel The view model for the ViewMoods view
     * @param moodDataAccessObject The data access object that deals with the Moods stored
     * @return
     */
    public static CreateMoodView create(ViewManagerModel viewManagerModel, CreateMoodViewModel createMoodViewModel,
                                        ViewMoodsViewModel viewMoodsViewModel, MoodDataAccessInterface moodDataAccessObject){

            CreateMoodController createMoodController = createMoodUseCase(viewManagerModel,
                    createMoodViewModel, viewMoodsViewModel, moodDataAccessObject);
            return new CreateMoodView(createMoodViewModel, createMoodController);
    }

    private static CreateMoodController createMoodUseCase(ViewManagerModel viewManagerModel, CreateMoodViewModel createMoodViewModel,
                                                          ViewMoodsViewModel viewMoodsViewModel, MoodDataAccessInterface moodDataAccessObject){
        CreateMoodOutputBoundary createMoodOutputBoundary =
                new CreateMoodPresenter(createMoodViewModel, viewMoodsViewModel, viewManagerModel);

        CreateMoodInputBoundary createMoodInputBoundary = new CreateMoodInteractor(moodDataAccessObject, createMoodOutputBoundary);

        return new CreateMoodController(createMoodInputBoundary);
    }
}
