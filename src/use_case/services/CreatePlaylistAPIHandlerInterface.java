package use_case.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public interface CreatePlaylistAPIHandlerInterface {
    public void createPlaylist(String accessToken, String username, String playlistName, String recommendations) throws IOException, InterruptedException;
}
