package data_access.group_playlist;

import use_case.group_playlist.GroupPlaylistDataAccessInterface;

import java.util.ArrayList;

public class GroupPlaylistDataAccessObject implements GroupPlaylistDataAccessInterface {
    @Override
    public ArrayList<String> getPlaylists() {

        return new ArrayList<>();
    }
}
