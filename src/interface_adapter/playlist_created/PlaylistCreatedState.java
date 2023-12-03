package interface_adapter.playlist_created;

public class PlaylistCreatedState {
    private String name = "";
    public PlaylistCreatedState(PlaylistCreatedState copy) {name = copy.name;}
    public PlaylistCreatedState() {}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
