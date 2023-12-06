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
        Boolean success;
        if (groupPlaylistInputData.self_only){
            success = groupPlaylistAPIHandler.combinePlaylists(groupPlaylistInputData.playlistsToCombine,
                    groupPlaylistInputData.user);

        } else{
            if(groupPlaylistAPIHandler.isValidPlaylist(groupPlaylistInputData.nonUserPlaylistID,
                    groupPlaylistInputData.user)){
                ArrayList<String> playlists = groupPlaylistInputData.playlistsToCombine;
                playlists.add("spotify:playlist:" + groupPlaylistInputData.nonUserPlaylistID);
                success = groupPlaylistAPIHandler.combinePlaylists(playlists, groupPlaylistInputData.user);
            }
            else{
                success = false;
            }
        }
        GroupPlaylistOutputData groupPlaylistOutputData = new GroupPlaylistOutputData(success, "name");
        if(!success){
            groupPresenter.prepareSuccessView(groupPlaylistOutputData, "Successfully created playlist!");
        } else{
            groupPresenter.prepareFailView(groupPlaylistOutputData, "Could not properly create group playlist :(");
        }
    }

    @Override
    public void getPlaylists(GroupPlaylistInputData groupPlaylistInputData){
        ArrayList<String> playlists;
        try {
            playlists = groupPlaylistAPIHandler.getPlaylists(groupPlaylistInputData.user);
            GroupPlaylistOutputData groupPlaylistOutputData = new GroupPlaylistOutputData(playlists,
                    groupPlaylistInputData.user,
                    groupPlaylistInputData.self_only, groupPlaylistInputData.nonUserPlaylistID);
            groupPresenter.getCurrentUserPlaylistsSuccessView(groupPlaylistOutputData);
        } catch (IOException e){
            System.out.println("Error!");
        }
    }

}
