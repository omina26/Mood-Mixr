package use_case.create_playlist;

import entity.Mood;
import entity.User;
import use_case.create_mood.MoodDataAccessInterface;
import use_case.services.*;
import use_case.login.LoginDataAccessInterface;

import java.util.List;
import java.util.Set;

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
            User user = userDataAccessObject.getCurrentUser();
            String username = user.getName();
            String accessToken = user.getToken();
            String seedTracks = topTracksAPIHandler.getUserTopTracks(accessToken);
            String selectedMood = createPlaylistInputData.getSelectedMoodName();
            Mood moodDetails = moodDataAccessObject.getMoods().get(selectedMood);
            double acousticness = moodDetails.getAcousticness();
            double danceability = moodDetails.getDanceability();
            double energy = moodDetails.getEnergy();
            double instrumentalness = moodDetails.getInstrumentalness();
            double liveness = moodDetails.getLiveness();
            double speechiness = moodDetails.getSpeechiness();
            double valence = moodDetails.getValence();
            String recommendations = this.getRecommendationHandler.getRecommendation(accessToken, seedTracks, acousticness, danceability, energy, instrumentalness, liveness, speechiness, valence);
            this.playlistHandler.createPlaylist(accessToken, username, selectedMood + " Playlist", recommendations);
            createPlaylistPresenter.prepareSuccessView();
        } catch (Exception e) {
            createPlaylistPresenter.prepareFailView(e.getMessage());
        }
    }
}
