package use_case.group_playlist;

import entity.User;

import java.util.ArrayList;

public class GroupPlaylistInputData {
    User user = null;
    boolean self_only = false;
    ArrayList<String> playlistsToCombine;
    public GroupPlaylistInputData(User user, Boolean selfOnly) {
        this.user = user;
        self_only = selfOnly;
    }
    public GroupPlaylistInputData(ArrayList<String> selectedPlaylists, boolean selfOnly){
        this.playlistsToCombine = selectedPlaylists;
        this.self_only = selfOnly;
    }


}
