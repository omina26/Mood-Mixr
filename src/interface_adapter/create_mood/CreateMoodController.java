package interface_adapter.create_mood;

import use_case.create_mood.CreateMoodInputBoundary;
import use_case.create_mood.CreateMoodInputData;

public class CreateMoodController {

    final CreateMoodInputBoundary createMoodInteractor;

    public CreateMoodController(CreateMoodInputBoundary createMoodInteractor) {
        this.createMoodInteractor = createMoodInteractor;
    }
    public void execute(String name, int acousticness, int danceability, int energy, int instrumentalness,
        int liveness, int speechiness, int valence) {

        CreateMoodInputData data = new CreateMoodInputData(name, acousticness, danceability, energy, instrumentalness,
                liveness, speechiness, valence);
        this.createMoodInteractor.execute(data);
    }
}
