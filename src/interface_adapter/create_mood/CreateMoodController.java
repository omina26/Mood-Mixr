package interface_adapter.create_mood;

import use_case.create_mood.CreateMoodInputBoundary;
import use_case.create_mood.CreateMoodInputData;

/**
 * A controller class for the Create Mood use case
 */
public class CreateMoodController {

    private final CreateMoodInputBoundary createMoodInteractor;

    /**
     * Constructor for the Create Mood Controller object
     * @param createMoodInteractor The interactor to execute the use case
     */
    public CreateMoodController(CreateMoodInputBoundary createMoodInteractor) {
        this.createMoodInteractor = createMoodInteractor;
    }

    /**
     * Triggers the interactor to create a mood with the following attributes and proceed with the use case
     * @param name The name of the mood to create
     * @param acousticness The selected acousticness level from 0 to 100
     * @param danceability The selected danceability level from 0 to 100
     * @param energy The selected energy level from 0 to 100
     * @param instrumentalness The selected instrumentalness level from 0 to 100
     * @param liveness The selected liveness level from 0 to 100
     * @param speechiness The selected speechiness level from 0 to 100
     * @param valence The selected valence level from 0 to 100
     */
    public void execute(String name, int acousticness, int danceability, int energy, int instrumentalness,
        int liveness, int speechiness, int valence) {

        CreateMoodInputData data = new CreateMoodInputData(name, acousticness, danceability, energy, instrumentalness,
                liveness, speechiness, valence);
        this.createMoodInteractor.execute(data);
    }
}
