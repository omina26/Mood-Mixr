package use_case.login.services;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.List;

public interface TracksAudioFeaturesAPIHandlerInterface {

    JsonObject getTracksAudioFeatures(List<String> trackIds, String accessToken) throws IOException, InterruptedException;

}
