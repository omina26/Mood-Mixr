package use_case.group_playlist;

import entity.User;

import java.util.ArrayList;

public class GroupPlaylistInputData {
    public User user = null;
    boolean self_only = false;
    public ArrayList<String> playlistsToCombine = new ArrayList<String>();
    public String nonUserPlaylistID = "";
    public GroupPlaylistInputData(User user, Boolean selfOnly, String playlistID) {
        this.user = user;
        self_only = selfOnly;
        this.nonUserPlaylistID = playlistID;
    }
    public GroupPlaylistInputData(ArrayList<String> selectedPlaylists, boolean selfOnly, User user,
                                  String nonUserPlaylist){
        this.user = user;
        this.playlistsToCombine = selectedPlaylists;
        this.self_only = selfOnly;
        this.nonUserPlaylistID = nonUserPlaylist;

    }


}
