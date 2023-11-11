package use_case.create_mood;

import entity.Mood;

public class CreateMoodInteractor implements  CreateMoodInputBoundary{

    final MoodDataAccessInterface moodDataAccessObject;
    final CreateMoodOutputBoundary createMoodOutputPresenter;

    public CreateMoodInteractor(MoodDataAccessInterface moodDataAccessObject, CreateMoodOutputBoundary createMoodOutputPresenter){
        this.moodDataAccessObject = moodDataAccessObject;
        this.createMoodOutputPresenter = createMoodOutputPresenter;
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

        moodDataAccessObject.saveMood(name, acousticness, danceability, energy, instrumentalness, liveness, speechiness, valence);
    }
}
