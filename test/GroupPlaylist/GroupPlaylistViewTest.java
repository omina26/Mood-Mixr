package GroupPlaylist;

import app.GroupPlaylistUseCaseFactory;
import data_access.group_playlist.GroupPlaylistDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist.GroupPlaylistViewModel;
import interface_adapter.group_playlist_created.GroupPlaylistCreatedViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.select_user_playlist.SelectUserPlaylistsViewModel;
import org.junit.Test;
import view.GroupPlaylistView;

import static org.junit.Assert.assertEquals;

public class GroupPlaylistViewTest {
    private GroupPlaylistViewModel mockGroupPlaylistViewModel;
    private ViewManagerModel mockViewManagerModel;
    private GroupPlaylistView groupPlaylistView;

    @Test
    public void TestCreatedView(){
        mockGroupPlaylistViewModel = new GroupPlaylistViewModel();
        mockViewManagerModel = new ViewManagerModel();
        LoggedInViewModel mockLoggedInViewModel = new LoggedInViewModel();
        GroupPlaylistDataAccessObject mockDataAccessObject = new GroupPlaylistDataAccessObject();
        SelectUserPlaylistsViewModel mockSelectUserPlaylistsViewModel = new SelectUserPlaylistsViewModel();
        GroupPlaylistCreatedViewModel mockGroupPlaylistCreatedViewModel = new GroupPlaylistCreatedViewModel();

        groupPlaylistView = GroupPlaylistUseCaseFactory.create(mockViewManagerModel, mockGroupPlaylistViewModel,
                mockLoggedInViewModel, mockDataAccessObject,
                mockSelectUserPlaylistsViewModel, mockGroupPlaylistCreatedViewModel);

        assert groupPlaylistView != null;
        assertEquals("Group Playlist", groupPlaylistView.viewName);
        assertEquals(mockGroupPlaylistViewModel, groupPlaylistView.groupPlaylistViewModel);
        assertEquals(false, groupPlaylistView.user_playlists);
        assertEquals("", groupPlaylistView.other_playlist_id);
    }

}

