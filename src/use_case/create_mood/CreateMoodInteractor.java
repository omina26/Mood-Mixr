package use_case.create_mood;

import entity.Mood;

import java.io.IOException;
import java.util.HashSet;

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

            HashSet<String> outputMoodNames = new HashSet<>();
            for (String moodName : moodDataAccessObject.getMoods().keySet()){
                Mood m = moodDataAccessObject.getMoods().get(moodName);
                outputMoodNames.add(moodName + " " + m.getAcousticness() +
                        " "+ m.getDanceability() +
                        " "+ m.getEnergy() +
                        " "+ m.getInstrumentalness() +
                        " "+ m.getLiveness() +
                        " "+ m.getSpeechiness() +
                        " " + m.getValence()
                );
            }
            CreateMoodOutputData outputData = new CreateMoodOutputData(outputMoodNames, false);
            createMoodPresenter.prepareMoodListView(outputData);
        } catch(IOException e) {
            createMoodPresenter.prepareFailView("Could not save mood: "+ e);
        }
    }
}
