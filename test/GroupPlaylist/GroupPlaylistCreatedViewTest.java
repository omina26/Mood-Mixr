package GroupPlaylist;

import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist_created.GroupPlaylistCreatedViewModel;
import org.junit.Test;
import view.GroupPlaylistCreatedView;

import static org.junit.Assert.assertEquals;

public class GroupPlaylistCreatedViewTest {
    private GroupPlaylistCreatedViewModel mockGroupPlaylistCreatedViewModel;
    private ViewManagerModel mockViewManagerModel;
    private GroupPlaylistCreatedView groupPlaylistCreatedView;

    @Test
    public void TestCreatedView(){
        mockGroupPlaylistCreatedViewModel = new GroupPlaylistCreatedViewModel();
        mockViewManagerModel = new ViewManagerModel();
        groupPlaylistCreatedView = new GroupPlaylistCreatedView(mockGroupPlaylistCreatedViewModel,
                mockViewManagerModel);
        assertEquals("Group Playlist Created", groupPlaylistCreatedView.viewName);
        assertEquals(mockGroupPlaylistCreatedViewModel, groupPlaylistCreatedView.groupPlaylistCreatedViewModel);
        assertEquals(mockViewManagerModel, groupPlaylistCreatedView.viewManagerModel);
    }

}

