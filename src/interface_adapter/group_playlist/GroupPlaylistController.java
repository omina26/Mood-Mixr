package interface_adapter.group_playlist;

import entity.User;
import use_case.group_playlist.GroupPlaylistInputBoundary;

public class GroupPlaylistController {

    final GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor;

    public GroupPlaylistController(GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor) {
        this.groupPlaylistUseCaseInteractor = groupPlaylistUseCaseInteractor;
    }


    public void executeUseCase(){
    }

    public void getPlaylists(User user){
        groupPlaylistUseCaseInteractor.getPlaylists(user);
    };

}
