package use_case.group_playlist;

import entity.User;

import java.util.ArrayList;

public class GroupPlaylistOutputData {
    public final ArrayList<String> playlists;
    public final User user;
    public final boolean isUserPlaylistsOnly;
    public final String nonUserPlaylistID;
    public GroupPlaylistOutputData(ArrayList<String> playlists, User user, boolean isUserPlaylistsOnly, String nonUserPlaylistID) {
        this.playlists = playlists;
        this.user = user;
        this.isUserPlaylistsOnly = isUserPlaylistsOnly;
        this.nonUserPlaylistID = nonUserPlaylistID;
    }
}
