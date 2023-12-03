package use_case.analyze_playlist;

/**
 * Input data for analyzing a playlist.
 */

public class AnalyzePlaylistInputData {
    final private String playlistID;

    /**
     * Constructor for AnalyzePlaylistInputData.
     *
     * @param playlistID The ID of the playlist to be analyzed.
     */
    public AnalyzePlaylistInputData(String playlistID) {
        this.playlistID = playlistID;
    }

    /**
     * Gets the playlist ID.
     *
     * @return The ID of the playlist.
     */
    String getPlaylistID() {return playlistID;}

    /**
     * Returns a string representation of the playlist ID.
     *
     * @return The playlist ID as a string.
     */
    public String toString(){
        return this.playlistID;
    }
}
