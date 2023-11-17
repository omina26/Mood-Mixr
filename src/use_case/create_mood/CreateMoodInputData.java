package use_case.create_mood;

public class CreateMoodInputData {

    final private String name;
    final private int acousticness;
    final private int danceability;
    final private int energy;
    final private int instrumentalness;
    final private int liveness;
    final private int speechiness;
    final private int valence;

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

    String getName() {
        return this.name;
    }

    int getAcousticness(){
        return this.acousticness;
    }

    int getDanceability(){
        return this.danceability;
    }

    int getEnergy(){
        return this.energy;
    }

    int getInstrumentalness(){
        return this.instrumentalness;
    }

    int getLiveness(){
        return this.liveness;
    }

    int getSpeechiness(){
        return this.speechiness;
    }

    int getValence(){
        return this.valence;
    }
}
