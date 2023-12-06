package GroupPlaylist;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist.GroupPlaylistPresenter;
import interface_adapter.group_playlist.GroupPlaylistViewModel;
import interface_adapter.group_playlist_created.GroupPlaylistCreatedViewModel;
import interface_adapter.select_user_playlist.SelectUserPlaylistsViewModel;
import org.junit.Test;
import use_case.group_playlist.GroupPlaylistOutputData;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GroupPlaylistPresenterTest {
    @Test
    public void GroupPlaylistPresenterPrepareSuccessViewTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GroupPlaylistViewModel groupPlaylistViewModel = new GroupPlaylistViewModel();
        SelectUserPlaylistsViewModel selectUserPlaylistsViewModel = new SelectUserPlaylistsViewModel();
        GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel = new GroupPlaylistCreatedViewModel();
        GroupPlaylistPresenter testGroupPlaylistPresenter = new GroupPlaylistPresenter(viewManagerModel,
                groupPlaylistViewModel, selectUserPlaylistsViewModel, groupPlaylistCreatedViewModel);
        GroupPlaylistOutputData groupPlaylistOutputData = new GroupPlaylistOutputData(true, "hello");
        testGroupPlaylistPresenter.prepareSuccessView(groupPlaylistOutputData, "success");
        assertEquals(viewManagerModel.getActiveView(), "Group Playlist Created");
        assertEquals(groupPlaylistCreatedViewModel.getState().getMessage(), "success");
    }
    @Test
    public void GroupPlaylistPresenterPrepareFailViewTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GroupPlaylistViewModel groupPlaylistViewModel = new GroupPlaylistViewModel();
        SelectUserPlaylistsViewModel selectUserPlaylistsViewModel = new SelectUserPlaylistsViewModel();
        GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel = new GroupPlaylistCreatedViewModel();
        GroupPlaylistPresenter testGroupPlaylistPresenter = new GroupPlaylistPresenter(viewManagerModel,
                groupPlaylistViewModel, selectUserPlaylistsViewModel, groupPlaylistCreatedViewModel);
        GroupPlaylistOutputData groupPlaylistOutputData = new GroupPlaylistOutputData(false, "hello");
        testGroupPlaylistPresenter.prepareFailView(groupPlaylistOutputData, "failure");
        assertEquals(viewManagerModel.getActiveView(), "Group Playlist Created");
        assertEquals(groupPlaylistCreatedViewModel.getState().getMessage(), "failure");
    }

    @Test
    public void GroupPlaylistPresenterGetCurrentUserPlaylistsSuccessViewTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GroupPlaylistViewModel groupPlaylistViewModel = new GroupPlaylistViewModel();
        SelectUserPlaylistsViewModel selectUserPlaylistsViewModel = new SelectUserPlaylistsViewModel();
        GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel = new GroupPlaylistCreatedViewModel();
        GroupPlaylistPresenter testGroupPlaylistPresenter = new GroupPlaylistPresenter(viewManagerModel,
                groupPlaylistViewModel, selectUserPlaylistsViewModel, groupPlaylistCreatedViewModel);
        ArrayList<String> playlists = new ArrayList<String>();
        User user = new User("name", "token", "userId");
        boolean isUserPlaylistsOnly = false;
        String nonUserPlaylistID = "ID";
        GroupPlaylistOutputData groupPlaylistOutputData = new GroupPlaylistOutputData(playlists, user,
                isUserPlaylistsOnly, nonUserPlaylistID);
        testGroupPlaylistPresenter.getCurrentUserPlaylistsSuccessView(groupPlaylistOutputData);
        assertEquals(viewManagerModel.getActiveView(), "Select User Playlists");
        assertEquals(selectUserPlaylistsViewModel.getState().getAllPlaylists(), playlists);
        assertEquals(selectUserPlaylistsViewModel.getState().getNonUserPlaylistID(), "ID");
        assertEquals(selectUserPlaylistsViewModel.getState().getUser(), user);
        assertEquals(selectUserPlaylistsViewModel.getState().getUserPlaylistsOnly(), false);
    }
}
