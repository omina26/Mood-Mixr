package interface_adapter.analyze_playlist;

/**
 * Represents the state of an analyzed playlist.
 */

public class AnalyzePlaylistState {
    private String playlist = "";
    private String saveError = null;
    private String playlistID;

    /**
     * Gets any error that occurred during saving.
     *
     * @return The save error, if any.
     */

    public String getSaveError () {return this.saveError;}

    /**
     * Copy constructor for AnalyzePlaylistState.
     *
     * @param copy The AnalyzePlaylistState object to copy.
     */
    public AnalyzePlaylistState(AnalyzePlaylistState copy) {playlist = copy.playlist;}

    /**
     * Constructor for AnalyzePlaylistState with a default playlist.
     *
     * @param defaultList The default playlist.
     */
    public AnalyzePlaylistState(String defaultList) {this.playlist = defaultList;}

    /**
     * Gets the playlist.
     *
     * @return The playlist.
     */
    public String getPlaylist() {return playlist;}

    /**
     * Sets the playlist ID.
     *
     * @param playlistID The ID of the playlist.
     */
    public void setPlaylist(String playlistID) {
        this.playlistID = playlistID;
    }

    /**
     * Gets the playlist ID.
     *
     * @return The playlist ID.
     */
    public String getPlaylistID() {return this.playlistID;
    }
}
