package entity;

public class AnalyzedPlaylist {
    private final String playlistID;
    private final String name;

    public AnalyzedPlaylist(String name, String playlistID) {
        this.name = name;
        this.playlistID = playlistID;
    }

    public String getName() {
        return this.name;
    }
    public String getPlaylistID() {
        return this.playlistID;}

}
