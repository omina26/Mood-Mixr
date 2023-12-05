package use_case.analyze_playlist;

import data_access.create_mood.MoodDataAccessObject;
import entity.User;
import interface_adapter.create_mood.CreateMoodState;
import use_case.login.LoginDataAccessInterface;
import use_case.services.TracksAudioFeaturesAPIHandler;
import use_case.services.UserPlaylistItemsAPIHandler;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Interactor for analyzing playlists.
 */

public class AnalyzePlaylistInteractor implements AnalyzePlaylistInputBoundary {

    final AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject;
    final AnalyzePlaylistOutputBoundary analyzePlaylistPresenter;
    final LoginDataAccessInterface userDataAccessObject;
    final UserPlaylistItemsAPIHandler playlistItemsHandler;
    final TracksAudioFeaturesAPIHandler trackHandler;

    /**
     * Constructor for AnalyzePlaylistInteractor.
     *
     * @param userDataAccessObject Data access object for user data.
     * @param analyzePlaylistDataAccessObject Data access object for analyzing playlists.
     * @param analyzePlaylistPresenter Presenter for the output of playlist analysis.
     * @param playlistItemsHandler Handler for retrieving playlist items.
     * @param trackHandler Handler for retrieving track audio features.
     */

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

    /**
     * Executes the analysis of a playlist.
     *
     * @param analyzePlaylistInputData The input data for analyzing the playlist.
     */
    public void execute(AnalyzePlaylistInputData analyzePlaylistInputData) {
        try {
            User user = userDataAccessObject.getCurrentUser();
            String token = user.getToken();
            String playlistID = analyzePlaylistInputData.getPlaylistID();
            String userName = user.getName();

            analyzePlaylistDataAccessObject.savePlaylistID(userName, playlistID);

            List<String> playlistItems = this.playlistItemsHandler.getPlaylistItems(token, playlistID);

            // Extract track IDs from the Spotify URIs
            List<String> trackIDs = playlistItems.stream()
                    .map(item -> item.split(":")[2])
                    .collect(Collectors.toList());

            JsonObject tracksAudioFeatures = this.trackHandler.getTracksAudioFeatures(trackIDs, token);
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

            //analyzePlaylistPresenter.prepareSuccessView();
            Set<String> trackIDsSet = new HashSet<>(trackIDs);
            AnalyzePlaylistOutputData outputData = new AnalyzePlaylistOutputData(trackIDsSet, averageFeatures, false);
            analyzePlaylistPresenter.prepareAnalyzedPlaylistView(outputData);

            // Normalize the values to the expected range for CreateMoodState
            int acousticness1 = (int) Math.round(averageFeatures.get("acousticness") * 100);
            int danceability1 = (int) Math.round(averageFeatures.get("danceability") * 100);
            int energy1 = (int) Math.round(averageFeatures.get("energy") * 100);
            int instrumentalness1 = (int) Math.round(averageFeatures.get("instrumentalness") * 100);
            int liveness1 = (int) Math.round(averageFeatures.get("liveness") * 100);
            int speechiness1 = (int) Math.round(averageFeatures.get("speechiness") * 100);
            int valence1 = (int) Math.round(averageFeatures.get("valence") * 100);

            // Create a new mood with these features
            String moodName = "AnalyzedPlaylistMood"; // You might want to give a meaningful name
            CreateMoodState mood = new CreateMoodState(moodName, acousticness1, danceability1, energy1, instrumentalness1, liveness1, speechiness1, valence1);

            // Extract values from moodState
            String name = mood.getName();
            double acousticness = mood.getAcousticness()/100.0;
            double danceability = mood.getDanceability()/100.0;
            double energy = mood.getEnergy()/100.0;
            double instrumentalness = mood.getInstrumentalness()/100.0;
            double liveness = mood.getLiveness()/100.0;
            double speechiness = mood.getSpeechiness()/100.0;
            double valence = mood.getValence()/100.0;

            // Save the mood using MoodDataAccessObject
            MoodDataAccessObject moodDAO = new MoodDataAccessObject(new File("./moods.csv"));
            moodDAO.saveMood(name, acousticness, danceability, energy, instrumentalness, liveness, speechiness, valence);
        } catch (Exception e) {
            analyzePlaylistPresenter.prepareFailView(e.getMessage());
        }
    }
}
