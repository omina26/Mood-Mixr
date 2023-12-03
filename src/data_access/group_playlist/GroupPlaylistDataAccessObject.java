package data_access.group_playlist;

import entity.Playlist;
import use_case.group_playlist.GroupPlaylistDataAccessInterface;

import java.util.ArrayList;

public class GroupPlaylistDataAccessObject implements GroupPlaylistDataAccessInterface {
    @Override
    public ArrayList<Playlist> getPlaylists() {

        return new ArrayList<>();
    }
}
