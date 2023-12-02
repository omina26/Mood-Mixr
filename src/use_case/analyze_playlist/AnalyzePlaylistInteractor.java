package use_case.analyze_playlist;


import data_access.login.UserDataAccessObject;
import entity.User;
import use_case.login.LoginDataAccessInterface;

public class AnalyzePlaylistInteractor implements AnalyzePlaylistInputBoundary {

    final AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject;
    final AnalyzePlaylistOutputBoundary analyzePlaylistPresenter;

    final LoginDataAccessInterface userDataAccessObject;




    public AnalyzePlaylistInteractor(LoginDataAccessInterface userDataAccessObject,
                                     AnalyzePlaylistDataAccessInterface analyzePlaylistDataAccessObject,
                                     AnalyzePlaylistOutputBoundary analyzePlaylistPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.analyzePlaylistDataAccessObject = analyzePlaylistDataAccessObject;
        this.analyzePlaylistPresenter = analyzePlaylistPresenter;
    }


    private String getPlaylistItems() {

    }

    @Override
    public void execute(AnalyzePlaylistInputData analyzePlaylistInputData) {
        String playlistID = analyzePlaylistInputData.getPlaylistID();

        analyzePlaylistDataAccessObject.savePlaylistID(playlistID);
    }
}
