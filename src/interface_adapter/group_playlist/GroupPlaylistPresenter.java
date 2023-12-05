package interface_adapter.group_playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.group_playlist.GroupPlaylistOutputBoundary;
import use_case.group_playlist.GroupPlaylistOutputData;

import java.util.ArrayList;

public class GroupPlaylistPresenter implements GroupPlaylistOutputBoundary {

    ViewManagerModel viewManagerModel;
    GroupPlaylistViewModel groupPlaylistViewModel;


    public GroupPlaylistPresenter(ViewManagerModel viewManagerModel,  GroupPlaylistViewModel groupPlaylistViewModel){
            this.groupPlaylistViewModel = groupPlaylistViewModel;
            this.viewManagerModel = viewManagerModel;
    }
    public ArrayList<String> getCurrentUserPlaylists(GroupPlaylistOutputData groupPlaylistOutputData){
        GroupPlaylistState groupPlaylistState = groupPlaylistViewModel.getState();
    }
}
