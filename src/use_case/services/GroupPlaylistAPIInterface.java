package use_case.services;

import entity.User;

import java.io.IOException;
import java.util.ArrayList;

public interface GroupPlaylistAPIInterface {
    ArrayList<ArrayList<String>> getPlaylists(User self) throws IOException;

    ArrayList<String> getTracks(ArrayList<String> playlistsToCombine, User user);

    Boolean combinePlaylists(ArrayList<String> playlistsToCombine, User user);

    Boolean isValidPlaylist(String playlistID, User user);
}
