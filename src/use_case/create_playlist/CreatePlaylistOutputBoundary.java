package use_case.create_playlist;

public interface CreatePlaylistOutputBoundary {
    void prepareSuccessView();
    void prepareFailView(String error);
}
