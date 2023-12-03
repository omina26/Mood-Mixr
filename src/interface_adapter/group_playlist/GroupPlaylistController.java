package interface_adapter.group_playlist;

import entity.User;
import use_case.group_playlist.GroupPlaylistInputBoundary;

public class GroupPlaylistController {

    final GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor;
    final User self;

    public GroupPlaylistController(GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor, User self) {
        this.groupPlaylistUseCaseInteractor = groupPlaylistUseCaseInteractor;
        this.self = self;
    }


    public void executeUseCase(){
    }

    public void getPlaylists(){
        groupPlaylistUseCaseInteractor.getPlaylists(self);
    };

}
