package entity;

public class Mood {

    private final String name;
    private double acousticness;
    private double danceability;
    private double energy;
    private double instrumentalness;
    private double liveness;
    private double speechiness;
    private double valence;

    public Mood(String name, double acousticness, double danceability, double energy, double instrumentalness, double liveness,
                double speechiness, double valence){
        this.name = name;
        this.acousticness = acousticness;
        this.danceability = danceability;
        this.energy = energy;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.speechiness = speechiness;
        this.valence = valence;
    }

    public String getName(){
        return this.name;
    }

    public double getAcousticness(){
        return this.acousticness;
    }

    public double getDanceability(){
        return this.danceability;
    }

    public double getEnergy(){
        return this.energy;
    }

    public double getInstrumentalness(){
        return this.instrumentalness;
    }

    public double getLiveness(){
        return this.liveness;
    }

    public double getSpeechiness(){
        return this.speechiness;
    }

    public double getValence(){
        return this.valence;
    }
}
