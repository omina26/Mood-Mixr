package entity;

/**
 * A class to represent the Mood that has been created for music
 */
public class Mood {

    private final String name;
    private double acousticness;
    private double danceability;
    private double energy;
    private double instrumentalness;
    private double liveness;
    private double speechiness;
    private double valence;

    /**
     * A constructor for the Mood class
     * @param name Name for the mood
     * @param acousticness The acousticness level from 0.0 to 1.0
     * @param danceability The danceability level from 0.0 to 1.0
     * @param energy The energy level from 0.0 to 1.0
     * @param instrumentalness The instrumentalness level from 0.0 to 1.0
     * @param liveness The liveness level from 0.0 to 1.0
     * @param speechiness The speechiness level from 0.0 to 1.0
     * @param valence The valence level from 0.0 to 1.0
     */
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

    /**
     * Gets the name attribute of the Mood
     * @return name of the Mood
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the acousticness attribute of the Mood
     * @return acousticness of the Mood
     */
    public double getAcousticness(){
        return this.acousticness;
    }

    /**
     * Gets the danceability attribute of the Mood
     * @return danceability of the Mood
     */
    public double getDanceability(){
        return this.danceability;
    }

    /**
     * Gets the energy attribute of the Mood
     * @return energy of the Mood
     */
    public double getEnergy(){
        return this.energy;
    }

    /**
     * Gets the instrumentalness attribute of the Mood
     * @return instrumentalness of the Mood
     */
    public double getInstrumentalness(){
        return this.instrumentalness;
    }

    /**
     * Gets the liveness attribute of the Mood
     * @return liveness of the Mood
     */
    public double getLiveness(){
        return this.liveness;
    }

    /**
     * Gets the speechiness attribute of the Mood
     * @return speechiness of the Mood
     */
    public double getSpeechiness(){
        return this.speechiness;
    }

    /**
     * Gets the valence attribute of the Mood
     * @return valence of the Mood
     */
    public double getValence(){
        return this.valence;
    }
}
