package use_case.services;

import java.io.IOException;
import java.util.List;

public interface UserTopTracksAPIHandlerInterface {

    String getUserTopTracks(String accessToken) throws IOException, InterruptedException;

}
