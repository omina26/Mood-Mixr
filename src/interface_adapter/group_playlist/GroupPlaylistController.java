package interface_adapter.group_playlist;

import use_case.group_playlist.GroupPlaylistInputBoundary;

public class GroupPlaylistController {

    final GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor;


    public GroupPlaylistController(GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor) {
        this.groupPlaylistUseCaseInteractor = groupPlaylistUseCaseInteractor;
    }


    public void executeUseCase(){

    }
}
