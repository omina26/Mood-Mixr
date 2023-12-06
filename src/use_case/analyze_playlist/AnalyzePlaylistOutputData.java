package use_case.analyze_playlist;

import java.util.Map;
import java.util.Set;

/**
 * Output data for the Analyze Playlist use case.
 */

public class AnalyzePlaylistOutputData {
    private final Set<String> playlistIDs;
    private final Map<String, Double> averageFeatures;
    private final boolean useCaseFailed;

    /**
     * Constructor for AnalyzePlaylistOutputData.
     *
     * @param playlistIDs The set of playlist IDs.
     * @param useCaseFailed Indicates if the use case failed.
     */
    public AnalyzePlaylistOutputData(Set<String> playlistIDs, Map<String, Double> averageFeatures, boolean useCaseFailed) {
        this.playlistIDs = playlistIDs;
        this.averageFeatures = averageFeatures;

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

    public Map<String, Double> getAverageFeatures(){
        return this.averageFeatures;
    }
}

