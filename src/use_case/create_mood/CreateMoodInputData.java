package use_case.create_mood;

/**
 * Represents the input data for the Create Mood use case
 */
public class CreateMoodInputData {

    final private String name;
    final private int acousticness;
    final private int danceability;
    final private int energy;
    final private int instrumentalness;
    final private int liveness;
    final private int speechiness;
    final private int valence;

    /**
     * A constructor for the CreateMoodInputData
     * @param name The name entered for the mood
     * @param acousticness The acousticness entered
     * @param danceability The danceability entered
     * @param energy The energy level entered
     * @param instrumentalness The instrumentalness entered
     * @param liveness The liveness entered
     * @param speechiness The speechiness entered
     * @param valence The valence entered
     */
    public CreateMoodInputData(String name, int acousticness, int danceability, int energy, int instrumentalness,
                               int liveness, int speechiness, int valence) {
        this.name = name;
        this.acousticness = acousticness;
        this.danceability = danceability;
        this.energy = energy;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.speechiness = speechiness;
        this.valence = valence;
    }

    /**
     * Gets the inputted mood name
     * @return mood name
     */
    String getName() {
        return this.name;
    }

    /**
     * Gets the inputted mood name
     * @return mood name
     */
    int getAcousticness(){
        return this.acousticness;
    }

    /**
     * Gets the inputted mood danceability
     * @return mood danceability
     */
    int getDanceability(){
        return this.danceability;
    }

    /**
     * Gets the inputted mood energy
     * @return mood energy
     */
    int getEnergy(){
        return this.energy;
    }

    /**
     * Gets the inputted mood instrumentaless
     * @return mood instrumentalness
     */
    int getInstrumentalness(){
        return this.instrumentalness;
    }

    /**
     * Gets the inputted mood liveness
     * @return mood liveness
     */
    int getLiveness(){
        return this.liveness;
    }

    /**
     * Gets the inputted mood speechiness
     * @return mood speechiness
     */
    int getSpeechiness(){
        return this.speechiness;
    }

    /**
     * Gets the inputted mood valence
     * @return mood valence
     */
    int getValence(){
        return this.valence;
    }

    /**
     * Gets the string representation of the object
     * @return the string representation of the object
     */
    public String toString(){
        return this.name + " " + this.acousticness + " " + this.danceability + " " + this.energy + " " + this.instrumentalness
                + " " + this.liveness + " " + this.speechiness + " " + this.valence;
    }
}
