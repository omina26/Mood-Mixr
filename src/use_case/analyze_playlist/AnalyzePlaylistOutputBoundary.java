package use_case.analyze_playlist;

public interface AnalyzePlaylistOutputBoundary {
    void prepareAnalyzedPlaylistView(AnalyzePlaylistOutputData data);
    void prepareFailView(String error);
}
