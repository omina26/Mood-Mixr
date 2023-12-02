package use_case.create_mood;

import entity.Mood;

import java.io.IOException;

/**
 * Represents the interactor for the Create Mood use case
 */
public class CreateMoodInteractor implements  CreateMoodInputBoundary{

    final MoodDataAccessInterface moodDataAccessObject;
    final CreateMoodOutputBoundary createMoodPresenter;

    /**
     * The constructor for the Create Mood Interactor object
     * @param moodDataAccessObject The Data Access Object that handles stored Moods
     * @param createMoodOutputPresenter The Presenter to handle the output
     */
    public CreateMoodInteractor(MoodDataAccessInterface moodDataAccessObject, CreateMoodOutputBoundary createMoodOutputPresenter){
        this.moodDataAccessObject = moodDataAccessObject;
        this.createMoodPresenter = createMoodOutputPresenter;
    }

    /**
     * Saves the created mood using the input data and sends the correct output data to the presenter
     * @param createMoodInputData The input data for the mood
     */
    @Override
    public void execute(CreateMoodInputData createMoodInputData) {
        String name = createMoodInputData.getName();
        double acousticness = createMoodInputData.getAcousticness()/100.0;
        double danceability = createMoodInputData.getDanceability()/100.0;
        double energy = createMoodInputData.getEnergy()/100.0;
        double instrumentalness = createMoodInputData.getInstrumentalness()/100.0;
        double liveness = createMoodInputData.getLiveness()/100.0;
        double speechiness = createMoodInputData.getSpeechiness()/100.0;
        double valence = createMoodInputData.getValence()/100.0;

        try {
            moodDataAccessObject.saveMood(name, acousticness, danceability, energy, instrumentalness, liveness, speechiness, valence);

            CreateMoodOutputData outputData = new CreateMoodOutputData(moodDataAccessObject.getMoodNames(), false);
            createMoodPresenter.prepareMoodListView(outputData);
        } catch(IOException e) {
            CreateMoodOutputData outputData = new CreateMoodOutputData(null, true);
            createMoodPresenter.prepareFailView("Could not save mood: "+ e);
        }
    }
}
