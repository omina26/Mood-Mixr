package use_case.analyze_playlist;

import entity.User;
import use_case.login.LoginDataAccessInterface;
import use_case.login.services.TracksAudioFeaturesAPIHandler;
import use_case.login.services.UserPlaylistItemsAPIHandler;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyzePlaylistInteractor implements AnalyzePlaylistInputBoundary {

    final AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject;
    final AnalyzePlaylistOutputBoundary analyzePlaylistPresenter;
    final LoginDataAccessInterface userDataAccessObject;
    final UserPlaylistItemsAPIHandler playlistItemsHandler;
    final TracksAudioFeaturesAPIHandler trackHandler;


    public AnalyzePlaylistInteractor(LoginDataAccessInterface userDataAccessObject,
                                     AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject,
                                     AnalyzePlaylistOutputBoundary analyzePlaylistPresenter,
                                     UserPlaylistItemsAPIHandler playlistItemsHandler,
                                     TracksAudioFeaturesAPIHandler trackHandler) {
        this.userDataAccessObject = userDataAccessObject;
        this.analyzePlaylistDataAccessObject = analyzePlaylistDataAccessObject;
        this.analyzePlaylistPresenter = analyzePlaylistPresenter;
        this.playlistItemsHandler = playlistItemsHandler;
        this.trackHandler = trackHandler;
    }


    public void execute(AnalyzePlaylistInputData analyzePlaylistInputData) {
        try {
            User user = userDataAccessObject.getCurrentUser();
            String token = user.getToken();
            String playlistID = analyzePlaylistInputData.getPlaylistID();
            List<String> playlistItems = this.playlistItemsHandler.getPlaylistItems(token, playlistID);

            JsonObject tracksAudioFeatures = this.trackHandler.getTracksAudioFeatures(playlistItems, token);
            JsonArray tracksAudioFeaturesArray = tracksAudioFeatures.getJsonArray("audio_features");

            Map<String, Map<String, Object>> trackFeaturesMap = new HashMap<>();
            for (JsonObject trackFeatures : tracksAudioFeaturesArray.getValuesAs(JsonObject.class)) {
                String trackId = trackFeatures.getString("id");
                Map<String, Object> features = new HashMap<>();

                features.put("acousticness", trackFeatures.getJsonNumber("acousticness").doubleValue());
                features.put("danceability", trackFeatures.getJsonNumber("danceability").doubleValue());
                features.put("energy", trackFeatures.getJsonNumber("energy").doubleValue());
                features.put("instrumentalness", trackFeatures.getJsonNumber("instrumentalness").doubleValue());
                features.put("liveness", trackFeatures.getJsonNumber("liveness").doubleValue());
                features.put("speechiness", trackFeatures.getJsonNumber("speechiness").doubleValue());
                features.put("valence", trackFeatures.getJsonNumber("valence").doubleValue());

                trackFeaturesMap.put(trackId, features);
                }
                Map<String, Double> averageFeatures = new HashMap<>();
                int totalTracks = trackFeaturesMap.size();

// Initialize sum map
                for (String featureKey : trackFeaturesMap.values().iterator().next().keySet()) {
                    averageFeatures.put(featureKey, 0.0);
                }

// Sum up all feature values
                for (Map<String, Object> features : trackFeaturesMap.values()) {
                    for (Map.Entry<String, Object> feature : features.entrySet()) {
                        double sum = averageFeatures.get(feature.getKey());
                        sum += (feature.getValue() instanceof Number) ? ((Number) feature.getValue()).doubleValue() : 0.0;
                        averageFeatures.put(feature.getKey(), sum);
                    }
                }

// Calculate averages
                for (Map.Entry<String, Double> entry : averageFeatures.entrySet()) {
                    double average = entry.getValue() / totalTracks;
                    averageFeatures.put(entry.getKey(), average);
                }

// Now 'averageFeatures' contains the average value of each audio feature across all tracks

            analyzePlaylistPresenter.prepareSuccessView();

        } catch (Exception e) {
            analyzePlaylistPresenter.prepareFailView(e.getMessage());
        }
    }
}
