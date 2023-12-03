package interface_adapter.analyze_playlist;

public class AnalyzePlaylistState {
    private String playlist = "";
    private String saveError = null;
    private String playlistID;

    public String getSaveError () {return this.saveError;}


    public AnalyzePlaylistState(AnalyzePlaylistState copy) {playlist = copy.playlist;}

    public AnalyzePlaylistState(String defaultList) {this.playlist = defaultList;}

    public String getPlaylist() {return playlist;}

    public void setPlaylist(String playlistID) {
        this.playlistID = playlistID;
    }

    public String getPlaylistID() {return this.playlistID;
    }
}
