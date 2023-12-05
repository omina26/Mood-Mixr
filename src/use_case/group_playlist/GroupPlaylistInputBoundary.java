package use_case.group_playlist;

import entity.Playlist;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;

public interface GroupPlaylistInputBoundary {
    public void execute();

    public ArrayList<Playlist> getPlaylists(User self) throws IOException;


}
