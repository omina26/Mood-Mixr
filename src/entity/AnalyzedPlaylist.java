package entity;

/**
 * A class to represent the Analyzed Playlist that has been created
 */
public class AnalyzedPlaylist {
    private final String playlistID;
    private final String name;

    /**
     * Constructs an AnalyzedPlaylist object with a specific name and playlistID.
     *
     * @param name The name of the playlist.
     * @param playlistID The unique identifier for the playlist.
     */
    public AnalyzedPlaylist(String name, String playlistID) {
        this.name = name;
        this.playlistID = playlistID;
    }

    /**
     * Retrieves the name of the playlist.
     *
     * @return The name of the playlist.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the unique identifier of the playlist.
     *
     * @return The playlist ID.
     */
    public String getPlaylistID() {
        return this.playlistID;}

}
