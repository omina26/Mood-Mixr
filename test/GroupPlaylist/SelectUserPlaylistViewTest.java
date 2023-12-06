package GroupPlaylist;

import app.GroupPlaylistUseCaseFactory;
import data_access.group_playlist.GroupPlaylistDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist.GroupPlaylistViewModel;
import interface_adapter.group_playlist_created.GroupPlaylistCreatedViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.select_user_playlist.SelectUserPlaylistState;
import interface_adapter.select_user_playlist.SelectUserPlaylistsViewModel;
import org.junit.Test;
import view.GroupPlaylistView;
import view.SelectUserPlaylistView;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SelectUserPlaylistViewTest {
    private GroupPlaylistViewModel mockGroupPlaylistViewModel;
    private ViewManagerModel mockViewManagerModel;
    private GroupPlaylistView mockGroupPlaylistView;

    @Test
    public void TestCreatedView() {
        mockGroupPlaylistViewModel = new GroupPlaylistViewModel();
        mockViewManagerModel = new ViewManagerModel();
        LoggedInViewModel mockLoggedInViewModel = new LoggedInViewModel();
        GroupPlaylistDataAccessObject mockDataAccessObject = new GroupPlaylistDataAccessObject();
        SelectUserPlaylistsViewModel selectUserPlaylistsViewModel = new SelectUserPlaylistsViewModel();
        GroupPlaylistCreatedViewModel mockGroupPlaylistCreatedViewModel = new GroupPlaylistCreatedViewModel();


        mockGroupPlaylistView = GroupPlaylistUseCaseFactory.create(mockViewManagerModel, mockGroupPlaylistViewModel,
                mockLoggedInViewModel, mockDataAccessObject, selectUserPlaylistsViewModel, mockGroupPlaylistCreatedViewModel);

        SelectUserPlaylistView selectUserPlaylistsView = new SelectUserPlaylistView(selectUserPlaylistsViewModel,
                mockGroupPlaylistView.groupPlaylistController,
                mockViewManagerModel);

        SelectUserPlaylistState selectUserPlaylistState = selectUserPlaylistsViewModel.getState();
        ArrayList<String> allPlaylists = new ArrayList<String>();
        allPlaylists.add("hi!");
        selectUserPlaylistState.setAllPlaylists(allPlaylists);
        selectUserPlaylistState.setNonUserPlaylistID("");
        selectUserPlaylistState.setUser(new User("0", "0", "0"));
        selectUserPlaylistsViewModel.firePropertyChanged();

        assert selectUserPlaylistsView != null;

        selectUserPlaylistsView.checkBoxes.get(0).setSelected(true);
        assertEquals("Select User Playlists", selectUserPlaylistsView.viewName);
        assertEquals(selectUserPlaylistsViewModel, selectUserPlaylistsView.selectUserPlaylistsViewModel);
        assertEquals(selectUserPlaylistsView.selectedPlaylists, allPlaylists);
        assertEquals(selectUserPlaylistsView.selectedPlaylists, allPlaylists);

    }
}