package interface_adapter.analyze_playlist;

public class AnalyzePlaylistState {
    private String playlist = "";

    public AnalyzePlaylistState(AnalyzePlaylistState copy) {playlist = copy.playlist;}

    public AnalyzePlaylistState() {}

    public String getPlaylist() {return playlist;}
}
