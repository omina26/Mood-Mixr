package interface_adapter.group_playlist;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GroupPlaylistState {

    ArrayList<String> playlists;

    public GroupPlaylistState(){
        this.playlists = new ArrayList<>();
    };

    public void setPlaylists(ArrayList<String> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<String> getPlaylists(ArrayList<String> playlists){
        return this.playlists;
    }
}
