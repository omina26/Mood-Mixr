package interface_adapter.group_playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.select_user_playlist.SelectUserPlaylistState;
import interface_adapter.select_user_playlist.SelectUserPlaylistsViewModel;
import use_case.group_playlist.GroupPlaylistOutputBoundary;
import use_case.group_playlist.GroupPlaylistOutputData;

import java.util.ArrayList;

public class GroupPlaylistPresenter implements GroupPlaylistOutputBoundary {

    ViewManagerModel viewManagerModel;
    GroupPlaylistViewModel groupPlaylistViewModel;
    SelectUserPlaylistsViewModel selectUserPlaylistsViewModel;

    public GroupPlaylistPresenter(ViewManagerModel viewManagerModel,
                                  GroupPlaylistViewModel groupPlaylistViewModel,
                                  SelectUserPlaylistsViewModel selectUserPlaylistsViewModel){
            this.groupPlaylistViewModel = groupPlaylistViewModel;
            this.viewManagerModel = viewManagerModel;
            this.selectUserPlaylistsViewModel = selectUserPlaylistsViewModel;
    }

    @Override
    public void getCurrentUserPlaylistsSuccessView(GroupPlaylistOutputData groupPlaylistOutputData) {

        SelectUserPlaylistState selectUserPlaylistState = selectUserPlaylistsViewModel.getState();
        selectUserPlaylistState.setAllPlaylists(groupPlaylistOutputData.playlists);
        selectUserPlaylistState.setUserPlaylistsOnly(groupPlaylistOutputData.isUserPlaylistsOnly);
        selectUserPlaylistState.setNonUserPlaylistID(groupPlaylistOutputData.nonUserPlaylistID);
        selectUserPlaylistState.setUser(groupPlaylistOutputData.user);

        this.selectUserPlaylistsViewModel.setState(selectUserPlaylistState);
        selectUserPlaylistsViewModel.firePropertyChanged();

        System.out.println(selectUserPlaylistsViewModel.getViewName());
        viewManagerModel.setActiveView(selectUserPlaylistsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(GroupPlaylistOutputData groupPlaylistOutputData, String successMessage) {

    }

    @Override
    public void prepareFailView(GroupPlaylistOutputData groupPlaylistOutputData, String failMessage) {

    }

    public void getCurrentUserPlaylistsFailView(String errorMessage){
        SelectUserPlaylistState selectUserPlaylistState = selectUserPlaylistsViewModel.getState();
        ArrayList<String> failedMessage = new ArrayList<String>();
        failedMessage.add(errorMessage);
        selectUserPlaylistState.setAllPlaylists(failedMessage);
    }


}
