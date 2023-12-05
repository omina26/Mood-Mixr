package interface_adapter.group_playlist;

import entity.User;
import use_case.group_playlist.GroupPlaylistInputBoundary;
import use_case.group_playlist.GroupPlaylistInputData;

import java.io.IOException;
import java.util.ArrayList;

public class GroupPlaylistController {

    final GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor;

    public GroupPlaylistController(GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor) {
        this.groupPlaylistUseCaseInteractor = groupPlaylistUseCaseInteractor;
    }


    public void executeUseCase(ArrayList<String> selectedPlaylists, boolean userPlaylistsOnly) {
        GroupPlaylistInputData groupPlaylistInputData = new GroupPlaylistInputData(selectedPlaylists,
                userPlaylistsOnly);
        groupPlaylistUseCaseInteractor.execute(groupPlaylistInputData);
    }

    public void getPlaylists(User user, Boolean self_only) {
        try {
            GroupPlaylistInputData groupPlaylistInputData = new GroupPlaylistInputData(user, self_only);
            groupPlaylistUseCaseInteractor.getPlaylists(groupPlaylistInputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
