package interface_adapter.create_mood;

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

    public CreateMoodState(int defaultValue) {
        this.acousticness = defaultValue;
        this.danceability = defaultValue;
        this.energy = defaultValue;
        this.instrumentalness = defaultValue;
        this.liveness = defaultValue;
        this.speechiness = defaultValue;
        this.valence = defaultValue;
    }

    public CreateMoodState(CreateMoodState copy) {
        this.name = copy.name;
        this.acousticness = copy.acousticness;
        this.danceability = copy.danceability;
        this.energy = copy.energy;
        this.instrumentalness = copy.instrumentalness;
        this.liveness = copy.liveness;
        this.speechiness = copy.speechiness;
        this.valence = copy.valence;
        this.saveError = copy.saveError;
    }

    public String getName() {
        return this.name;
    }

    public String getSaveError () {return this.saveError;}
    public int getAcousticness(){
        return this.acousticness;
    }

    public int getDanceability(){
        return this.danceability;
    }

    public int getEnergy(){
        return this.energy;
    }

    public int getInstrumentalness(){
        return this.instrumentalness;
    }

    public int getLiveness(){
        return this.liveness;
    }

    public int getSpeechiness(){
        return this.speechiness;
    }

    public int getValence(){
        return this.valence;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSaveError(String saveError) { this.saveError = saveError; }

    public void setAcousticness(int acousticness){
        this.acousticness = acousticness;
    }
    public void setDanceability(int danceability){
        this.danceability = danceability;
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }

    public void setInstrumentalness(int instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public void setLiveness(int liveness){
        this.liveness = liveness;
    }

    public void setSpeechiness(int speechiness){
        this.speechiness = speechiness;
    }

    public void setValence(int valence){
        this.valence = valence;
    }
}
