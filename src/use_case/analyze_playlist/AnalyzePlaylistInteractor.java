package use_case.analyze_playlist;

import entity.User;
import use_case.login.LoginDataAccessInterface;
import use_case.login.services.TracksAudioFeaturesAPIHandler;
import use_case.login.services.UserPlaylistItemsAPIHandler;

import javax.json.JsonObject;
import java.util.List;
import java.util.Set;

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
            analyzePlaylistPresenter.prepareSuccessView();
        } catch (Exception e) {
            analyzePlaylistPresenter.prepareFailView(e.getMessage());
        }
    }
}
