package use_case.login.services;

import java.io.IOException;
import java.util.List;

public interface UserPlaylistItemsAPIHandlerInterface {
    List<String> getPlaylistItems(String accessToken, String playlistID) throws IOException, InterruptedException;
}
