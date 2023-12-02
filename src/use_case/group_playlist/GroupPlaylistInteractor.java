package use_case.group_playlist;

import entity.Playlist;
import entity.User;
import use_case.group_playlist.services.GroupPlaylistAPIInterface;

import java.util.ArrayList;

public class GroupPlaylistInteractor implements GroupPlaylistInputBoundary{

    final GroupPlaylistDataAccessInterface groupDataAccessObject;
    final GroupPlaylistOutputBoundary groupPresenter;
    final GroupPlaylistAPIInterface groupPlaylistAPIHandler;


    public GroupPlaylistInteractor(GroupPlaylistDataAccessInterface groupDataAccessObject, GroupPlaylistOutputBoundary groupPresenter, GroupPlaylistAPIInterface groupPlaylistAPIHandler) {
        this.groupDataAccessObject = groupDataAccessObject;
        this.groupPresenter = groupPresenter;
        this.groupPlaylistAPIHandler = groupPlaylistAPIHandler;
    }

    @Override
    public void execute() {

    }

    @Override
    public ArrayList<Playlist> getPlaylists(User self) {
        ArrayList<Playlist> playlists;
        playlists = groupPlaylistAPIHandler.getPlaylists(self);

    }

}
