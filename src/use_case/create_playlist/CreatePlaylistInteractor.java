package use_case.create_playlist;

import data_access.login.UserDataAccessObject;
import entity.Mood;
import entity.User;
import use_case.create_mood.MoodDataAccessInterface;
import use_case.create_playlist.services.CreatePlaylistAPIHandler;
import use_case.create_playlist.services.GetRecommendationAPIHandler;
import use_case.login.LoginDataAccessInterface;

import java.util.Set;

public class CreatePlaylistInteractor implements CreatePlaylistInputBoundary{
    final LoginDataAccessInterface userDataAccessObject;
    final MoodDataAccessInterface moodDataAccessObject;
    final CreatePlaylistOutputBoundary createPlaylistPresenter;
    final GetRecommendationAPIHandler getRecommendationHandler;
    final CreatePlaylistAPIHandler playlistHandler;

    public CreatePlaylistInteractor(LoginDataAccessInterface userDataAccessObject,
                                    MoodDataAccessInterface moodDataAccessObject,
                                    CreatePlaylistOutputBoundary createPlaylistPresenter,
                                    GetRecommendationAPIHandler getRecommendationHandler,
                                    CreatePlaylistAPIHandler playlistHandler) {
        this.userDataAccessObject = userDataAccessObject;
        this.moodDataAccessObject = moodDataAccessObject;
        this.createPlaylistPresenter = createPlaylistPresenter;
        this.getRecommendationHandler = getRecommendationHandler;
        this.playlistHandler = playlistHandler;
    }

    public void execute(CreatePlaylistInputData createPlaylistInputData){
        try {
            User user = userDataAccessObject.getCurrentUser();
            String token = user.getToken();
            //Set<String> seedTracks = user.getTopTracks();
            String selectedMood = createPlaylistInputData.getSelectedMoodName();
            Mood moodDetails = moodDataAccessObject.getMoods().get(selectedMood);
            double acousticness = moodDetails.getAcousticness();
            double danceability = moodDetails.getDanceability();
            double energy = moodDetails.getEnergy();
            double instrumentalness = moodDetails.getInstrumentalness();
            double liveness = moodDetails.getLiveness();
            double speechiness = moodDetails.getSpeechiness();
            double valence = moodDetails.getValence();
            //Set<String> recommendations = this.getRecommendationHandler.getRecommendation(seedTracks, acousticness, danceability, energy, instrumentalness, liveness, speechiness, valence);
            //this.playlistHandler.getPlaylistInfo(selectedMood + " Playlist", recommendations);
            createPlaylistPresenter.prepareSuccessView();
        } catch (Exception e) {
            createPlaylistPresenter.prepareFailView(e.getMessage());
        }
    }
}
