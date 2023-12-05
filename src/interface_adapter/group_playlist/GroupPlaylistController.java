package interface_adapter.group_playlist;

import entity.User;
import use_case.group_playlist.GroupPlaylistInputBoundary;
import use_case.group_playlist.GroupPlaylistInputData;

import javax.swing.*;
import java.io.IOException;

public class GroupPlaylistController {

    final GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor;

    public GroupPlaylistController(GroupPlaylistInputBoundary groupPlaylistUseCaseInteractor) {
        this.groupPlaylistUseCaseInteractor = groupPlaylistUseCaseInteractor;
    }


    public void executeUseCase() {
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
