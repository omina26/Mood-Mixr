package use_case.login.services;

import java.io.IOException;
import java.util.List;

public interface UserTopTracksAPIHandlerInterface {

    List<String> getUserTopTracks(String accessToken) throws IOException, InterruptedException;
}
