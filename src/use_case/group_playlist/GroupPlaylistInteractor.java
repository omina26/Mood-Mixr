package use_case.group_playlist;

import use_case.group_playlist.services.GroupPlaylistAPIInterface;

import java.io.IOException;
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
    public void execute(GroupPlaylistInputData groupPlaylistInputData) {

    }

    @Override
    public void getPlaylists(GroupPlaylistInputData groupPlaylistInputData){
        ArrayList<String> playlists;
        try {
            playlists = groupPlaylistAPIHandler.getPlaylists(groupPlaylistInputData.user);
            GroupPlaylistOutputData groupPlaylistOutputData = new GroupPlaylistOutputData(playlists);
            groupPresenter.getCurrentUserPlaylistsSuccessView(groupPlaylistOutputData);
        } catch (IOException e){
            System.out.println("Error!");
        }
    }

}
