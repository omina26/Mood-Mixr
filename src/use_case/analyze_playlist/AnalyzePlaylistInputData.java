package use_case.analyze_playlist;

public class AnalyzePlaylistInputData {
    private final String playlist;

    public AnalyzePlaylistInputData(String playlist) {
        this.playlist = playlist;
    }

    String getPlaylist() {return playlist;}
}
