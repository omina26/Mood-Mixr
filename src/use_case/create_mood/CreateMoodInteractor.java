package use_case.create_mood;

import entity.Mood;

import java.io.IOException;

public class CreateMoodInteractor implements  CreateMoodInputBoundary{

    final MoodDataAccessInterface moodDataAccessObject;
    final CreateMoodOutputBoundary createMoodPresenter;

    public CreateMoodInteractor(MoodDataAccessInterface moodDataAccessObject, CreateMoodOutputBoundary createMoodOutputPresenter){
        this.moodDataAccessObject = moodDataAccessObject;
        this.createMoodPresenter = createMoodOutputPresenter;
    }

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
