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

public class GroupPlaylistUseCaseFactoryTest {
    @Test
    public void groupPlaylistUseCaseFactoryTest(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        GroupPlaylistViewModel groupPlaylistViewModel = new GroupPlaylistViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        GroupPlaylistDataAccessObject groupPlaylistDataAccessObject = new GroupPlaylistDataAccessObject();
        SelectUserPlaylistsViewModel selectUserPlaylistsViewModel = new SelectUserPlaylistsViewModel();
        GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel = new GroupPlaylistCreatedViewModel();

        GroupPlaylistView testview = GroupPlaylistUseCaseFactory.create(viewManagerModel, groupPlaylistViewModel, loggedInViewModel,
                groupPlaylistDataAccessObject, selectUserPlaylistsViewModel, groupPlaylistCreatedViewModel);

        assert testview != null;
        assertEquals(testview.viewName, "Group Playlist");
    }

}
