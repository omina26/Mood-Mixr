package use_case.create_playlist;

import entity.Mood;
import entity.User;
import use_case.create_mood.MoodDataAccessInterface;
import use_case.services.*;
import use_case.login.LoginDataAccessInterface;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class CreatePlaylistInteractor implements CreatePlaylistInputBoundary{
    final LoginDataAccessInterface userDataAccessObject;
    final MoodDataAccessInterface moodDataAccessObject;
    final CreatePlaylistOutputBoundary createPlaylistPresenter;
    final UserTopTracksAPIHandlerInterface topTracksAPIHandler;
    final GetRecommendationAPIHandlerInterface getRecommendationHandler;
    final CreatePlaylistAPIHandlerInterface playlistHandler;

    /**
     * The constructor for the Create Mood Interactor object
     * @param userDataAccessObject The Data Access Object that handles stored Users
     * @param moodDataAccessObject The Data Access Object that handles stored Moods
     * @param createPlaylistPresenter The Presenter to handle the output
     * @param topTracksAPIHandler The Handler for retrieving the user's top tracks
     * @param getRecommendationHandler The Handler for retrieving spotify recommendations
     * @param playlistHandler The Handler for creating a playlist and adding tracks to it
     */
    public CreatePlaylistInteractor(LoginDataAccessInterface userDataAccessObject,
                                    MoodDataAccessInterface moodDataAccessObject,
                                    CreatePlaylistOutputBoundary createPlaylistPresenter,
                                    UserTopTracksAPIHandlerInterface topTracksAPIHandler,
                                    GetRecommendationAPIHandlerInterface getRecommendationHandler,
                                    CreatePlaylistAPIHandlerInterface playlistHandler) {
        this.userDataAccessObject = userDataAccessObject;
        this.moodDataAccessObject = moodDataAccessObject;
        this.createPlaylistPresenter = createPlaylistPresenter;
        this.topTracksAPIHandler = topTracksAPIHandler;
        this.getRecommendationHandler = getRecommendationHandler;
        this.playlistHandler = playlistHandler;
    }

    /**
     * Executes the creation of a playlist.
     *
     * @param createPlaylistInputData The input data for creating the playlist.
     */
    public void execute(CreatePlaylistInputData createPlaylistInputData){
        try {
            // Get current user and their username and access token
            User user = userDataAccessObject.getCurrentUser();
            String username = user.getName();
            String accessToken = user.getToken();
            // Get user's top tracks
            List<String> seedTracks = topTracksAPIHandler.getUserTopTracks(accessToken);
            // Extract track ids from handler response
            List<String> cleanedSeedTracks = seedTracks.stream()
                                                        .map(id -> id.replace("spotify:track:", ""))
                                                        .collect(Collectors.toList());
            // Get user's selected mood and mood details
            String selectedMood = createPlaylistInputData.getSelectedMoodName();
            Mood moodDetails = moodDataAccessObject.getMoods().get(selectedMood);
            double acousticness = moodDetails.getAcousticness();
            double danceability = moodDetails.getDanceability();
            double energy = moodDetails.getEnergy();
            double instrumentalness = moodDetails.getInstrumentalness();
            double liveness = moodDetails.getLiveness();
            double speechiness = moodDetails.getSpeechiness();
            double valence = moodDetails.getValence();
            // Get track recommendations from Spotify
            List<String> recommendations = this.getRecommendationHandler.getRecommendation(accessToken, cleanedSeedTracks, acousticness, danceability, energy, instrumentalness, liveness, speechiness, valence);
            // Create playlist and add recommended tracks to it
            this.playlistHandler.createPlaylist(accessToken, username, selectedMood + " Playlist", recommendations);
            // Playlist successfully created and can be found on user's Spotify
            createPlaylistPresenter.prepareSuccessView();
        } catch (Exception e) {
            createPlaylistPresenter.prepareFailView(e.getMessage());
        }
    }
}
