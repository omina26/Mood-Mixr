package use_case.group_playlist;

public interface GroupPlaylistOutputBoundary {
    void getCurrentUserPlaylistsSuccessView(GroupPlaylistOutputData groupPlaylistOutputData);

    void prepareSuccessView(GroupPlaylistOutputData groupPlaylistOutputData, String successMessage);

    void prepareFailView(GroupPlaylistOutputData groupPlaylistOutputData, String failMessage);
}
