package use_case.analyze_playlist;

import java.util.Set;

public class AnalyzePlaylistOutputData {
    private final Set<String> playlistIDs;
    private final boolean useCaseFailed;

    public AnalyzePlaylistOutputData(Set<String> playlistIDs, boolean useCaseFailed) {
        this.playlistIDs = playlistIDs;

        this.useCaseFailed = useCaseFailed;
    }

    public Set<String> getPlaylistIDs() {
        return this.playlistIDs;
    }
}
