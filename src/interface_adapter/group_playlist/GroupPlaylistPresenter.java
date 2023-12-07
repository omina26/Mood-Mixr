package interface_adapter.group_playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist_created.GroupPlaylistCreatedState;
import interface_adapter.group_playlist_created.GroupPlaylistCreatedViewModel;
import interface_adapter.select_user_playlist.SelectUserPlaylistState;
import interface_adapter.select_user_playlist.SelectUserPlaylistsViewModel;
import use_case.group_playlist.GroupPlaylistOutputBoundary;
import use_case.group_playlist.GroupPlaylistOutputData;

public class GroupPlaylistPresenter implements GroupPlaylistOutputBoundary {

    ViewManagerModel viewManagerModel;
    GroupPlaylistViewModel groupPlaylistViewModel;
    SelectUserPlaylistsViewModel selectUserPlaylistsViewModel;

    GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel;

    public GroupPlaylistPresenter(ViewManagerModel viewManagerModel,
                                  GroupPlaylistViewModel groupPlaylistViewModel,
                                  SelectUserPlaylistsViewModel selectUserPlaylistsViewModel,
                                  GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel){
            this.groupPlaylistViewModel = groupPlaylistViewModel;
            this.viewManagerModel = viewManagerModel;
            this.selectUserPlaylistsViewModel = selectUserPlaylistsViewModel;
            this.groupPlaylistCreatedViewModel = groupPlaylistCreatedViewModel;
    }

    @Override
    public void getCurrentUserPlaylistsSuccessView(GroupPlaylistOutputData groupPlaylistOutputData, GroupPlaylistOutputData names) {

        SelectUserPlaylistState selectUserPlaylistState = selectUserPlaylistsViewModel.getState();
        selectUserPlaylistState.setAllPlaylists(groupPlaylistOutputData.playlists);
        selectUserPlaylistState.setUserPlaylistsOnly(groupPlaylistOutputData.isUserPlaylistsOnly);
        selectUserPlaylistState.setNames(names.playlists);
        selectUserPlaylistState.setNonUserPlaylistID(groupPlaylistOutputData.nonUserPlaylistID);
        selectUserPlaylistState.setUser(groupPlaylistOutputData.user);

        this.selectUserPlaylistsViewModel.setState(selectUserPlaylistState);
        selectUserPlaylistsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(selectUserPlaylistsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(GroupPlaylistOutputData groupPlaylistOutputData, String successMessage) {
        GroupPlaylistCreatedState groupPlaylistCreatedState = groupPlaylistCreatedViewModel.getState();
        groupPlaylistCreatedState.setUser(groupPlaylistOutputData.user);
        groupPlaylistCreatedState.setMessage(successMessage);

        this.groupPlaylistCreatedViewModel.setState(groupPlaylistCreatedState);
        groupPlaylistCreatedViewModel.firePropertyChanged();

        System.out.println("here");
        viewManagerModel.setActiveView(groupPlaylistCreatedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(GroupPlaylistOutputData groupPlaylistOutputData, String failMessage) {
        GroupPlaylistCreatedState groupPlaylistCreatedState = groupPlaylistCreatedViewModel.getState();
        groupPlaylistCreatedState.setUser(groupPlaylistOutputData.user);
        groupPlaylistCreatedState.setMessage(failMessage);

        this.groupPlaylistCreatedViewModel.setState(groupPlaylistCreatedState);
        groupPlaylistCreatedViewModel.firePropertyChanged();

        System.out.println("here");
        viewManagerModel.setActiveView(groupPlaylistCreatedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }


}
