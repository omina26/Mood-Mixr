package use_case.analyze_playlist;

public class AnalyzePlaylistInputData {
    final private String playlistID;

    public AnalyzePlaylistInputData(String playlistID) {
        this.playlistID = playlistID;
    }

    String getPlaylistID() {return playlistID;}

    public String toString(){
        return this.playlistID;
    }
}
