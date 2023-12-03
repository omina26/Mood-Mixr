package interface_adapter.analyze_playlist;

public class AnalyzePlaylistState{

    private String state;

    public AnalyzePlaylistState(String state) {this.state = state;}

    void setState(String state){this.state = state;}

    String getState(){return this.state;}

}

