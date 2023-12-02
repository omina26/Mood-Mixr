package use_case.analyze_playlist;

public interface AnalyzePlaylistOutputBoundary {
    void analyzePlaylistView(AnalyzePlaylistOutputData data);
    void prepareFailView(String error);

    void prepareSuccessView();
}
