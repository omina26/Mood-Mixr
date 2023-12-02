package use_case.login.services;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.List;

public interface TracksAudioFeaturesAPIHandlerInterface {
    /**
     * Retrieves audio features for a list of tracks.
     *
     * @param trackIds A list of Spotify track IDs.
     * @param accessToken Spotify API access token.
     * @return A JsonObject containing the audio features of the tracks.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */

    JsonObject getTracksAudioFeatures(List<String> trackIDs, String accessToken) throws IOException, InterruptedException;

}
