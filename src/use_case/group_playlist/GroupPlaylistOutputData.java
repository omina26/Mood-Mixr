package use_case.group_playlist;

import entity.User;

import java.util.ArrayList;

public class GroupPlaylistOutputData {
    public final ArrayList<String> playlists;
    public final User user;
    public final boolean isUserPlaylistsOnly;
    public boolean success;
    public final String playlistName;
    public final String nonUserPlaylistID;
    public GroupPlaylistOutputData(ArrayList<String> playlists, User user, boolean isUserPlaylistsOnly, String nonUserPlaylistID) {
        this.playlists = playlists;
        this.user = user;
        this.isUserPlaylistsOnly = isUserPlaylistsOnly;
        this.nonUserPlaylistID = nonUserPlaylistID;
        this.playlistName = "";
    }
    public GroupPlaylistOutputData(Boolean bool, String playlistName) {
        this.success = bool;
        this.playlistName = playlistName;
        nonUserPlaylistID = null;
        this.playlists = null;
        this.isUserPlaylistsOnly = false;
        this.user = null;
    }

    public GroupPlaylistOutputData(ArrayList<String> names){
        this.playlists = names;
        this.playlistName = null;
        nonUserPlaylistID = null;
        this.isUserPlaylistsOnly = false;
        this.user = null;
        this.success = false;
    }
}
