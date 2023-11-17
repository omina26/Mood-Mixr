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

public class CreateMoodUseCaseFactory {

    private CreateMoodUseCaseFactory() {}

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
