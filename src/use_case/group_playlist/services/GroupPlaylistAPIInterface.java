package use_case.group_playlist.services;

import entity.User;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;

public interface GroupPlaylistAPIInterface {
    ArrayList<String> getPlaylists(User self) throws IOException;

    boolean combinePlaylists(ArrayList<String> playlistsToCombine, User user);
}
