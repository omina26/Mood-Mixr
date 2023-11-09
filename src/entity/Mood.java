package entity;

public class Mood {

    public final String name;
    public double acousticness;
    public double danceability;
    public double energy;
    public double instrumentalness;
    public double liveness;
    public double speechiness;
    public double valence;

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

}
