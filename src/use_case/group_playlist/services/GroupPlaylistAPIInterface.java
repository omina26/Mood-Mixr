package use_case.group_playlist.services;

import entity.Playlist;
import entity.User;

import java.util.ArrayList;

public interface GroupPlaylistAPIInterface {
    ArrayList<Playlist> getPlaylists(User self);
}
