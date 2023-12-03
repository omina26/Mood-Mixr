package interface_adapter.create_mood;

/**
 * The State for the Create Mood View
 */
public class CreateMoodState {

    private String name;
    private String saveError = null;
    private int acousticness;
    private int danceability;
    private int energy;
    private int instrumentalness;
    private int liveness;
    private int speechiness;
    private int valence;

    /**
     * A constructor for the CreateMoodState object which sets all the attributes to a default value
     * @param defaultValue The default value to set all the attributes
     */
    public CreateMoodState(int defaultValue) {
        this.acousticness = defaultValue;
        this.danceability = defaultValue;
        this.energy = defaultValue;
        this.instrumentalness = defaultValue;
        this.liveness = defaultValue;
        this.speechiness = defaultValue;
        this.valence = defaultValue;
    }

    /**
     * A constructor for the CreateMoodState object which sets all the attributes to the values entered
     */
    public CreateMoodState(String name, int defaultAcousticness, int defaultDanceability, int defaultEnergy,
                           int defaultInstrumentalness, int defaultLiveness, int defaultSpeechiness, int defaultValence) {
        this.name = name;
        this.acousticness = defaultAcousticness;
        this.danceability = defaultDanceability;
        this.energy = defaultEnergy;
        this.instrumentalness = defaultInstrumentalness;
        this.liveness = defaultLiveness;
        this.speechiness = defaultSpeechiness;
        this.valence = defaultValence;
    }

    /**
     * Gets the name of the Mood in the state of being created
     * @return the name of the Mood in the state of being created
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the save error which happens due to a failure of the use case
     * @return the name of the Mood in the state of being created
     */
    public String getSaveError () {return this.saveError;}

    /**
     * Gets the acousticness set for the Mood in the state of being created
     * @return acousticness set for the Mood in the state of being created
     */
    public int getAcousticness(){
        return this.acousticness;
    }

    /**
     * Gets the danceability set for the Mood in the state of being created
     * @return the danceability set for the Mood in the state of being created
     */
    public int getDanceability(){
        return this.danceability;
    }

    /**
     * Gets the energy set for the Mood in the state of being created
     * @return the energy set for the Mood in the state of being created
     */
    public int getEnergy(){
        return this.energy;
    }

    /**
     * Gets the instrumentalness set for the Mood in the state of being created
     * @return the instrumentalness set for the Mood in the state of being created
     */
    public int getInstrumentalness(){
        return this.instrumentalness;
    }

    /**
     * Gets the liveness set for the Mood in the state of being created
     * @return the liveness set for the Mood in the state of being created
     */
    public int getLiveness(){
        return this.liveness;
    }

    /**
     * Gets the speechiness set for the Mood in the state of being created
     * @return the speechiness set for the Mood in the state of being created
     */
    public int getSpeechiness(){
        return this.speechiness;
    }

    /**
     * Gets the valence set for the Mood in the state of being created
     * @return the valence set for the Mood in the state of being created
     */
    public int getValence(){
        return this.valence;
    }

    /**
     * Sets the name for the Mood in the state of being created
     * @param name the name for the Mood in the state of being created
     */
    public void setName(String name){
        this.name = name;
    }


    public void setSaveError(String saveError) { this.saveError = saveError; }

    /**
     * Sets the acousticness for the Mood in the state of being created
     * @param acousticness the name for the Mood in the state of being created
     */
    public void setAcousticness(int acousticness){
        this.acousticness = acousticness;
    }

    /**
     * Sets the danceability for the Mood in the state of being created
     * @param danceability the danceability for the Mood in the state of being created
     */
    public void setDanceability(int danceability){
        this.danceability = danceability;
    }

    /**
     * Sets the energy for the Mood in the state of being created
     * @param energy the energy for the Mood in the state of being created
     */
    public void setEnergy(int energy){
        this.energy = energy;
    }

    /**
     * Sets the instrumentalness for the Mood in the state of being created
     * @param instrumentalness the instrumentalness for the Mood in the state of being created
     */
    public void setInstrumentalness(int instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    /**
     * Sets the liveness for the Mood in the state of being created
     * @param liveness the liveness for the Mood in the state of being created
     */
    public void setLiveness(int liveness){
        this.liveness = liveness;
    }

    /**
     * Sets the speechiness for the Mood in the state of being created
     * @param speechiness the speechiness for the Mood in the state of being created
     */
    public void setSpeechiness(int speechiness){
        this.speechiness = speechiness;
    }

    /**
     * Sets the valence for the Mood in the state of being created
     * @param valence the valence for the Mood in the state of being created
     */
    public void setValence(int valence){
        this.valence = valence;
    }
}
