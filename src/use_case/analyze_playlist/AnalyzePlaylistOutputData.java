package use_case.analyze_playlist;

import java.util.Set;

/**
 * Output data for the Analyze Playlist use case.
 */

public class AnalyzePlaylistOutputData {
    private final Set<String> playlistIDs;
    private final boolean useCaseFailed;

    /**
     * Constructor for AnalyzePlaylistOutputData.
     *
     * @param playlistIDs The set of playlist IDs.
     * @param useCaseFailed Indicates if the use case failed.
     */
    public AnalyzePlaylistOutputData(Set<String> playlistIDs, boolean useCaseFailed) {
        this.playlistIDs = playlistIDs;

        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the playlist IDs.
     *
     * @return A set of playlist IDs.
     */
    public Set<String> getPlaylistIDs() {
        return this.playlistIDs;
    }
}
