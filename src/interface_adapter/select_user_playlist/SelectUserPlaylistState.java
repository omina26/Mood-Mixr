package interface_adapter.select_user_playlist;

import java.util.ArrayList;

public class SelectUserPlaylistState {

    ArrayList<String> allPlaylists;
    SelectUserPlaylistState(){
    }

    public ArrayList<String> getAllPlaylists() {
        return allPlaylists;
    }

    public void setAllPlaylists(ArrayList<String> allPlaylists){
        this.allPlaylists = allPlaylists;
    }
}
